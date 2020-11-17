package top.ptcc9.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.ptcc9.commonresult.CommonResult;

/**
 * @Author: Pratikshiku
 * @Description:
 * @Date: 2020-11-17 12:08
 */
@Component
@FeignClient(value = "${provider.name}")
public interface CustomerService {
    @RequestMapping(value = "/customer/getString",method = RequestMethod.GET)
    CommonResult<String> getString();
}
