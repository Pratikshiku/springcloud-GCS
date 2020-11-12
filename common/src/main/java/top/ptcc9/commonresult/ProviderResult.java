package top.ptcc9.commonresult;

import lombok.Data;

@Data
public class ProviderResult<T> {
    private Integer stateCode;
    private String message;
    private T data;
    private boolean state;

    public ProviderResult(CODE code) {
        this(code,null,false);
    }

    public ProviderResult(CODE code,T data) {
        this(code,data,true);
    }

    public ProviderResult(CODE code, T data, boolean state) {
        this.stateCode = code.getCode();
        this.message = code.getMessage();
        this.data = data;
        this.state = state;
    }

    public enum CODE {
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
