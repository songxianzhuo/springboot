package com.example.rabbitconsumer.consumer.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/11
 **/
@Component
public class TopicConsumer {

    @RabbitListener(queues = "topic.A")
    public void processA(String msg) {
        System.out.println("TopicConsumer_A : " +msg);
    }

    @RabbitListener(queues = "topic.B")
    public void processB(String msg) {
        System.out.println("TopicConsumer_B : " +msg);
    }

    @RabbitListener(queues = "topic.C")
    public void processC(String msg) {
        System.out.println("TopicConsumer_C : " +msg);
    }
}
