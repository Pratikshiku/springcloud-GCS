package top.ptcc9.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.ptcc9.commonresult.CommonResult;

@RestController
public class CustomerController {
    /**
     * this is an example method
     * @return
     */
    @RequestMapping(value = "/customer/getString",method = RequestMethod.GET)
    public CommonResult<String> getString() {
        String testString = "this is a test message for success";
        return new CommonResult<>(CommonResult.CODE.SUCCESS_QUERY,testString);
    }
}
