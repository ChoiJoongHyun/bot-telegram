package com.joonghyun.dispatch;

import com.joonghyun.anotation.Command;
import com.joonghyun.anotation.Function;
import com.joonghyun.helper.RedisHelper;
import org.apache.commons.lang3.text.WordUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Component
public class MessageDispatch {
    private static final Logger logger = LoggerFactory.getLogger(MessageDispatch.class);

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private BeanFactory beanFactory;

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
                    commanderMap.put(command.function(), new Commander(WordUtils.uncapitalize(clazz.getSimpleName()), method, beanFactory));
                }
            }
        }
    }

    private final static Map<String, Commander> commanderMap = new HashMap<>();

    private static class Commander {
        private String name;
        private BeanFactory beanFactory;
        private Method method;

        Commander(String name, Method method, BeanFactory beanFactory){
            this.name = name;
            this.method = method;
            this.beanFactory = beanFactory;
        }

        String execute(String msg) throws InvocationTargetException, IllegalAccessException {
            return (String) this.method.invoke(this.beanFactory.getBean(this.name), msg);
        }
        String execute() throws InvocationTargetException, IllegalAccessException {
            return (String) this.method.invoke(this.beanFactory.getBean(this.name));
        }
    }

   




    public String message(Long romeKey, String msg) throws InvocationTargetException, IllegalAccessException {
        logger.debug("message start");

        String resultMsg;

        if("#wakeup!".equals(msg)) {
            return commanderMap.get("wakeup").execute();
        }

        resultMsg = commanderMap.get("conference").execute(msg);


        return resultMsg;
    }



}
