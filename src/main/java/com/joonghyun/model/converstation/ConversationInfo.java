package com.joonghyun.model.converstation;

/**
 * Created by joonghyun on 2017. 5. 4..
 */
public class ConversationInfo {
    private String function;    //호출 메소드명

    private String msg; //메세지

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
