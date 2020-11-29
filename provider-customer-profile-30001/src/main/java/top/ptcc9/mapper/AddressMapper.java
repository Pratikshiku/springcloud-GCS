package top.ptcc9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.ptcc9.pojo.DO.Address;

/**
 * @author: HE LONG CAN
 * @description: 地址持久化
 * @date: 2020-11-29 00:20
 */
@Mapper
@Repository
public interface AddressMapper extends BaseMapper<Address> {
}
