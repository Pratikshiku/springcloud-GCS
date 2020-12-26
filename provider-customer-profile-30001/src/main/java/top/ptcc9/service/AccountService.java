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
     * 小程序登录
     * @param weChatLoginDto
     * @return CustomerVo
     */
    CustomerVo doLogin(WeChatLoginDto weChatLoginDto);

    /**
     * 通过id获取个人信息通过id获取个人信息
     * @param openid
     * @return CustomerVo
     */
    CustomerVo getCustomerInfoById(String openid);
}
