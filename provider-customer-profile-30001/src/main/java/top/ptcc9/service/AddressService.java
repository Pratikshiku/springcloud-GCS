package top.ptcc9.service;

import org.springframework.stereotype.Component;
import top.ptcc9.mapper.AddressMapper;
import top.ptcc9.pojo.DTO.AddressDetailDto;
import top.ptcc9.pojo.DTO.UpdateAddressDetailDto;
import top.ptcc9.pojo.VO.AddressDetailVo;
import top.ptcc9.pojo.VO.AddressVo;

import javax.annotation.Resource;
import java.util.List;

@Component
public interface AddressService {

    /**
     * open_id获取address们
     * @param openId
     * @param defaultAddressId
     * @return
     */
    List<AddressVo> getAddressByOpenId(String openId, String defaultAddressId);

    /**
     * 查询某地址（县级）的可用区域
     * @param province
     * @return
     */
    List<Object> getAvailableAreaList(String province);

    /**
     * 查询address详情（单个）
     * @param addressId
     * @param defaultAddressId
     * @return
     */
    AddressDetailVo getAddressByAddressId(String addressId, String defaultAddressId);

    /**
     * 添加地址
     * @param addressDetailDto
     * @return
     */
    void addAddress(AddressDetailDto addressDetailDto);

    /**
     * 更新address
     * @param updateAddressDetailDto
     * @return
     */
    void updateAddressById(UpdateAddressDetailDto updateAddressDetailDto);

    /**
     * 删除地址
     * @param addressId
     * @return
     */
    void deleteAddressById(String addressId, String defaultId);
}
