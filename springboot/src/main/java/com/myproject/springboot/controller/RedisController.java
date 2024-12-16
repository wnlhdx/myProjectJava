package com.myproject.springboot.controller;

import com.myproject.springboot.service.impl.RedisQueueService;
import com.myproject.springboot.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisQueueService redisQueueService;

    @GetMapping("/setCache")
    public String setCache() {
        redisService.setCache("name", "Spring Boot with Redis");
        return "Cache Set!";
    }

    @GetMapping("/getCache")
    public String getCache() {
        Object value = redisService.getCache("name");
        return "Cache Value: " + value;
    }

    @GetMapping("/enqueue")
    public String enqueue() {
        redisQueueService.enqueue("myQueue", "Message 1");
        return "Message Enqueued!";
    }

    @GetMapping("/dequeue")
    public String dequeue() {
        String message = redisQueueService.dequeue("myQueue");
        return "Dequeued Message: " + message;
    }
}