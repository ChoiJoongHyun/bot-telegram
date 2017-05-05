package com.joonghyun.model.request;

/**
 * Created by joonghyun on 2017. 5. 4..
 */
public class MessageRequest {
    private String roomKey;
    private String msg;

    public MessageRequest(String roomKey, String msg) {
        this.roomKey = roomKey;
        this.msg = msg;
    }

    public String getRoomKey() {
        return roomKey;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "roomKey='" + roomKey + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
