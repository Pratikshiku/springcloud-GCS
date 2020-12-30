package top.ptcc9.commonresult;

import lombok.Data;

/**
 * @Author: Pratikshiku
 * @Description: a result template
 * @Date: 2020-11-17 12:02
 */
@Data
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(CommonResult.State state) {
        this(state,null);
    }

    public CommonResult(CommonResult.State state, T data) {
        this.code = state.code;
        this.message = state.message;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public enum State {
        /**
         * 状态码
         */
        SUCCESS_LOGIN(200,"login success"),
        ERROR_LOGIN(201,"failed login"),
        SUCCESS_QUERY(202,"query success"),
        ERROR_QUERY_NON(203,"no match rows"),

        ;

        private Integer code;
        private String message;

        State(Integer code,String message) {
            this.code = code;
            this.message = message;
        }
    }
}
