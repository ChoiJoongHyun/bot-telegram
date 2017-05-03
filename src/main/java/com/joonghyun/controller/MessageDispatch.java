package com.joonghyun.controller;

import com.joonghyun.anotation.Command;
import com.joonghyun.helper.RedisHelper;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Component
public class MessageDispatch {
    private static final Logger logger = LoggerFactory.getLogger(MessageDispatch.class);

    @Autowired
    private RedisHelper redisHelper;

    @PostConstruct
    public void init(){
        logger.info("command start");
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.joonghyun.function")).setScanners(
                        new TypeAnnotationsScanner(), new SubTypesScanner()));
        for(Class<?> clazz : reflections.getTypesAnnotatedWith(com.joonghyun.anotation.Function.class)) {
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(Command.class)) {
                    Command command = method.getAnnotation(Command.class);
                    logger.info("command : {}", command.function());
                    
                }
            }
        }
    }


    public String message(Long romeKey, String msg) {

        if("#wakeup!".equals(msg)) {
            redisHelper.delete(String.valueOf(romeKey));
            redisHelper.push(String.valueOf(romeKey) , msg);

        }

        return msg;
    }



}
