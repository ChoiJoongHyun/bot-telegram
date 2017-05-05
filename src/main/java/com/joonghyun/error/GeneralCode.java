package com.joonghyun.error;

/**
 * Created by joonghyun on 2017. 5. 5..
 */
public enum GeneralCode implements Code{
    NO_EXIST_COMMAND("2001", "잘못된 접근 입니다.")
    , NO_ROOM_KEY("2002", "잘못된 방 입니다.")

    , NO_CONFERENCE_ZONE("3001", "회의실이 존재하지 않습니다.")
    ;

    private String code;
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    GeneralCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
