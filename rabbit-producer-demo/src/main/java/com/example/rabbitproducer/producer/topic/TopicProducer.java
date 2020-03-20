package com.example.rabbitproducer.producer.topic;

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
public class TopicProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendA() {
        String msg = "I am topic.a msg======";
        System.out.println("TopicProducer_A : " + msg);
        this.rabbitTemplate.convertAndSend("topic", "topic.a", msg);
    }

    public void sendB(){
        String msg = "I am topic.b msg########";
        System.out.println("TopicProducer_B : " + msg);
        this.rabbitTemplate.convertAndSend("topic", "topic.a.b", msg);
    }

    public void sendC(){
        String msg = "I am topic.c msg########";
        System.out.println("TopicProducer_C : " + msg);
        this.rabbitTemplate.convertAndSend("topic", "any.a", msg);
    }

}
