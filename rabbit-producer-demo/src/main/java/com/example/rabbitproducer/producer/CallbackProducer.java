package com.example.rabbitproducer.producer;

import com.alibaba.fastjson.JSONObject;
import com.example.rabbitproducer.model.MessageWithRetryCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/12
 **/
@Slf4j
@Component
public class CallbackProducer implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback, InitializingBean {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /** 缓存map */
    private Map<String, MessageWithRetryCount> cashMap = new ConcurrentSkipListMap<>();
    /** 最大重试次数 */
    private Integer maxRetryCount = 3;


    /**
     * 传递 字符串
     */
    public void send(String content) {
        String msg = "i am callback producer" + content;
        // 用UUID为消息添加唯一标识
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 封装消息
        MessageWithRetryCount messageWithRetryCount = new MessageWithRetryCount(msg);
        // 先将消息缓存起来
        cashMap.put(correlationData.getId(),messageWithRetryCount);
        //设置消息唯一id 保证每次重试消息id唯一
        Message message = MessageBuilder.withBody(JSONObject.toJSONString(messageWithRetryCount).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .setMessageId(correlationData.getId())
                .build();
        log.debug("消息发送，messageId：{}，message：{}",correlationData.getId(),msg);
        /**
         * 通过指定交换机exchange的name和路由关键字routingkey，投递到对应的消息队列queue中
         * 没有指定交换机时，会使用默认的交换机
         * 交换机的名称错误，消息投递失败
         *              confirm方法中ack=false，cause为失败原因,
         *
         * 路由关键字错误，confirm中ack=true，说明消息已发送到交换机，
         *              returnedMessage有返回，说明交换机没有能够成功的将消息投递到对应的消息队列中，replyText为失败原因
         */
        rabbitTemplate.convertAndSend("callback","callback", message,correlationData);
    }


    /**
     * 消息发布回执确认
     * 投递到交换机失败原因：找不到交换机、网络闪断，Broker端异、负责queue的Erlang进程发生内部错误常等
     * 注意：回退的消息的ack也是true
     * @param correlationData
     * @param ack 消息是否成功投递到交换机
     * @param cause 若投递失败，失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.debug("消息投递成功,回调id:{}",correlationData.getId());
            // 表示已经成功投递 从map中删除
            cashMap.remove(correlationData.getId());
        } else {
            //失败则进行具体的后续操作，重试或者补偿等手段。
            log.debug("消息投递失败,回调id:{}, 原因:{}",correlationData.getId(),cause);
            // 从map中获取消息
            MessageWithRetryCount messageWithRetryCount = cashMap.get(correlationData.getId());
            if(null != messageWithRetryCount){
                // 尝试次数加 1
                messageWithRetryCount.setRetrycount(messageWithRetryCount.getRetrycount() + 1);
                if(maxRetryCount <  messageWithRetryCount.getRetrycount()){
                    // 表示尝试次数已用尽 从map中删除
                    log.debug("消息失败重试次数用尽，messageId:{}",correlationData.getId());
                    cashMap.remove(correlationData.getId());
                    return;
                }
            }
            // 更新map中消息
            cashMap.put(correlationData.getId(),messageWithRetryCount);
            // 失败尝试
            retrySendMessage(messageWithRetryCount,correlationData);
        }
    }

    /**
     * 消息回退接受接口
     * 投递到队列失败原因:找不到路由关键字对应消息队列、message server闪断等
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.debug("回退的消息：{}, replyCode:{}, replyText:{}, exchange:{}, routingKey:{}",new String(message.getBody()),replyCode,replyText,exchange,routingKey);
        // 解析message
        MessageWithRetryCount messageWithRetryCount = JSONObject.parseObject(new String(message.getBody())).toJavaObject(MessageWithRetryCount.class);
        // 重试次数加1
        messageWithRetryCount.setRetrycount(messageWithRetryCount.getRetrycount() + 1);
        if(maxRetryCount < messageWithRetryCount.getRetrycount()){
            // 表示尝试次数已用尽 从map中删除
            log.debug("消息失败重试次数用尽，messageId:{}",message.getMessageProperties().getMessageId());
            cashMap.remove(message.getMessageProperties().getMessageId());
            return;
        }
        // 因为消息代理Broker先回调basic.return()方法，再调用basic.ack()方法，所以先判断是否已回调消息发布确认接口
        while(cashMap.containsKey(message.getMessageProperties().getMessageId())){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 用UUID为消息添加唯一标识
        CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getMessageId());
        // 更新map中消息
        cashMap.put(correlationData.getId(),messageWithRetryCount);
        // 失败尝试
        retrySendMessage(messageWithRetryCount,correlationData);
    }

    /**
     * 创建新线程进行尝试
     */
    private void retrySendMessage(MessageWithRetryCount messageWithRetryCount,CorrelationData correlationData){
        new Thread(() ->{
            //设置消息唯一id 保证每次重试消息id唯一
            Message messageRetry = MessageBuilder.withBody(JSONObject.toJSONString(messageWithRetryCount).getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .setContentEncoding("utf-8")
                    .setMessageId(correlationData.getId())
                    .build();
            try {
                // 线程睡眠两秒钟再次尝试
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("消息进行第{}次失败重试，messageId:{}",messageWithRetryCount.getRetrycount(),correlationData.getId());
            rabbitTemplate.convertAndSend("callback","callback", messageRetry,correlationData);
        }).start();
    }



    /**
     * RabbitTemplate开启消息发布确认和消息回退
     * 注意：
     *      1.每个RabbitTemplate只能有一个ConfirmCallback或ReturnCallback
     *      2.如果有多个消息发布者，且每一个消息发布者都有自己的一个ConfirmCallback或ReturnCallback，那么需要使用自己注册的Bean。而且Bean的scope域为ConfigurableBeanFactory.SCOPE_PROTOTYPE原型模式
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 开启消息回执确认
        rabbitTemplate.setConfirmCallback(this);
        // 开启消息回退
        rabbitTemplate.setReturnCallback(this);
        // 开启强制性消费，使用自己注册的RabbitTemplate时打开注释
        //rabbitTemplate.setMandatory(true);
    }

    /**
     * 知识点
     * 消息的发送者和消息代理（broker）之间的消息确认
     * 解决方案
     *      1.发布回执确认，实现RabbitTemplate.ConfirmCallback接口，重写写confirm()方法，是异步的，性能较好
     *      2.amqp事务，rabbitTemplate开启事务支持，事务是唯一可以有效保障消息安全的措施，但其在消息的吞吐量上做了很大的牺牲，性能很差
     * 注意：amqp事务和发布回执确认解决的都是消息是否成功的到达broker，并投递到匹配的队列中
     *
     * 事务发布模式和异步发布确认模式性能比较
     *      1.采用事务模式发布消息性能差，而且有可能会影响消息顺序
     *      2.相比事务模式，异步消息确认的性能则好很多，相同硬件下，官方文档说可以提高250倍，我本地测试效率提高10倍左右
     *
     * rabbitmq消息发送确认步流程，前提是消息生产者开启了消息发布确认，消息回退功能，并且实现了RabbitTemplate.ConfirmCallback和RabbitTemplate.ReturnCallback两个接口
     *      1.消息发送到消息代理Broker，根据消息属性查找交换机，没有指定使用默认交换机；
     *      2.如果根据交换机名称没有找到交换机，消息代理直接回调confirm()方法，ack是false，cause为失败原因；
     *      3.找到交换机后，交换机就会根据路由关键字查找匹配的队列集合；
     *      4.如果没有找到合适的队列，消息代理先回调returnedMessage()方法，将消息回退给生产者，再回调confirm()方法，此时ack依然为true；
     *      5.匹配到队里后，交换机就会把消息push到匹配的队列中去
     *      6.如果消息是瞬时态，即不持久化的，那消息代理会在消息被交换机投递到所有匹配的队列后，回调confirm()方法，此时ack依然为true；
     *      7.如果消息是需要持久化的，那消息代理会在消息被交换机投递到匹配队列，并完成消息持久化后（写入内存或磁盘）后，回调confirm()方法，此时ack依然为true；
     *
     *
     * 注意：测试简单的demo时，应该关闭消息发布确认和消息回退功能
     */
}
