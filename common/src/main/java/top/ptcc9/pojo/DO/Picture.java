package top.ptcc9.pojo.DO;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: HE LONG CAN
 * @description: 订单中的图片
 * @date: 2021-01-18 21:04
 */
@Data
@Accessors(chain = true)
public class Picture {
    private String picId;
    private String orderId;
    private String path;
}
