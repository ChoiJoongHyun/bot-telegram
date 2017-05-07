package com.joonghyun.error;

/**
 * Created by joonghyun on 2017. 5. 5..
 */
public class UserHandlerException extends RuntimeException {
    private Code code;

    private String addMsg;

    public UserHandlerException(Code code) {
        super();
        this.code = code;
    }

    public UserHandlerException(Code code, String addMsg) {
        super();
        this.code = code;
        this.addMsg = addMsg;
    }

    public Code getCode() {
        return code;
    }

    public String getAddMsg() {
        return addMsg;
    }
}
