package top.ptcc9.commonresult;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;

@Data
public class ConsumerResult<T> {
    private Integer code;
    private String message;
    private T data;

    public ConsumerResult(ProviderResult<T> result) {
        BeanUtil.copyProperties(this,result,false);
    }
}
