package top.ptcc9.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.ptcc9.commonresult.ConsumerResult;
import top.ptcc9.commonresult.ProviderResult;

import javax.annotation.Resource;

@RestController
public class CustomerController {
    @Value("${provider.url}")
    private String url;

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/getString",method = RequestMethod.GET)
    public ConsumerResult<String> getString() {
        return new ConsumerResult<String>(restTemplate.getForObject(url + "/getString", ProviderResult.class));
    }
}
