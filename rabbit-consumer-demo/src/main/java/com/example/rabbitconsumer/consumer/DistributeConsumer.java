package com.example.rabbitconsumer.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述 测试rabbitMq消息分发机制 消费端
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/12
 **/
@Component
public class DistributeConsumer {

    @RabbitListener(queues = "distribute")
    public void process1(Message message, Channel channel) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("DistributeConsumer_1: " + new String(message.getBody()) + " 时间: " + sdf.format(new Date()));
        try {
            // 通过睡眠时间来表示消费者处理消息的能力
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 消息确认，消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "distribute")
    public void process2(Message message, Channel channel) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("DistributeConsumer_2: " + new String(message.getBody()) + " 时间: " + sdf.format(new Date()));
        try {
            // 通过睡眠时间来表示消费者处理消息的能力
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 消息确认，消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * rabbitMq有两种消息分发机制
     *  1.Round-robin dispatch (轮询分发)：
     *      在默认情况下，RabbitMQ不会顾虑消息者处理消息的能力，即使其中有的消费者闲置有的消费者高负荷。
     *      RabbitMQ会逐个发送消息到在序列中的下一个消费者(而不考虑每个任务的时长等等，且是提前一次性分配，并非一个一个分配)。
     *      平均每个消费者获得相同数量的消息，这种方式分发消息机制称为Round-Robin（轮询）
     *  2.Fair dispatch (公平分发)：
     *      公平分发，则是根据消费者的处理能力来进行分发处理的。这里主要是通过设置prefetchCount参数来实现的。
     *      这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理规定的数量级个数的Message。
     *      换句话说，在接收到该Consumer的ack前，它不会将新的Message分发给它。
     *      比如prefetchCount=1，则在同一时间下，每个Consumer在同一个时间点最多处理1个Message，同时在收到Consumer的ack前，它不会将新的Message分发给它
     *  注意：
     *      1.springboot封装rabbitMq,默认采用的是轮询分发的方式
     *      2.如何开启公平分发？
     *          1.关闭自动应答，改为手动应答，acknowledge-mode设置为manual
     *          2.设置prefetchCount参数
     *
     *
     */
}
