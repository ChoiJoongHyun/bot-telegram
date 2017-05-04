package com.joonghyun.function;

import com.joonghyun.anotation.Command;
import com.joonghyun.anotation.Function;
import com.joonghyun.model.request.MessageRequest;
import org.springframework.stereotype.Component;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Function
public class WakeupFunction {

    public class Message {
        public static final String WAKEUP = "wakeup";
    }



    @Command(msg = "#wakeup!", function = "wakeup")
    public String wakeup(MessageRequest messageRequest) {

        return Message.WAKEUP;
    }
}
