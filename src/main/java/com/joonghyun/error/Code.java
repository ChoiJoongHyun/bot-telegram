package com.joonghyun.error;

/**
 * Created by joonghyun on 2017. 5. 5..
 */
public enum Code {
    NO_EXIST_COMMAND("2001", "잘못된 접근 입니다.")
    , NO_ROOM_KEY("2002", "잘못된 방 입니다.")
    ;

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    Code(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
