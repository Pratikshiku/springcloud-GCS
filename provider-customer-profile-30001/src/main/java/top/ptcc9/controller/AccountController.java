package top.ptcc9.controller;


import cn.hutool.crypto.SecureUtil;
import org.springframework.web.bind.annotation.*;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.DO.Customer;
import top.ptcc9.pojo.DTO.LoginRegisterCustomerDto;
import top.ptcc9.service.AccountService;
import top.ptcc9.utils.JwtUtil;
import top.ptcc9.pojo.VO.CustomerVo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-24 12:38
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    /**
     * 用户输入的密码进行加密
     * po => vo
     * 签发token
     * @param customerDto
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public CommonResult<String> doLogin(@RequestBody LoginRegisterCustomerDto customerDto) {
        //query(phone) => customer(id,password)
        Customer customerByPhone = accountService.getCustomerByPhone(customerDto.getPhone());
        //加密
        String md5Password = SecureUtil.md5().digestHex(customerDto.getPassword());
        if (customerByPhone != null && md5Password.equals(customerByPhone.getPassword())) {
            //生成token
            Map<String, String> map = new HashMap<>(1);
            map.put("id",customerByPhone.getId());
            String token = JwtUtil.create(map);
            return new CommonResult<>(CommonResult.State.SUCCESS_LOGIN,token);
        }else {
            return new CommonResult<>(CommonResult.State.ERROR_LOGIN);
        }
    }

    /**
     * 执行 insert 返回成功或失败状态码
     * @param customer
     * @return
     */
    @RequestMapping(value = "/doSignUp",method = RequestMethod.POST)
    public CommonResult<String> doSignUp(@RequestBody Customer customer) {
        if (accountService.insertCustomer(customer) == 1) {
            return new CommonResult<>(CommonResult.State.SUCCESS_SIGN_UP);
        }else {
            return new CommonResult<>(CommonResult.State.ERROR_SIGN_UP);
        }
    }


    /**
     * id 查询用户
     * po => vo
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCurrentCustomerInfo",method = RequestMethod.GET)
    public CommonResult<CustomerVo> getCurrentCustomerInfo(
            String id
    ) {
        Customer currentCustomerInfo = accountService.getCurrentCustomerInfo(id);
        if (currentCustomerInfo != null) {
            CustomerVo customerVo = accountService.customerToVo(currentCustomerInfo);
            return new CommonResult<>(CommonResult.State.SUCCESS_QUERY,customerVo);
        }else {
            return new CommonResult<>(CommonResult.State.ERROR_QUERY_NON);
        }
    }
}
