package top.ptcc9.pojo.VO;

import lombok.Data;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-29 00:22
 */
@Data
public class AddressVo {
    private String id;
    /**
     * 广东省中山市石岐街道电子科技大学中山学院14栋414
     */
    private String address;
    private String contactName;
    private String phone;
    private boolean isDefault;
}
