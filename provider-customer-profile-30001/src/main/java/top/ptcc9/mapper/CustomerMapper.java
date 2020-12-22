package top.ptcc9.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.ptcc9.pojo.DO.Customer;

/**
 * @author: HE LONG CAN
 * @description: DAO
 * @date: 2020-11-24 12:34
 */
@Mapper
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {
    /**
     * 根据 openId 获取用户信息
     * @param openId
     * @return
     */
    @Select("select * from customer where open_id = #{openId}")
    public Customer getCustomerByOpenId(String openId);
}
