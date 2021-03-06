package com.joonghyun.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joonghyun.model.converstation.ConversationInfo;
import com.joonghyun.model.converstation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joonghyun on 2017. 5. 4..
 */
public class ConversationUtils {

    private static final Logger log = LoggerFactory.getLogger(ConversationUtils.class);

    public static String objectToString(ConversationInfo redis) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(redis);
            log.debug("jsonString : {}", jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static ConversationInfo stringToObject(String str) {
        if(str == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        ConversationInfo redis = new ConversationInfo();
        try {
            redis = mapper.readValue(str, ConversationInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return redis;
    }

//    public static ConversationInfo paramToObject(String function, Param... params) {
//        ConversationInfo redis = new ConversationInfo();
//        redis.setFunction(function);
//
//        Map<String, String> map = new HashMap<>();
//        for(Param param : params) {
//            map.put(param.getKey(), param.getValue());
//        }
//        redis.setParam(map);
//
//        return redis;
//    }

    public static ConversationInfo paramToObject(String function, String msg) {
        ConversationInfo conversationInfo = new ConversationInfo();
        conversationInfo.setFunction(function);
        conversationInfo.setMsg(msg);
        return conversationInfo;
    }
}
