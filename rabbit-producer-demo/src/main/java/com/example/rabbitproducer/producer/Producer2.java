package com.example.rabbitproducer.producer;

import com.example.rabbitproducer.config.MqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/11
 **/
@Component
public class Producer2 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String content) {
        System.out.println("Producer2: " + content);
        this.rabbitTemplate.convertAndSend("hello", content);
    }
}
