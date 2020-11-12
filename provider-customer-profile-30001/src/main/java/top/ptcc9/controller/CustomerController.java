package top.ptcc9.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    /**
     * this is a example method
     * @return
     */
    @RequestMapping(value = "/getString",method = RequestMethod.GET)
    public String getString() {
        String testString = "this is a test message for success";
        boolean flag = true;
        return flag ? testString : "error message";
    }
}
