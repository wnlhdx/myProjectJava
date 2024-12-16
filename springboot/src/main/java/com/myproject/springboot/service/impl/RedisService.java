package com.myproject.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 存储缓存
    public void setCache(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 获取缓存
    public Object getCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除缓存
    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }
}
