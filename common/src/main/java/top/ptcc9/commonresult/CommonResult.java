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

    public CommonResult() {

    }

    public CommonResult(CommonResult.State state) {
        this(state,null);
    }

    public CommonResult(CommonResult.State state, T data) {
        this.code = state.code;
        this.message = state.message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public enum State {
        /**
         * 状态码
         */
        SUCCESS_QUERY(200,"query success"),
        SUCCESS_UPDATE(201,"update success"),
        SUCCESS_INSERT(202,"insert success"),
        SUCCESS_DELETE(203,"delete success"),
        SUCCESS_LOGIN(204,"login success"),
        SUCCESS_SIGN_UP(205,"sign up success"),

        NO_TOKEN(409,"no token"),
        EXPIRED_TOKEN(410,"token expired"),
        ERROR_NO_AUTHORITY(401,"no authority"),
        ERROR_LOGIN(402,"fail login"),
        ERROR_TIMEOUT(403,"timeout"),
        ERROR_QUERY_NON(404,"no match rows"),
        ERROR_UPDATE(405,"fail update"),
        ERROR_INSERT(406,"fail insert"),
        ERROR_DELETE(407,"fail delete"),
        ERROR_SIGN_UP(408,"fail sign up");
        ;

        private Integer code;
        private String message;

        State(Integer code,String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
