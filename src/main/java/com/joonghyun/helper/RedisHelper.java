package com.joonghyun.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Component
public class RedisHelper {
    private static final Logger log = LoggerFactory.getLogger(RedisHelper.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name="strRedisTemplate")
    private ListOperations<String, String> listOperations;

    public void push(String roomKey, String value) {
        log.debug("Redis push roomKey : {}, value : {} ", roomKey, value);
        listOperations.rightPush(roomKey, value);
    }

    public String pop(String roomKey) {
        String value = listOperations.rightPop(roomKey);
        log.debug("Redis pop roomKey : {}, value : {} ", roomKey, value);
        return value;
    }

    public String peek(String roomKey) {
        String value = listOperations.index(roomKey, listOperations.size(roomKey) - 1);
        log.debug("Redis peek roomKey : {}, value : {} ", roomKey, value);
        return value;
    }

    public void delete(String roomKey) {
        log.debug("Redis delete roomKey : {}", roomKey);
        redisTemplate.opsForValue().getOperations().delete(roomKey);
    }
}
