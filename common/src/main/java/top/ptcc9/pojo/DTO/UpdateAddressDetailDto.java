package top.ptcc9.pojo.DTO;

import lombok.Data;

import java.util.List;

/**
 * @author: HE LONG CAN
 * @description: 用于更新地址
 * @date: 2021-01-05 20:58
 */
@Data
public class UpdateAddressDetailDto {
    /**
     * 目标地址id
     */
    private String addressId;
    /**
     * 用户openId
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
