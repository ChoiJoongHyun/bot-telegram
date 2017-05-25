package com.joonghyun.function;

import com.joonghyun.anotation.Command;
import com.joonghyun.anotation.Function;
import com.joonghyun.helper.RedisHelper;
import com.joonghyun.model.request.MessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cjh on 2017. 5. 25..
 */
@Function
public class LunchFunction {

    private static final Logger log = LoggerFactory.getLogger(ConferenceFunction.class);

    @Autowired
    private RedisHelper redisHelper;

    public class Message {

    }

    @Command(msg="점심메뉴", function = "lunch", parent = "wakeup")
    public String lunch(MessageRequest messageRequest) {
        log.debug("lunch start messageRequest : {}", messageRequest.toString());
        redisHelper.delete(messageRequest.getRoomKey());
        return "TODO 현재 구현중... 배고프다."
                + "\n\nBye Bye ~";
    }
}
