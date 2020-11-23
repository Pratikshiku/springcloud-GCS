package top.ptcc9.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.service.CustomerService;

import javax.annotation.Resource;

/**
 * @author Pratik_shiku
 */
@RestController
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @RequestMapping("/consumer/customer/getString")
    public CommonResult<String> getString() {
        return customerService.getString();
    }


}
