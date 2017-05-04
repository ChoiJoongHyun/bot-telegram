package com.joonghyun.function;

import com.joonghyun.anotation.Command;
import com.joonghyun.anotation.Function;
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
    public String wakeup() {

        return Message.WAKEUP;
    }
}
