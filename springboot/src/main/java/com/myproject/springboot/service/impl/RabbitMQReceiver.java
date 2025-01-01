package com.myproject.springboot.service.impl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiver {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "myQueue")
    public void receiveMessage(Message message) {
        // 手动确认消息
        System.out.println("Received message: " + message);
        // 需要手动确认时可以调用：
        // channel.basicAck(deliveryTag, false);
    }
}

