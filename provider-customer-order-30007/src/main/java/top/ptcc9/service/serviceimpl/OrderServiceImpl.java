package top.ptcc9.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ptcc9.mapper.OrderMapper;
import top.ptcc9.mapper.PictureMapper;
import top.ptcc9.pojo.DO.Bill;
import top.ptcc9.pojo.DO.Picture;
import top.ptcc9.pojo.DTO.CustomerBillDto;
import top.ptcc9.service.OrderService;
import top.ptcc9.utils.OssUtil;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2021-01-20 13:59
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    public OrderMapper orderMapper;
    @Resource
    public PictureMapper pictureMapper;

    /**
     * 顾客上传图片
     * @param inputStream
     * @param path
     * @return
     */
    @Override
    public String uploadOrderPic(InputStream inputStream, String path) {
        return OssUtil.uploadFile(inputStream, path);
    }

    /**
     * 顾客下单
     * @param customerBillDto
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void customerDoOrder(CustomerBillDto customerBillDto) {
        //dto => do
        Bill bill = customerBillDtoToDo(customerBillDto);
        //如果插入不成功则抛出异常使其回滚
        if (orderMapper.insert(bill) == 0) {
            throw new RuntimeException();
        }
        //获取到所有图片链接
        List<String> paths = customerBillDto.getPaths();
        paths.forEach(path -> {
            Picture picture = pathToDo(path, bill.getBillId());
            if (pictureMapper.insert(picture) == 0) {
                throw new RuntimeException();
            }
        });
    }

    /**
     * customerBillDto => Order
     * 用于顾客下单场景
     * @param customerBillDto
     * @return
     */
    private Bill customerBillDtoToDo(CustomerBillDto customerBillDto) {
        Bill bill = new Bill();
        /**
         * 拷贝属性
         */
        BeanUtil.copyProperties(customerBillDto, bill);
        /**
         * 生成 32 位 id
         */
        bill.setBillId(IdUtil.simpleUUID());
        /**
         * 生成当前时间
         */
        bill.setCreateTime(DateTime.now());
        return bill;
    }


    /**
     * 顾客上传的图片链接转换成Picture
     * @param orderId
     * @return
     */
    private Picture pathToDo(String path,String orderId) {
        return new Picture()
                .setPicId(IdUtil.simpleUUID())
                .setPath(path)
                .setOrderId(orderId)
                //uploader = 0 代表图片是顾客上传的
                .setUploader(0);
    }
}
