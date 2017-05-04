package com.joonghyun.dispatch;

import com.joonghyun.anotation.Command;
import com.joonghyun.helper.RedisHelper;
import com.joonghyun.model.converstation.ConversationInfo;
import com.joonghyun.model.request.MessageRequest;
import com.joonghyun.utils.ConversationUtils;
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
    private static final Logger log = LoggerFactory.getLogger(MessageDispatch.class);

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private BeanFactory beanFactory;

    @PostConstruct
    public void init(){
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.joonghyun.function")).setScanners(
                        new TypeAnnotationsScanner(), new SubTypesScanner()));
        for(Class<?> clazz : reflections.getTypesAnnotatedWith(com.joonghyun.anotation.Function.class)) {
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(Command.class)) {
                    Command command = method.getAnnotation(Command.class);
                    log.info("command msg : {}, function : {}, parent : {}", command.msg(), command.function(), command.parent());
                    commanderMap.put(command.function(), new Commander(WordUtils.uncapitalize(clazz.getSimpleName()), method, beanFactory));

                    if(command.parent().isEmpty()) {
                        continue;
                    }

                    Conversation conversation = conversationMap.get(command.parent());
                    if(conversation == null) {
                        conversationMap.put(command.parent(), new Conversation(command.msg(), command.function()));
                    } else {
                        conversation.putFunctionMap(command.msg(), command.function());
                    }
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

        String execute(MessageRequest messageRequest) throws InvocationTargetException, IllegalAccessException {
            return (String) this.method.invoke(this.beanFactory.getBean(this.name), messageRequest);
        }
    }

    private static final Map<String, Conversation> conversationMap = new HashMap<>();
    private static class Conversation {
        private Map<String, String> functionMap;    //key : msg, value : function
        Conversation(String msg, String function) {
            functionMap =  new HashMap<>();
            functionMap.put(msg, function);
        }

        public void putFunctionMap(String msg, String function) {
            this.functionMap.put(msg, function);
        }

        public Map<String, String> getFunctionMap() {
            return functionMap;
        }
    }






    public String message(Long romeKey, String msg) throws InvocationTargetException, IllegalAccessException {
        log.debug("message start romeKey : {}, msg : {}", romeKey, msg);
        for(String key : conversationMap.keySet()) {
            log.debug("parentKey : {}, msg-function : {}", key, conversationMap.get(key).getFunctionMap().toString());
        }

        //redis check
        ConversationInfo conversationInfo = ConversationUtils.stringToObject(redisHelper.peek(String.valueOf(romeKey)));

        MessageRequest messageRequest = new MessageRequest(String.valueOf(romeKey), msg);

        if(conversationInfo == null) {
            if(!"#wakeup!".equals(msg)) {
                return msg;
            }
            return commanderMap.get("wakeup").execute(messageRequest);
        }

        Conversation conversation = conversationMap.get(conversationInfo.getFunction());
        if(conversation == null) {
            return null;
        }

        if(conversation.getFunctionMap().containsKey("")) {
            return commanderMap.get(conversation.getFunctionMap().get("")).execute(messageRequest);
        }

        return commanderMap.get(conversation.getFunctionMap().get(msg)).execute(messageRequest);
    }



}
