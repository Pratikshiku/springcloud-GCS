package top.ptcc9.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.ptcc9.po.Customer;

/**
 * @author: HE LONG CAN
 * @description: DAO
 * @date: 2020-11-24 12:34
 */
@Mapper
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {
    /**
     * 通过手机号查用户
     * @param phone
     * @return
     */
    @Select("select * from customer where phone = #{phone} and deleted = 0 limit 1")
    Customer getCustomerByPhone(String phone);

    /**
     * 通过 id 取用户
     * @param id
     * @return
     */
    @Select("select id,phone,balance,create_time,vip_expiration from customer where id = #{id} and deleted = 0 limit 1")
    Customer getCustomerById(String id);


    /**
     * 查看手机号是否已注册
     * @param phone
     * @return
     */
    @Select("select 1 from customer where phone = #{phone}")
    Integer checkRegistered(String phone);
}
