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
    private final String APP_ID = "wx855a4cd1717292bd";
    private final String APP_SECRET = "645156b56330646944848f30a09468ce";
}
