package com.joonghyun.aop;

import com.joonghyun.anotation.Command;
import com.joonghyun.dispatch.MessageDispatch;
import com.joonghyun.helper.RedisHelper;
import com.joonghyun.model.converstation.ConversationInfo;
import com.joonghyun.model.request.MessageRequest;
import com.joonghyun.utils.ConversationUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Component
@Aspect
public class CommandAspect {

    private static final Logger log = LoggerFactory.getLogger(CommandAspect.class);

    @Autowired
    private RedisHelper redisHelper;

    @Around("@annotation(com.joonghyun.anotation.Command)")
    public Object redisCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("redisCheck start go go~");


        Command command = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Command.class);

        if(command == null) {
            throw new RuntimeException("command is null");
        }

        String romeKey = null;
        for(Object obj : joinPoint.getArgs()) {
            if(obj instanceof MessageRequest) {
                romeKey = ((MessageRequest) obj).getRomeKey();
            }
        }

        if(romeKey == null) {
            throw new RuntimeException("romeKey is null");
        }

        if(command.function().equals("#wakeup!")) {
            redisHelper.delete(String.valueOf(romeKey));
        }

        ConversationInfo conversationInfo = ConversationUtils.paramToObject(command.function());
        redisHelper.push(String.valueOf(romeKey), ConversationUtils.objectToString(conversationInfo));

        Object object = joinPoint.proceed();

        log.info("redisCheck end");
        return object;
    }

}
