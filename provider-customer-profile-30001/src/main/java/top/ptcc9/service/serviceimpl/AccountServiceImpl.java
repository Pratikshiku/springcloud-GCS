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
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import top.ptcc9.commonresult.CommonResult;
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
    public String getOpenId(WeChatLoginDto weChatLoginDto) {
        StringBuilder requestUrl = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        requestUrl.append("appid=");
        requestUrl.append(weChatLoginDto.getAPP_ID());
        requestUrl.append("&secret=");
        requestUrl.append(weChatLoginDto.getAPP_SECRET());
        requestUrl.append("&js_code=");
        requestUrl.append(weChatLoginDto.getCode());
        requestUrl.append("&grant_type=authorization_code");
        try{
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(requestUrl.toString());
            HttpResponse response = client.execute(get);
            HttpEntity result = response.getEntity();
            String content = EntityUtils.toString(result);
            JSONObject jsonObject = JSONUtil.parseObj(content);
            Map<String, Object> map = BeanUtil.beanToMap(jsonObject);
            for (Map.Entry<String, Object> item : map.entrySet()) {
                System.out.println("item.getKey() = " + item.getKey());
                System.out.println("item.getValue() = " + item.getValue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public CommonResult<CustomerVo> doLogin(String openId) {
        return null;
    }
}
