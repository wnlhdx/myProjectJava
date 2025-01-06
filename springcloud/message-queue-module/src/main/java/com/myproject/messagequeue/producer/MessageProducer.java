package com.myproject.messagequeue.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private final StreamBridge streamBridge;

    @Autowired
    public MessageProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendMessage(String message) {
        streamBridge.send("outputChannel", message);
        System.out.println("Message sent: " + message);
    }
}
