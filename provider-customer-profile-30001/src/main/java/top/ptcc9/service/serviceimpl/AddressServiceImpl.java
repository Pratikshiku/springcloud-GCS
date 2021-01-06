package top.ptcc9.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ptcc9.mapper.AddressMapper;
import top.ptcc9.mapper.CustomerMapper;
import top.ptcc9.pojo.DO.Address;
import top.ptcc9.pojo.DO.Customer;
import top.ptcc9.pojo.DTO.AddressDetailDto;
import top.ptcc9.pojo.DTO.UpdateAddressDetailDto;
import top.ptcc9.pojo.VO.AddressDetailVo;
import top.ptcc9.pojo.VO.AddressVo;
import top.ptcc9.pojo.VO.CustomerVo;
import top.ptcc9.service.AddressService;
import top.ptcc9.utils.RedisUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pratik_shiku
 */
@SuppressWarnings("unchecked")
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    AddressMapper addressMapper;

    @Resource
    CustomerMapper customerMapper;

    @Resource
    RedisUtil redisUtil;

    @Override
    public List<AddressVo> getAddressByOpenId(String openId, String defaultAddressId) {
        List<Address> addressList = addressMapper.selectList(
                new QueryWrapper<Address>()
                        .eq("customer_open_id", openId)
                        .eq("deleted",0));
        List<AddressVo> addressVoList = new ArrayList<>();
        for (Address item : addressList) {
            addressVoList.add(addressToVo(item, defaultAddressId));
        }
        return addressVoList;
    }

    @Override
    public List<Object> getAvailableAreaList(String province) {
        if (!redisUtil.hasKey(province)) {
            return null;
        }
        return redisUtil.lGet(province, 0, -1);
    }

    @Override
    public AddressDetailVo getAddressByAddressId(String addressId, String defaultAddressId) {
        Address address = addressMapper.selectOne(
                new QueryWrapper<Address>()
                        .eq("id", addressId)
                        .eq("deleted",0));
        AddressDetailVo addressDetailVo = null;
        if (address != null) {
            addressDetailVo = addressToDetailVo(address, defaultAddressId);
        }
        return addressDetailVo;
    }

    @Override
    @Transactional
    public void addAddress(AddressDetailDto addressDetailDto) {
        Address address = new Address();
        StringBuilder stringBuilder = new StringBuilder();
        address.setId(IdUtil.simpleUUID());
        address.setCustomerOpenId(addressDetailDto.getOpenId());
        addressDetailDto.getProvince().forEach(item -> {
            stringBuilder.append(item);
            stringBuilder.append("-");
        });
        stringBuilder.append(addressDetailDto.getArea() + "-");
        stringBuilder.append(addressDetailDto.getDetail());
        address.setAddress(String.valueOf(stringBuilder));
        address.setContactName(addressDetailDto.getContactName());
        address.setPhone(addressDetailDto.getPhone());
        if (addressMapper.insert(address) == 0) {
            throw new RuntimeException();
        } else {
            if (addressDetailDto.isChangeDefault()) {
                Customer customer = new Customer();
                customer.setOpenId(addressDetailDto.getOpenId());
                customer.setDefaultAddressId(address.getId());
                int open_id = customerMapper.update(customer,
                        new QueryWrapper<Customer>()
                                .eq("open_id", customer.getOpenId())
                );
                if (open_id == 0) {
                    throw new RuntimeException();
                }
            }
        }
    }

    @Override
    @Transactional
    public void updateAddressById(UpdateAddressDetailDto updateAddressDetailDto) {
        Address address = new Address();
        StringBuilder stringBuilder = new StringBuilder();
        address.setId(updateAddressDetailDto.getAddressId());
        address.setCustomerOpenId(updateAddressDetailDto.getOpenId());
        updateAddressDetailDto.getProvince().forEach(item -> {
            stringBuilder.append(item);
            stringBuilder.append("-");
        });
        stringBuilder.append(updateAddressDetailDto.getArea() + "-");
        stringBuilder.append(updateAddressDetailDto.getDetail());
        address.setAddress(String.valueOf(stringBuilder));
        address.setContactName(updateAddressDetailDto.getContactName());
        address.setPhone(updateAddressDetailDto.getPhone());
        if (addressMapper.update(address, new QueryWrapper<Address>().eq("id",address.getId())) == 0) {
            throw new RuntimeException();
        } else {
            if (updateAddressDetailDto.isChangeDefault()) {
                Customer customer = new Customer();
                customer.setOpenId(address.getCustomerOpenId());
                customer.setDefaultAddressId(address.getId());
                int open_id = customerMapper.update(customer,
                        new QueryWrapper<Customer>()
                                .eq("open_id", customer.getOpenId())
                );
                if (open_id == 0) {
                    throw new RuntimeException();
                }
            }
        }
    }

    @Override
    public Boolean deleteAddressById(String addressId, String defaultId) {
        Address address = addressMapper.selectById(addressId);
        if (address != null) {
            address.setDeleted(1);
            addressMapper.updateById(address);
            return true;
        } else {
            return false;
        }
    }

    private AddressVo addressToVo(Address address, String defaultAddressId) {
        AddressVo addressVo = new AddressVo();
        address.setAddress(address.getAddress().replaceAll("-",""));
        //拷贝属性
        BeanUtil.copyProperties(address, addressVo, "customerOpenId");
        if (!defaultAddressId.equals(address.getId())) {
            addressVo.setDefault(false);
        } else {
            addressVo.setDefault(true);
        }
        return addressVo;
    }

    private AddressDetailVo addressToDetailVo(Address address, String defaultAddressId) {
        AddressDetailVo addressDetailVo = new AddressDetailVo();
        //拷贝属性
        BeanUtil.copyProperties(address, addressDetailVo, "customerOpenId", "address");
        if (!defaultAddressId.equals(address.getId())) {
            addressDetailVo.setDefault(false);
        } else {
            addressDetailVo.setDefault(true);
        }
        String[] split = address.getAddress().split("-");
        List<String> provinceList = new ArrayList<>();
        provinceList.add(split[0]);
        provinceList.add(split[1]);
        provinceList.add(split[2]);
        StringBuilder stringBuilder = new StringBuilder();
        addressDetailVo.setArea(split[3]);
        for (int i = 4; i < split.length; i++) {
            stringBuilder.append(split[i]);
            if (i < split.length - 1) {
                stringBuilder.append("-");
            }
        }
        addressDetailVo.setProvince(provinceList);
        addressDetailVo.setDetail(String.valueOf(stringBuilder));
        return addressDetailVo;
    }
}
