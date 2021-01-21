package top.ptcc9.controller;

import org.apache.catalina.core.ApplicationPart;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.DTO.CustomerBillDto;
import top.ptcc9.pojo.VO.CustomerVo;
import top.ptcc9.service.CustomerProfileFeignService;
import top.ptcc9.service.OrderService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: HE LONG CAN
 * @description: 订单相关操作
 * @date: 2021-01-20 13:58
 */
@RestController
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    CustomerProfileFeignService customerProfileFeignService;

    /**
     * 顾客上传图片
     * @param request
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/uploadOrderPic",method = RequestMethod.POST,consumes = "multipart/form-data")
    public CommonResult<String> uploadOrderPic(HttpServletRequest request,String customerId) {
        String finalPath = null;
        try{
            //获取小程序端传递的二进制图片
            ApplicationPart image = (ApplicationPart) request.getPart("file");
            //从二进制中获取流
            InputStream in = image.getInputStream();
            //上传图片到OSS并得到返回的访问链接
            finalPath = orderService.uploadOrderPic(in,customerId + "/");
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
        return finalPath != null ?
                new CommonResult<>(CommonResult.State.SUCCESS_UPLOAD,finalPath)
                :
                new CommonResult<>(CommonResult.State.ERROR_UPLOAD);
    }


    /**
     * 顾客下单
     * @param customerBillDto
     * @return
     */
    @RequestMapping(value = "/customerDoOrder",method = RequestMethod.POST)
    public CommonResult<Boolean> customerDoOrder(@RequestBody CustomerBillDto customerBillDto) {
        //微服务间调用查询余额
        CommonResult<CustomerVo> customer = customerProfileFeignService.getCustomerInfoById(customerBillDto.getCustomerId());
        //余额不足判断
        if (customer.getData().getBalance() < customerBillDto.getFinalPrice()) {
            //出现余额不足异常
            return new CommonResult<>(CommonResult.State.ERROR_CUSTOMER_ORDER_INSUFFICIENT_BALANCE);
        }else {
            //todo 用户金额减少
            try{
                orderService.customerDoOrder(customerBillDto);
                //未出现异常则返回成功
                return new CommonResult<>(CommonResult.State.SUCCESS_CUSTOMER_ORDER);
            }catch (RuntimeException e){
                return new CommonResult<>(CommonResult.State.ERROR_INSERT);
            }
        }
    }
}
