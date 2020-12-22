package top.ptcc9.controller;


import org.springframework.web.bind.annotation.*;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.DTO.WeChatLoginDto;
import top.ptcc9.pojo.VO.CustomerVo;
import top.ptcc9.service.AccountService;

import javax.annotation.Resource;

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
     * res.code ==> openId
     * @param weChatLoginDto
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public CommonResult<CustomerVo> doLogin(@RequestBody WeChatLoginDto weChatLoginDto) {
        CustomerVo customerVo = accountService.doLogin(weChatLoginDto);
        return new CommonResult<>(CommonResult.State.SUCCESS_LOGIN,customerVo);
    }
}
