package top.ptcc9.pojo.DO;

import lombok.Data;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-21 17:01
 */
@Data
public class Address {
    private String id;
    private String customerId;
    private String areaId;
    /**
     * longitude 经度
     * latitude  纬度
     */
    private String longitude;
    private String latitude;
    private String address;
    private String contactName;
    private String phone;
}
