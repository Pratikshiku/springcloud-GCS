package top.ptcc9.controller;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.web.bind.annotation.*;
import top.ptcc9.annotations.LoginRequired;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.DO.Customer;
import top.ptcc9.pojo.DTO.LoginRegisterCustomerDto;
import top.ptcc9.service.AccountService;
import top.ptcc9.utils.JwtUtil;
import top.ptcc9.pojo.VO.CustomerVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Pratik_shiku
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/doLogin",method = RequestMethod.GET)
    public CommonResult<String> doLogin(LoginRegisterCustomerDto customerDto) {
        return accountService.doLogin(customerDto);
    }

    @RequestMapping(value = "/doSignUp",method = RequestMethod.POST)
    public CommonResult<String> doSignUp(@RequestBody Customer customer) {
        return accountService.doSignUp(customer);
    }


    @LoginRequired
    @RequestMapping(value = "/getCurrentCustomerInfo",method = RequestMethod.GET)
    public CommonResult<CustomerVo> getCurrentCustomerInfo(
            HttpServletRequest request
    ) {
        String token = request.getHeader("token");
        Map<String, Claim> claims = JwtUtil.getClaims(token);
        String id = claims.get("id").asString();
        return accountService.getCurrentCustomerInfo(id);
    }
}
