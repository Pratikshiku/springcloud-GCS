package top.ptcc9.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import top.ptcc9.mapper.CustomerMapper;
import top.ptcc9.pojo.DO.Customer;
import top.ptcc9.pojo.DTO.WeChatLoginDto;
import top.ptcc9.service.AccountService;
import top.ptcc9.pojo.VO.CustomerVo;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-24 12:43
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public CustomerVo doLogin(WeChatLoginDto weChatLoginDto) {
        Customer customer = null;
        String openId = getOpenId(weChatLoginDto);
        if (!openId.isEmpty()) {
            customer = customerMapper.selectOne(
                    new QueryWrapper<Customer>()
                            .eq("open_id", openId)
            );
            if (customer == null) {
                customer = new Customer();
                customer
                        .setOpenId(openId)
                        .setCreateTime(DateTime.now());
                customerMapper.insert(customer);
            }
        }
        return customerToVo(customer);
    }

    @Override
    public CustomerVo getCustomerInfoById(String openid) {
        Customer customer = null;
        customer = customerMapper.selectOne(new QueryWrapper<Customer>().eq("open_id", openid));
        if (customer != null) {
            return customerToVo(customer);
        }
        return null;
    }

    private CustomerVo customerToVo(Customer customer) {
        CustomerVo customerVo = new CustomerVo();
        //拷贝属性
        BeanUtil.copyProperties(customer,customerVo,"vipExpiration","createTime");
        //date ==> string
        String createTime = DateUtil.format(customer.getCreateTime(), "yyyy-MM-dd hh:mm:ss");
        customerVo.setCreateTime(createTime);
        //
        DateTime now = DateTime.now();
        if (customer.getVipExpiration() != null) {
            long between = DateUtil.between(now, customer.getVipExpiration(), DateUnit.DAY);
            if (now.isAfter(customer.getVipExpiration())) {
                customerVo.setExpired(false);
            }
            customerVo.setVipExpiration(between);
        }
        return customerVo;
    }

    private String getOpenId(WeChatLoginDto weChatLoginDto) {
        StringBuilder requestUrl = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        requestUrl.append("appid=");
        requestUrl.append(weChatLoginDto.getAPP_ID());
        requestUrl.append("&secret=");
        requestUrl.append(weChatLoginDto.getAPP_SECRET());
        requestUrl.append("&js_code=");
        requestUrl.append(weChatLoginDto.getCode());
        requestUrl.append("&grant_type=authorization_code");
        String openid = "";
        try{
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(requestUrl.toString());
            HttpResponse response = client.execute(get);
            HttpEntity result = response.getEntity();
            String content = EntityUtils.toString(result);
            JSONObject jsonObject = JSONUtil.parseObj(content);
            Map<String, Object> map = BeanUtil.beanToMap(jsonObject);
            openid = (String) map.get("openid");
        }catch (Exception e){
            e.printStackTrace();
        }
        return openid;
    }
}
