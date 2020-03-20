package com.example.rabbitconsumer.consumer.direct;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/11
 **/
@Component
public class DirectConsumer {

//    @RabbitListener(queues = "direct.A")
//    public void processA(String msg) {
//        System.out.println("DirectConsumer_A : " + msg);
//    }

    @RabbitListener(queues = "direct.B")
    public void processB(String msg) {
        System.out.println("DirectConsumer_B : " + msg);
    }

    @RabbitListener(queues = "direct.C")
    public void processC(String msg) {
        System.out.println("DirectConsumer_C : " + msg);
    }

    /**
     * 手动确认
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "direct.A")
    public void processA(Message message, Channel channel) throws IOException {
        System.out.println("DirectConsumer_A: " + new String(message.getBody()));
        // 消息确认，消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 手动确认
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "direct.A")
    public void processB(Message message, Channel channel) throws IOException {
        System.out.println("DirectConsumer_B: " + new String(message.getBody()));
        // 消息确认，消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 手动确认
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "direct.A")
    public void processC(Message message, Channel channel) throws IOException {
        System.out.println("DirectConsumer_C: " + new String(message.getBody()));
        // 消息确认，消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
