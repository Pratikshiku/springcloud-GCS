package top.ptcc9.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.pojo.DTO.AddressDetailDto;
import top.ptcc9.pojo.DTO.UpdateAddressDetailDto;
import top.ptcc9.pojo.VO.AddressDetailVo;
import top.ptcc9.pojo.VO.AddressVo;
import top.ptcc9.service.AddressService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: HE LONG CAN
 * @description: 地址相关操作
 * @date: 2020-11-29 00:20
 */

@RestController
public class AddressController {
    @Resource
    AddressService addressService;

    /**
     * open_id获取address们
     * @param openId
     * @param defaultAddressId
     * @return
     */
    @RequestMapping(value = "/getAddressByOpenId", method = RequestMethod.GET)
    public CommonResult<List<AddressVo>> getAddressByOpenId(String openId, String defaultAddressId) {
        List<AddressVo> addressVoList = addressService.getAddressByOpenId(openId, defaultAddressId);
        return addressVoList.size() != 0 ?
                new CommonResult<>(CommonResult.State.SUCCESS_QUERY, addressVoList) :
                new CommonResult<>(CommonResult.State.ERROR_QUERY_NON);
    }

    /**
     * 查询某地址（县级）的可用区域
     * @param province
     * @return
     */
    @RequestMapping(value = "/getAvailableAreaList", method = RequestMethod.GET)
    public CommonResult<List<Object>> getAvailableAreaList(String province) {
        List<Object> addressList = addressService.getAvailableAreaList(province);
        if (addressList == null) {
            return new CommonResult<>(CommonResult.State.ERROR_QUERY_NON_REDIS);
        } else {
            return addressList.size() > 0 ?
                    new CommonResult<>(CommonResult.State.SUCCESS_QUERY, addressList) :
                    new CommonResult<>(CommonResult.State.ERROR_QUERY_SIZE_ZERO_REDIS);
        }
    }

    /**
     * 查询address详情（单个）
     * @param addressId
     * @param defaultAddressId
     * @return
     */
    @RequestMapping(value = "/getAddressByAddressId", method = RequestMethod.GET)
    public CommonResult<AddressDetailVo> getAddressByAddressId(String addressId, String defaultAddressId) {
        AddressDetailVo addressDetailVo = addressService.getAddressByAddressId(addressId, defaultAddressId);
        return addressDetailVo != null ?
                new CommonResult<>(CommonResult.State.SUCCESS_QUERY,addressDetailVo):
                new CommonResult<>(CommonResult.State.ERROR_QUERY_NON);
    }

    /**
     *
     * @param addressDetailDto
     * @return
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public CommonResult<Boolean> addAddress(@RequestBody AddressDetailDto addressDetailDto) {
        try {
            addressService.addAddress(addressDetailDto);
            return new CommonResult<>(CommonResult.State.SUCCESS_INSERT);
        }catch (RuntimeException e) {
            return new CommonResult<>(CommonResult.State.ERROR_INSERT);
        }
    }

    /**
     * 更新address
     * @param updateAddressDetailDto
     * @return
     */
    @RequestMapping(value = "/updateAddressById", method = RequestMethod.POST)
    public CommonResult<Boolean> updateAddressById(@RequestBody UpdateAddressDetailDto updateAddressDetailDto) {
        try {
            addressService.updateAddressById(updateAddressDetailDto);
            return new CommonResult<>(CommonResult.State.SUCCESS_UPDATE);
        }catch (RuntimeException e) {
            return new CommonResult<>(CommonResult.State.ERROR_UPDATE);
        }
    }

    @RequestMapping(value = "/deleteAddressById", method = RequestMethod.GET)
    public CommonResult<Boolean> deleteAddressById(String addressId, String defaultId) {
        try {
            addressService.deleteAddressById(addressId, defaultId);
            return new CommonResult<>(CommonResult.State.SUCCESS_DELETE);
        }catch (RuntimeException e) {
            return new CommonResult<>(CommonResult.State.ERROR_DELETE);
        }
    }
}
