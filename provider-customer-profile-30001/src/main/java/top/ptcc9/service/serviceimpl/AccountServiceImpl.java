package top.ptcc9.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import top.ptcc9.mapper.CustomerMapper;
import top.ptcc9.po.Customer;
import top.ptcc9.service.AccountService;
import top.ptcc9.utils.JwtUtil;
import top.ptcc9.vo.CustomerVo;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-24 12:43
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public CustomerVo customerToVo(Customer customer) {
        /**
         * 属性拷贝
         * 计算过两个日期之差
         * 是否过期
         */
        CustomerVo customerVo = new CustomerVo();
        BeanUtil.copyProperties(customer,customerVo,"createTime");
        customerVo.setCreateTime(DateUtil.format(customer.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        if (customer.getVipExpiration() != null) {
            DateTime expirationTime = DateTime.of(customer.getVipExpiration());
            long between = DateUtil.between(DateTime.now(), expirationTime, DateUnit.DAY);
            customerVo.setVipExpiration(between + 1);
            customerVo.setExpired(expirationTime.isBefore(DateTime.now()));
        }
        return customerVo;
    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        return customerMapper.getCustomerByPhone(phone);
    }

    /**
     * 生成时间 + 全局唯一id
     * password加密
     * 查看 phone 是否已注册
     * 注册
     * @param customer
     * @return
     */
    @Override
    public Integer insertCustomer(Customer customer) {
        customer.setCreateTime(DateTime.now()).setId(IdUtil.simpleUUID());
        String md5 = SecureUtil.md5().digestHex(customer.getPassword());
        customer.setPassword(md5);
        int insert = 0;
        if (customerMapper.checkRegistered(customer.getPhone()) == null) {
            insert = customerMapper.insert(customer);
        }
        return insert;
    }

    @Override
    public Customer getCurrentCustomerInfo(String token) {
        String id = JwtUtil.getClaims(token).get("id").asString();
        return customerMapper.getCustomerById(id);
    }
}
