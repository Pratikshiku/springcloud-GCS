package top.ptcc9.pojo.DTO;

import lombok.Data;
import top.ptcc9.pojo.DO.Picture;

import java.util.List;

/**
 * @author: HE LONG CAN
 * @description: 顾客下单
 * @date: 2021-01-21 21:36
 */
@Data
public class CustomerBillDto {
    /**
     * 顾客id
     */
    private String customerId;
    /**
     * 地址id
     */
    private String addressId;
    /**
     * 预估重量
     */
    private Integer preWeight;
    /**
     * 总价
     */
    private Double totalPrice;
    /**
     * 优惠金额
     */
    private Double discount;
    /**
     * 最终价格
     */
    private Double finalPrice;
    /**
     * 优惠卷id
     */
    private String couponId;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 订单备注
     */
    private String remark;
    /**
     * 用户上传的图片链接们
     */
    private List<String> paths;
}
