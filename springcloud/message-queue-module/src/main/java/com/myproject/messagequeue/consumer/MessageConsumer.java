package com.myproject.messagequeue.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public class MessageConsumer {

    @Bean
    public Consumer<String> inputChannel() {
        return message -> System.out.println("Message received: " + message);
    }
}
