package top.ptcc9.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.DTO.WeChatLoginDto;

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
     * @param weChatLoginDto
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    CommonResult<TokenVo> doLogin(@RequestBody WeChatLoginDto weChatLoginDto);
}
