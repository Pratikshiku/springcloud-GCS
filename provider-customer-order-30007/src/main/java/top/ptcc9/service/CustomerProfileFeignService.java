package top.ptcc9.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.VO.CustomerVo;

/**
 * @author: HE LONG CAN
 * @description: 一个服务间调用的service
 * @date: 2021-01-21 23:45
 */
@FeignClient(value = "provider-customer-profile")
public interface CustomerProfileFeignService {
    /**
     * 远程调用customer-profile-30001中的已有服务
     * @param openId
     * @return
     */
    @RequestMapping(value = "/getCustomerInfoById",method = RequestMethod.GET)
    CommonResult<CustomerVo> getCustomerInfoById(@RequestParam("openId") String openId);
}
