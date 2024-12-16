package com.myproject.springboot.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // 定义队列
    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", true);  // true表示队列持久化
    }

    // 定义交换机
    @Bean
    public TopicExchange myExchange() {
        return new TopicExchange("myExchange");
    }

    // 定义队列和交换机之间的绑定
    @Bean
    public Binding binding(Queue myQueue, TopicExchange myExchange) {
        return BindingBuilder.bind(myQueue).to(myExchange).with("my.routing.key");
    }

    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> myRabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);  // 设置手动确认模式
        return factory;
    }
}
