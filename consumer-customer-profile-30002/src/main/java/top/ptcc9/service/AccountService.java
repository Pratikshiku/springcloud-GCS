package top.ptcc9.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.DO.Customer;
import top.ptcc9.pojo.DTO.LoginRegisterCustomerDto;
import top.ptcc9.pojo.VO.CustomerVo;

/**
 * @Author: Pratikshiku
 * @Description:
 * @Date: 2020-11-17 12:08
 */
@Component
@FeignClient(value = "${provider.name}")
public interface AccountService {

    /**
     * 消费者 调用服务提供者doLogin
     * @param customerDto
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    CommonResult<String> doLogin(@RequestBody LoginRegisterCustomerDto customerDto);


    /**
     * 调用服务提供者 注册
     * @param customer
     * @return
     */
    @RequestMapping(value = "/doSignUp",method = RequestMethod.POST)
    CommonResult<String> doSignUp(@RequestBody Customer customer);


    /**
     * 调用服务提供者  获取当前登录用户的详细信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCurrentCustomerInfo",method = RequestMethod.GET)
    CommonResult<CustomerVo> getCurrentCustomerInfo(@RequestParam("id") String id);
}
