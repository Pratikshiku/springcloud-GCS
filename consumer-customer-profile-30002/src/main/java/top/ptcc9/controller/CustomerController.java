package top.ptcc9.controller;

import org.springframework.web.bind.annotation.*;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.po.Customer;
import top.ptcc9.service.CustomerService;
import top.ptcc9.vo.CustomerVo;

import javax.annotation.Resource;

/**
 * @author Pratik_shiku
 */
@RestController
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @RequestMapping(value = "/doLogin",method = RequestMethod.GET)
    public CommonResult<CustomerVo> doLogin(Customer customer) {
        return customerService.doLogin(customer.getPhone(),customer.getPassword());
    }

    @RequestMapping(value = "/doSignUp",method = RequestMethod.POST)
    public CommonResult<String> doSignUp(@RequestBody Customer customer) {
        return customerService.doSignUp(customer);
    }
}
