package com.example.rabbitconsumer.consumer;

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
 * @since 2020/03/13
 **/
@Component
public class TransactionConsumer {

    @RabbitListener(queues = "transaction")
    public void process1(Message message, Channel channel) throws IOException {
        System.out.println("TransactionConsumer: " + new String(message.getBody()));
        // 消息确认，消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
