package top.ptcc9.pojo.DO;

import lombok.Data;
import java.util.Date;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-21 17:02
 */
@Data
public class Bill {
    /**
     * 订单id
     */
    private String billId;
    /**
     * 下单顾客id
     */
    private String customerId;
    /**
     * 工人id - 顾客下单时为null
     */
    private String workerId;

    /**
     * 地址id
     */
    private String addressId;

    /**
     * 预估重量
     */
    private Integer preWeight;

    /**
     * 真实重量 - 工人未接单时为null，工人处理订单时若无问题，则真实重量与预估重量相符
     */
    private Integer weight;

    /**
     * 总价
     */
    private Double totalPrice;

    /**
     * 优惠价格
     */
    private Double discount;

    /**
     * 最终价格
     */
    private Double finalPrice;


    /**
     * 使用的优惠卷id   未使用为null
     */
    private String couponId;


    /**
     * 订单状态  数据库默认为 0
     * 0 - 正在进行
     * 1 - 已完成
     * 2 - 问题订单
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发起时间
     */
    private Date createTime;
    /**
     * 工人接单时间
     */
    private Date receiveTime;
    /**
     * 订单完成时间
     */
    private Date completeTime;
}
