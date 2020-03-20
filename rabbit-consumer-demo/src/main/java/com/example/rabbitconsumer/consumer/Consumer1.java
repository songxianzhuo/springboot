package com.example.rabbitconsumer.consumer;

import com.alibaba.fastjson.JSONObject;
import com.example.common.model.User;
import com.example.rabbitconsumer.model.Person;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

/**
 * 描述 自动确认模式
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/11
 **/
@Component
public class Consumer1 {

    /**
     * @RabbitListener就是监听某个队列，如果该队列中有消息时，就会发送给用户
     */
//    @RabbitListener(queues = "callback")
//    public void process(String content) {
//        System.out.println("Consumer_1-1: " + content);
//    }
//
//    @RabbitListener(queues = "user")
//    public void processPojo(User user) {
//        System.out.println("Consumer_1-2: " + user.toString());
//    }


}
