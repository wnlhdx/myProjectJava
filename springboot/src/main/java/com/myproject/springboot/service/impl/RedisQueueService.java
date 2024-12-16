package com.myproject.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisQueueService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 将消息添加到队列
    public void enqueue(String queueName, String message) {
        redisTemplate.opsForList().leftPush(queueName, message);
    }

    // 从队列中获取消息
    public String dequeue(String queueName) {
        return (String) redisTemplate.opsForList().rightPop(queueName);
    }
}
