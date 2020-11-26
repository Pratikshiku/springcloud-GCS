package top.ptcc9.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import top.ptcc9.mapper.CustomerMapper;
import top.ptcc9.po.Customer;
import top.ptcc9.service.AccountService;
import top.ptcc9.vo.CustomerVo;

import javax.annotation.Resource;

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
         */
        CustomerVo customerVo = new CustomerVo();
        BeanUtil.copyProperties(customer,customerVo,"createTime");
        customerVo.setCreateTime(DateUtil.format(customer.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));

        if (customer.getVipExpiration() != null) {
            /**
             * 计算过两个日期之差
             */
            DateTime expirationTime = DateTime.of(customer.getVipExpiration());
            long between = DateUtil.between(DateTime.now(), expirationTime, DateUnit.DAY);
            customerVo.setVipExpiration(between + 1);
            /**
             * 是否过期
             */
            customerVo.setExpired(expirationTime.isBefore(DateTime.now()));
        }
        return customerVo;
    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        return customerMapper.getCustomerByPhone(phone);
    }

    @Override
    public Integer insertCustomer(Customer customer) {
        /**
         * 生成时间
         * 生成全局唯一 UUID
         */
        customer.setCreateTime(DateTime.now()).setId(IdUtil.simpleUUID());
        /**
         * md5 盐值加密
         */
        String md5 = SecureUtil.md5().digestHex(customer.getPassword());
        customer.setPassword(md5);
        /**
         * 有可能会失败   唯一索引
         */
        int insert = 0;
        try{
            insert = customerMapper.insert(customer);
        }catch (DuplicateKeyException e){
            e.printStackTrace();
        }
        return insert;
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerMapper.getCustomerById(id);
    }
}
