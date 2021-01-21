package top.ptcc9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.ptcc9.pojo.DO.Picture;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2021-01-20 13:59
 */
@Mapper
@Repository
public interface PictureMapper extends BaseMapper<Picture> {
}
