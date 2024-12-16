package com.myproject.springboot.controller;

import com.myproject.springboot.service.impl.RabbitMQReceiver;
import com.myproject.springboot.service.impl.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    private RabbitMQSender sender;

    @Autowired
    private RabbitMQReceiver receiver;

    @GetMapping("/send")
    public String sendMessage() {
        sender.sendMessage("Hello, RabbitMQ!");
        return "Message Sent!";
    }
}
