package top.ptcc9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.ptcc9.pojo.DO.Bill;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2021-01-21 22:22
 */
@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Bill> {
}
