package com.joonghyun.error;

/**
 * Created by joonghyun on 2017. 5. 5..
 */
public class UserHandlerException extends RuntimeException {
    private Code code;

    public UserHandlerException(Code code) {
        super();
        this.code = code;
    }

    public Code getCode() {
        return code;
    }
}
