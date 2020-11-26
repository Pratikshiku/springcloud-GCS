package top.ptcc9.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.po.Customer;
import top.ptcc9.vo.CustomerVo;

/**
 * @Author: Pratikshiku
 * @Description:
 * @Date: 2020-11-17 12:08
 */
@Component
@FeignClient(value = "${provider.name}")
public interface CustomerService {

    /**
     * 消费者 调用服务提供者doLogin
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.GET)
    CommonResult<CustomerVo> doLogin(@RequestParam("phone") String phone,@RequestParam("password") String password);


    /**
     * 调用服务提供者 注册
     * @param customer
     * @return
     */
    @RequestMapping(value = "/doSignUp",method = RequestMethod.POST)
    CommonResult<String> doSignUp(@RequestBody Customer customer);
}
