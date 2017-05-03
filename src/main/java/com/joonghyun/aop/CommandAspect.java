package com.joonghyun.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Component
@Aspect
public class CommandAspect {

    private static final Logger log = LoggerFactory.getLogger(CommandAspect.class);

    @Around("@annotation(com.joonghyun.anotation.Command)")
    public Object redisCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("redisCheck start go go~");

        Object object = joinPoint.proceed();

        log.debug("redisCheck end");
        return object;
    }

}
