package top.ptcc9.service;

import org.springframework.stereotype.Component;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.DO.Customer;
import top.ptcc9.pojo.DTO.WeChatLoginDto;
import top.ptcc9.pojo.VO.CustomerVo;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-24 12:40
 */
@Component
public interface AccountService {
    /**
     * 通过 code 取得 openId
     * @param weChatLoginDto
     * @return
     */
     String getOpenId(WeChatLoginDto weChatLoginDto);


    /**
     * 小程序登录
     * @param weChatLoginDto
     * @return
     */
    CustomerVo doLogin(WeChatLoginDto weChatLoginDto);
}
