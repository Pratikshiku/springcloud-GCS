package top.ptcc9.controller;

import org.springframework.web.bind.annotation.*;
import top.ptcc9.annotations.LoginRequired;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.po.Customer;
import top.ptcc9.service.AccountService;
import top.ptcc9.vo.CustomerVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Pratik_shiku
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/doLogin",method = RequestMethod.GET)
    public CommonResult<CustomerVo> doLogin(Customer customer) {
        return accountService.doLogin(customer.getPhone(),customer.getPassword());
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
        return accountService.getCurrentCustomerInfo(request.getHeader("token"));
    }
}
