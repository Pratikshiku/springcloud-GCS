package top.ptcc9.service;

import top.ptcc9.pojo.DTO.CustomerBillDto;

import java.io.InputStream;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2021-01-20 13:59
 */
public interface OrderService {
    /**
     * 上传图片
     * @param inputStream
     * @param id 用户id
     * @return
     */
    String uploadOrderPic(InputStream inputStream,String id);

    /**
     * 顾客下单
     * @param customerBillDto
     * @return
     */
    void customerDoOrder(CustomerBillDto customerBillDto);
}
