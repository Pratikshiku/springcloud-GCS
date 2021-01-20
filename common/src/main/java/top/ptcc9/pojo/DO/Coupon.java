package top.ptcc9.pojo.DO;

import lombok.Data;

import java.util.Date;

/**
 * @author: HE LONG CAN
 * @description: 优惠卷实体类
 * @date: 2021-01-18 21:13
 */
@Data
public class Coupon {
    private String couponId;
    /**
     * 所属顾客id
     */
    private String customerId;

    /**
     * 优惠卷类别【获取渠道】
     * 1 - 学习分类知识获取的优惠卷
     * 2 - 开通月卡获取的优惠卷
     * n - 后续可补充
     */
    private Integer type;

    /**
     * 可抵扣金额
     */
    private Double discount;

    /**
     * 获取时间
     */
    private Date createTime;

    /**
     * 过期时间
     */
    private Date expirationTime;

    /**
     * 剩余数量
     */
    private Integer amount;


}
