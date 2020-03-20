package com.example.rabbitconsumer.consumer;

import com.alibaba.fastjson.JSONObject;
import com.example.rabbitconsumer.model.Person;
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
public class Consumer2 {

    @RabbitListener(queues = "hello")
    public void process(Message message, Channel channel) throws IOException {
        System.out.println("Consumer_2: " + new String(message.getBody()));
        // 消息确认，消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "person")
    public void processPojo(Message message, Channel channel) throws IOException {
        String str = new String(message.getBody());
        System.out.println("消息体：" + str);
        JSONObject jsonObject = JSONObject.parseObject(str);
        Person person = jsonObject.toJavaObject(Person.class);
        System.out.println("Consumer_1-3: " + person.toString());
        // 消息确认，消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
