package com.example.rabbitproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述 测试rabbitMq消息的分发机制 生产端
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/12
 **/
@Component
public class DistributeProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 传递 字符串
     * @param content
     */
    public void send(String content) {
        System.out.println("DistributeProducer: " + content);
        rabbitTemplate.convertAndSend("distribute", content);
    }
}
