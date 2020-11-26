package top.ptcc9.po;

import lombok.Data;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-21 17:01
 */
@Data
public class Address {
    private Long id;
    private Long customerId;
    private Integer areaId;
    private String address;
    private String phone;
}
