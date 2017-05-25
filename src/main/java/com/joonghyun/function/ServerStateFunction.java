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
public class ServerStateFunction {

    private static final Logger log = LoggerFactory.getLogger(ConferenceFunction.class);

    @Autowired
    private RedisHelper redisHelper;

    public class Message {

    }

    @Command(msg="서버현황", function = "serverState", parent = "wakeup")
    public String serverState(MessageRequest messageRequest) {
        log.debug("game start messageRequest : {}", messageRequest.toString());
        redisHelper.delete(messageRequest.getRoomKey());
        return "TODO 현재 구현중... 아이파킹 안정화 !!"
                + "\n\nBye Bye ~";
    }
}
