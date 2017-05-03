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

    public void push(String romeKey, String value) {
        log.debug("Redis push romeKey : {}, value : {} ", romeKey, value);
        listOperations.rightPush(romeKey, value);
    }

    public String pop(String romeKey) {
        String value = listOperations.rightPop(romeKey);
        log.debug("Redis pop romeKey : {}, value : {} ", romeKey, value);
        return value;
    }

    public String peek(String romeKey) {
        String value = listOperations.index(romeKey, listOperations.size(romeKey) - 1);
        log.debug("Redis peek romeKey : {}, value : {} ", romeKey, value);
        return value;
    }

    public void delete(String romeKey) {
        log.debug("Redis delete romeKey : {}", romeKey);
        redisTemplate.opsForValue().getOperations().delete(romeKey);
    }
}
