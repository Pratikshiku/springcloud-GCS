package top.ptcc9.pojo.DTO;

import lombok.Data;

import java.util.List;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-12-28 21:25
 */
@Data
public class AddressDetailDto {
    /**
     * 目标添加顾客
     */
    private String openId;
    /**
     * ["广东省","中山市","石岐街道"]
     */
    private List<Object> province;
    /**
     * 电子科技大学中山学院
     */
    private String area;
    /**
     * 14栋-414
     */
    private String detail;
    private String contactName;
    private String phone;
    /**
     * 默认地址是否变化  如果为true则把当前customer的
     * default address id 换成 当前 address id
     */
    private boolean changeDefault;
}
