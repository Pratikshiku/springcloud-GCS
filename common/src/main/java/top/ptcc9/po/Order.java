package top.ptcc9.po;

import lombok.Data;
import java.util.Date;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-21 17:02
 */
@Data
public class Order {
    private Long id;
    private Long customerId;
    private Long workerId;
    private Integer areaId;
    private Long addressId;
    private Date createTime;

    /**
     * 预估重量
     */
    private Integer preWeight;

    /**
     * 真实重量
     */
    private Integer weight;

    private Double price;
    private String remarks;
    private String comment;
}
