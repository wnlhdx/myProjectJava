package com.myproject.springboot.controller;

import com.myproject.springboot.service.impl.RedisQueueService;
import com.myproject.springboot.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisQueueService redisQueueService;

    @GetMapping("/setCache")
    public Mono<String> setCache() {
        // 这里假设 redisService.setCache() 是响应式的，可以返回 Mono<Void> 或其他 Mono 类型
        return Mono.fromRunnable(() -> redisService.setCache("name", "Spring Boot with Redis"))
                .thenReturn("Cache Set!");
    }

    @GetMapping("/getCache")
    public Mono<String> getCache() {
        // 假设 redisService.getCache() 是响应式的，可以返回 Mono<Object>
        return Mono.fromCallable(() -> redisService.getCache("name"))
                .map(value -> "Cache Value: " + value);
    }

    @GetMapping("/enqueue")
    public Mono<String> enqueue() {
        // 假设 redisQueueService.enqueue() 是响应式的，可以返回 Mono<Void>
        return Mono.fromRunnable(() -> redisQueueService.enqueue("myQueue", "Message 1"))
                .thenReturn("Message Enqueued!");
    }

    @GetMapping("/dequeue")
    public Mono<String> dequeue() {
        // 假设 redisQueueService.dequeue() 是响应式的，可以返回 Mono<String>
        return Mono.fromCallable(() -> redisQueueService.dequeue("myQueue"))
                .map(message -> "Dequeued Message: " + message);
    }
}
