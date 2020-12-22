package top.ptcc9.controller;


import cn.hutool.crypto.SecureUtil;
import org.springframework.web.bind.annotation.*;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.DO.Customer;
import top.ptcc9.pojo.DTO.LoginRegisterCustomerDto;
import top.ptcc9.pojo.DTO.WeChatLoginDto;
import top.ptcc9.pojo.VO.TokenVo;
import top.ptcc9.service.AccountService;
import top.ptcc9.utils.JwtUtil;
import top.ptcc9.pojo.VO.CustomerVo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-24 12:38
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    /**
     * 用户输入的密码进行加密
     * po => vo
     * 签发token
     * @param weChatLoginDto
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public CommonResult<TokenVo> doLogin(@RequestBody WeChatLoginDto weChatLoginDto) {
        String openId = accountService.getOpenId(weChatLoginDto);
        return new CommonResult<>(CommonResult.State.ERROR_LOGIN);
    }
}
