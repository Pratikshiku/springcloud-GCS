package top.ptcc9.pojo.VO;

import lombok.Data;

import java.util.List;

/**
 * @author: HE LONG CAN
 * @description: 用于查询单个地址
 * @date: 2020-12-28 17:45
 */
@Data
public class AddressDetailVo {
    private String id;
    /**
     * ["广东省","中山市","石岐街道"]
     */
    private List<String> province;
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
    private boolean isDefault;
}
