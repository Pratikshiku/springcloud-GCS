package top.ptcc9.controller;

import org.springframework.web.bind.annotation.*;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.DTO.WeChatLoginDto;
import top.ptcc9.pojo.VO.TokenVo;
import top.ptcc9.service.AccountService;
import javax.annotation.Resource;

/**
 * @author Pratik_shiku
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/doLogin",method = RequestMethod.GET)
    public CommonResult<TokenVo> doLogin(WeChatLoginDto weChatLoginDto) {
        return accountService.doLogin(weChatLoginDto);
    }
}
