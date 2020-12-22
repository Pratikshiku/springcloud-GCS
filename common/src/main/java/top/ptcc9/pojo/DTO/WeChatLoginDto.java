package top.ptcc9.pojo.DTO;

import lombok.Data;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-29 20:35
 */
@Data
public class WeChatLoginDto {
    private String code;
    private final String APP_ID = "wx7f77f43127f66c3c";
    private final String APP_SECRET = "1adeb83645027324fdc17c56bc794585";
}
