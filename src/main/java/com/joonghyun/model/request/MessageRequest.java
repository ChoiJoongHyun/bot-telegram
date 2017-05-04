package com.joonghyun.model.request;

/**
 * Created by joonghyun on 2017. 5. 4..
 */
public class MessageRequest {
    private String romeKey;
    private String msg;

    public MessageRequest(String romeKey, String msg) {
        this.romeKey = romeKey;
        this.msg = msg;
    }

    public String getRomeKey() {
        return romeKey;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "romeKey='" + romeKey + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
