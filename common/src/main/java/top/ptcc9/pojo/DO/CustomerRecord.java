package top.ptcc9.pojo.DO;

import lombok.Data;

import java.util.Date;

/**
 * @author: HE LONG CAN
 * @description: 用户充值记录
 * @date: 2021-01-19 19:11
 */
@Data
public class CustomerRecord {
    private String recordId;
    /**
     * 顾客id
     */
    private String customerId;
    /**
     * 操作类型
     * 1 - 月卡充值
     * 2 - 次卡充值
     * 3 - 学习分类奖励
     * 4 - 推荐奖励
     */
    private int type;

    /**
     * 若 type = 1 则 amount 以月数为单位
     *          其他         以次卡个数为单位
     */
    private int amount;

    /**
     * 此次操作消费的金额
     */
    private Double price;

    /**
     * 支付方式
     * 1 - 微信支付
     */
    private int paymentType;

    private Date createTime;
}
