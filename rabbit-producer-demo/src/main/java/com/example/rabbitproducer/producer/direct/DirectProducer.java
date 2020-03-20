package com.example.rabbitproducer.producer.direct;

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
public class DirectProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendA() {
        String msg = "I am direct.a msg  =======";
        System.out.println("DirectProducer_A : " + msg);
        this.rabbitTemplate.convertAndSend("direct", "direct.a", msg);
    }

    public void sendB(){
        String msg = "I am direct.b msg  =======";
        System.out.println("DirectProducer_B : " + msg);
        this.rabbitTemplate.convertAndSend("direct", "direct.b", msg);
    }

    public void sendC(){
        String msg = "I am direct.c msg  =======";
        System.out.println("DirectProducer_C : " + msg);
        this.rabbitTemplate.convertAndSend("direct", "direct.c", msg);
    }
}
