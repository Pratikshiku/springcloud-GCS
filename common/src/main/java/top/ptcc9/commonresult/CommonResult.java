package top.ptcc9.commonresult;

import lombok.Data;

/**
 * @Author: Pratikshiku
 * @Description: a result template
 * @Date: 2020-11-17 12:02
 */
@Data
public class CommonResult<T> {
    private Integer stateCode;
    private String message;
    private T data;

    public CommonResult() {

    }

    public CommonResult(CommonResult.CODE code) {
        this(code,null);
    }

    public CommonResult(CommonResult.CODE code, T data) {
        this.stateCode = code.getCode();
        this.message = code.getMessage();
        this.data = data;
    }

    public enum CODE {
        /**
         * 状态码
         */
        SUCCESS_QUERY(200,"query success"),
        SUCCESS_UPDATE(201,"update success"),
        SUCCESS_INSERT(202,"insert success"),
        SUCCESS_DELETE(203,"delete success"),

        ERROR_NO_AUTHORITY(401,"no authority"),
        ERROR_LOGIN(402,"fail login"),
        ERROR_TIMEOUT(403,"timeout"),
        ERROR_QUERY_NON(404,"no match rows"),
        ERROR_UPDATE(405,"fail update"),
        ERROR_INSERT(406,"fail insert"),
        ERROR_DELETE(407,"fail delete")
        ;

        private Integer code;
        private String message;

        CODE(Integer code,String message) {
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
