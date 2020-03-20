package com.example.rabbitconsumer.consumer.fanout;

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
public class FanoutConsumer {

    @RabbitListener(queues = "fanout.A")
    public void processA(String msg) {
        System.out.println("FanoutConsumer_A : " + msg);
    }

    @RabbitListener(queues = "fanout.B")
    public void processB(String msg) {
        System.out.println("FanoutConsumer_B : " + msg);
    }

    @RabbitListener(queues = "fanout.C")
    public void processC(String msg) {
        System.out.println("FanoutConsumer_C : " + msg);
    }
}
