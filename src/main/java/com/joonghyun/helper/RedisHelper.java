package com.joonghyun.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
        redisTemplate.expire(roomKey, 30, TimeUnit.SECONDS);
        listOperations.rightPush(roomKey, value);
    }

    public String pop(String roomKey) {
        redisTemplate.expire(roomKey, 30, TimeUnit.SECONDS);
        String value = listOperations.rightPop(roomKey);
        log.debug("Redis pop roomKey : {}, value : {} ", roomKey, value);
        return value;
    }

    public String peek(String roomKey) {
        redisTemplate.expire(roomKey, 30, TimeUnit.SECONDS);
        String value = listOperations.index(roomKey, listOperations.size(roomKey) - 1);
        log.debug("Redis peek roomKey : {}, value : {} ", roomKey, value);
        return value;
    }

    public void delete(String roomKey) {
        log.debug("Redis delete roomKey : {}", roomKey);
        redisTemplate.opsForValue().getOperations().delete(roomKey);
    }

    public String getIndex(String roomKey, int length) {
        String value = listOperations.index(roomKey, length);
        log.debug("Redis get[{}] roomeKey : {}, value : {}", length, roomKey, value);
        return value;
    }

    @PostConstruct
    public void redisDeleteAllKey() {
        Set<String> redisKeys = redisTemplate.keys("*");
        for(String key : redisKeys) {
            delete(key);
        }
    }
}
