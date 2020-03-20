package com.example.rabbitconsumer.consumer;

import com.alibaba.fastjson.JSONObject;
import com.example.rabbitconsumer.model.Person;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 描述 手动确认
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/12
 **/
@Slf4j
@Component
public class CheckConsumer {

    @Autowired
    private RedisTemplate redisTemplate;
    /** 用于存储messageId */
    private final String zetKey = "messageZet";
    /** 重试消费次数 */
    private Integer maxRetryConsumerCount = 3;

    /**
     * 正常消费消息
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "callback")
    public void process(Message message, Channel channel) throws IOException {
        // 获取messageId
        String messageId = message.getMessageProperties().getMessageId();
        if(null == messageId){
            return;
        }
        // 将messageId写入redis
        Boolean writeToRedis = redisTemplate.opsForValue().setIfAbsent(messageId,"1");
        // 消费成功消费标识
        Boolean success = Boolean.TRUE;
        if(writeToRedis){
            try{
                // todo 进行业务处理
                //Thread.sleep(1000);
                // 模拟异常
                //System.out.println(1/0);
                // 修改expireTime
                redisTemplate.expire(messageId,10, TimeUnit.MINUTES);
                // 消息消费成功后messageId从zset集合中移除
                redisTemplate.opsForZSet().remove(zetKey,messageId);
                log.debug("消息消费成功，messageId:{},message:{}",messageId,new String(message.getBody()));
            }catch (Exception e){
                success = Boolean.FALSE;
                // 是否重入队列
                Boolean requeue = Boolean.TRUE;
                // 获取messageId的score
                Double score = redisTemplate.opsForZSet().score(zetKey,messageId);
                if(null != score){
                    score = score + 1.0;
                    // 分值大于消费者重试最大值
                    if(maxRetryConsumerCount < score.intValue()){
                        requeue = Boolean.FALSE;
                        // 尝试次数用尽，将messageId从zset集合中移除
                        redisTemplate.opsForZSet().remove(zetKey,messageId);
                    }else{
                        // score 加 1
                        redisTemplate.opsForZSet().incrementScore(zetKey,messageId,1.0);
                        log.debug("消费者第{}次消费消息，messageId:{}",score.intValue(),messageId);
                    }
                }else{
                    // 加入redis，记录重试次数
                    redisTemplate.opsForZSet().add(zetKey,messageId,1);
                }
                // redis除去messageId
                redisTemplate.delete(messageId);
                /**
                 * 消费者重试
                 * 业务过程发生异常，重新加入队列，让别的消费者消费
                 * b为参数multiple，当multiple为false，只确认当前的消息。当multiple为true，批量确认所有比当前deliveryTag小的消息
                 * b1为参数requeue，true时消息会重新进入Queue，false时丢弃该消息
                 * 注意：
                 * 1.业务发生异常，不会影响channel操作
                 */
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,requeue);
                log.debug("消息消费失败，业务处理发生异常，消息已[{}]，messageId:{}",requeue ? "重新入列" : "丢弃",messageId);
                e.printStackTrace();
            }
        }
        if(success){
            /**
             * 消费端通过basicAck方法进行消息消费确认
             * 方法说明:
             *      告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发;
             * 参数说明:
             *      b为参数multiple，当multiple为false，只确认当前的消息。当multiple为true，批量确认所有比当前deliveryTag小的消息，
             *      deliveryTag是用来标识Channel中投递的消息。RabbitMQ保证在每个Channel中，消息的deliveryTag是从1递增。
             * 注意:
             *      1.消费端如果忘记了ACK，这些消息会一直处于Unacked 状态。由于RabbitMQ消息消费没有超时机制，也就是程序不重启，消息会一直处于Unacked状态。当消费端程序关闭时，这些处于Unack状态的消息会重新恢复成Ready状态
             *      2.手动确认ack发生异常时，会触发消费者retry机制
             */
            //System.out.println(1/0);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.debug("消息手动确认成功，messageId:{}",messageId);
        }
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "dlxQueue",declare = "true",autoDelete = "false"),
            exchange = @Exchange(value = "dlxExchange", type = ExchangeTypes.DIRECT),
            key = "deadMessage"),ackMode ="AUTO")
    public void dLxprocess(Message message, Channel channel){
        log.debug("死信队列，messageId；{}，message：{}",message.getMessageProperties().getMessageId(),new String(message.getBody()));
    }


    /**
     * 知识点
     * 消费者和消息代理（broker）之间消息的确认，
     * 同理 消息是否被消费者接受或处理
     * 同理 消息代理（broker）什么时刻将消息从队列中删除
     * 解决方案
     *      1.自动确认模式（automatic acknowledgement model）：当消息代理（broker）将消息发送给消费者后立即删除，也称自动应答，
     *          缺点：1.容易造成消息丢失，2.容易造成信道上消息积压
     *      2.显式确认模式（explicit acknowledgement model）：待消费者手动发送一个确认回执（acknowledgement）后再删除消息，也称手动应答
     *          方法：
     *              消息确认：channel.basicAck()
     *              消息拒绝：channel.basicReject()，缺点：不支持批量否定确认消息
     *              消息拒绝：channel.basicNack(), 可以批量拒绝消息，拒绝的消息重新放在队列头部
     *  使用显示确认模式应注意：
     *      1.不要忘记应答消息，因为对于RabbitMQ来说处理消息没有超时，只要不应答消息，他就会认为仍在正常处理消息，导致消息队列出现阻塞，影响
     *      业务执行。
     *      2.如果消费者来不及处理就死掉时，没有响应ack时，这种情况下，队列将消息重新发送给其他消费者（也有可能还是自己）的唯一依据是消费该消息的消费者连接是否已断开
     *      3.可以选择丢弃消息，这其实也是一种应答，chanel.basicNack()方法；
     *      4.如果消费者设置了手动应答模式，并且设置了重试，出现异常时无论是否捕获了异常，都是不会重试的
     *      5.如果消费者没有设置手动应答模式，并且设置了重试，那么在出现异常时没有捕获异常会进行重试，如果捕获了异常不会重试
     *
     * 保证消息只消费一次
     *      每个消息配置一个唯一id，利用redis的setnx()方法的原子性，每次消费前，调用此方法，返回true，则证明没有消费过，返回false则证明redis中已存在该messgeId，表示已经消费过
     * 当业务处理异常时，会调用basic.nack()方法将消息重新进入队列，此时消息如果不能放在原位置，则会放在队列的头部位置，那么怎么避免消息次次消息，次次异常送回的死循环
     *      利用redis的zset结构，将消息的消费次数设置为每个key的score，每消费一次，sore加1，直到满足最大次数然后丢弃消息，消息会进入死信队列
     *
     *
     */

}
