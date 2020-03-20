package com.example.rabbitconsumer.consumer.headers;

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
public class HeadersConsumer {

    @RabbitListener(queues = "headers.A")
    public void processA(String msg) {
        System.out.println("HeaderConsumer_A : " + msg);
    }

    @RabbitListener(queues = "headers.B")
    public void processB(String msg) {
        System.out.println("HeaderConsumer_B : " + msg);
    }

    @RabbitListener(queues = "headers.C")
    public void processC(String msg) {
        System.out.println("HeaderConsumer_C : " + msg);
    }
}
