package com.joonghyun.aop;

import com.joonghyun.anotation.Command;
import com.joonghyun.error.GeneralCode;
import com.joonghyun.error.UserHandlerException;
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
        Command command = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Command.class);

        if(command == null) {
            throw new UserHandlerException(GeneralCode.NO_EXIST_COMMAND);
        }

        MessageRequest mr = null;
        for(Object obj : joinPoint.getArgs()) {
            if(obj instanceof MessageRequest) {
                mr = new MessageRequest(((MessageRequest) obj).getRoomKey(), ((MessageRequest) obj).getMsg());
            }
        }

        if(mr == null) {
            throw new UserHandlerException(GeneralCode.NO_ROOM_KEY);
        }

        if(mr.getRoomKey() == null || mr.getRoomKey().equals("")) {
            throw new UserHandlerException(GeneralCode.NO_ROOM_KEY);
        }

        if(command.function().equals("#wakeup!")) {
            redisHelper.delete(String.valueOf(mr.getRoomKey()));
        }

        //redis push
        ConversationInfo conversationInfo = ConversationUtils.paramToObject(command.function(),mr.getMsg());
        redisHelper.push(String.valueOf(mr.getRoomKey()), ConversationUtils.objectToString(conversationInfo));

        try {
            return joinPoint.proceed();
        } catch (UserHandlerException ue) {

            System.out.println("cjh userexception");
            redisHelper.pop(String.valueOf(mr.getRoomKey()));
            return ue.getCode() + " : " + ue.getMessage() + "(" + ue.getAddMsg() + ")";
        } catch (Exception e) {
            System.out.println("cjh exception");
            redisHelper.pop(String.valueOf(mr.getRoomKey()));
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
