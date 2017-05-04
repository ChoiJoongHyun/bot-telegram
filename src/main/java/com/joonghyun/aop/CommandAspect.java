package com.joonghyun.aop;

import com.joonghyun.dispatch.MessageDispatch;
import com.joonghyun.helper.RedisHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.plugin2.message.Message;

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



        Object object = joinPoint.proceed();

        log.info("redisCheck end");
        return object;
    }

}
