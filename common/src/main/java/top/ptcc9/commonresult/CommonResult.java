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
        ERROR_QUERY_NON_REDIS(204,"province not available"),
        ERROR_QUERY_SIZE_ZERO_REDIS(205,"province available but no area"),
        SUCCESS_INSERT(206,"insert success"),
        ERROR_INSERT(207,"insert error"),
        SUCCESS_UPDATE(208,"update success"),
        ERROR_UPDATE(209,"update failed"),
        SUCCESS_DELETE(210,"delete success"),
        ERROR_DELETE(211,"delete failed");

        private Integer code;
        private String message;

        State(Integer code,String message) {
            this.code = code;
            this.message = message;
        }
    }
}
