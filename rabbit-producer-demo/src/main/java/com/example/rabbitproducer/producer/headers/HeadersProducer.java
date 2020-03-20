package com.example.rabbitproducer.producer.headers;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.Map;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/11
 **/
@Component
public class HeadersProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendA() {
        String msg = "i am Song Xianzhuo";
        MessageProperties props = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("123")
                .setHeader("age", "30")
                .build();
        Message message = MessageBuilder.withBody(msg.getBytes()).andProperties(props).build();
        System.out.println("HeadersProducer_A : " + msg);
        this.rabbitTemplate.convertAndSend("headers", "", message);
    }

    public void sendB() {
        String msg = "i am Song Xianzhuo";
        MessageProperties props = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("123")
                .setHeader("name", "rabbitMq")
                .build();
        Message message = MessageBuilder.withBody(msg.getBytes()).andProperties(props).build();
        System.out.println("HeadersProducer_B : " + msg);
        this.rabbitTemplate.convertAndSend("headers", "", message);
    }

    public void sendC() {
        String msg = "i am Song Xianzhuo";
        MessageProperties props = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("123")
                .setHeader("name", "rabbitMq")
                .setHeader("type","headers")
                .build();
        Message message = MessageBuilder.withBody(msg.getBytes()).andProperties(props).build();
        System.out.println("HeadersProducer_C : " + msg);
        this.rabbitTemplate.convertAndSend("headers", "", message);
    }

    public void sendD() {
        String msg = "i am Song Xianzhuo";
        MessageProperties props = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("123")
                .setHeader("name", "a")
                .setHeader("type","b")
                .build();
        Message message = MessageBuilder.withBody(msg.getBytes()).andProperties(props).build();
        System.out.println("HeadersProducer_D : " + msg);
        this.rabbitTemplate.convertAndSend("headers", "", message);
    }
}
