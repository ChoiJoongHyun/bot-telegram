package com.joonghyun.function;

import com.joonghyun.anotation.Command;
import org.springframework.stereotype.Component;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Component
public class WakeupFunction {

    @Command(value = "#wakeup!", function = "wakeup")
    public String wakeup() {

        return "wakeup";
    }
}
