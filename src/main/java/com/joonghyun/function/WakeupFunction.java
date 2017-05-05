package com.joonghyun.function;

import com.joonghyun.anotation.Command;
import com.joonghyun.anotation.Function;
import com.joonghyun.model.request.MessageRequest;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Function
public class WakeupFunction {

    public class Message {
        public static final String WAKEUP = "무엇을 도와 드릴까요?" +
                "\n'회의실' : 회의실 예약 및 취소 상황을 보여줍니다." +
                "\n* 30초간 입력이 없을시 저는 사라집니다.";
    }

    @Command(msg = "#wakeup!", function = "wakeup")
    public String wakeup(MessageRequest messageRequest) {
        return Message.WAKEUP;
    }
}
