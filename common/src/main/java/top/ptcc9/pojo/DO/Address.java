package top.ptcc9.pojo.DO;

import cn.hutool.crypto.SecureUtil;
import lombok.Data;

import java.util.List;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-21 17:01
 */
@Data
public class Address {
    private String id;
    private String customerOpenId;
    /**
     * 广东省-中山市-石岐街道-电子科技大学中山学院-14栋-414
     */
    private String address;
    private String contactName;
    private String phone;

}
