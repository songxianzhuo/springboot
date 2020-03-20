package com.example.rabbitproducer.producer.fanout;

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
public class FanoutProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String msg ="hello i am an umbrella";
        System.out.println("FanoutProducer : " + msg);
        this.rabbitTemplate.convertAndSend("fanout","abcd.ee", msg);
    }
}
