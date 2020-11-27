package top.ptcc9.service;

import org.springframework.stereotype.Component;
import top.ptcc9.po.Customer;
import top.ptcc9.vo.CustomerVo;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-24 12:40
 */
@Component
public interface AccountService {
    /**
     * pojo => vo
     * @param customer
     * @return
     */
    public CustomerVo customerToVo(Customer customer);

    /**
     * 通过 Phone 查询 Customer
     * @param phone
     * @return
     */
    public Customer getCustomerByPhone(String phone);


    /**
     * 插入用户 ==> 持久层
     * @param customer
     * @return
     */
    public Integer insertCustomer(Customer customer);

    /**
     * 根据 id 查询用户 无密码字段
     * @param token
     * @return
     */
    public Customer getCurrentCustomerInfo(String token);
}
