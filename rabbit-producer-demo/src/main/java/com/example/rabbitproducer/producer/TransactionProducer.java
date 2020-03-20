package com.example.rabbitproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述 rabbitMq事务 测试
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/13
 **/
@Component
public class TransactionProducer implements InitializingBean {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * RabbitTemplate同步事务
     * 使用外部事物@Transactional(rollbackFor = Exception.class),来实现事务方法，出现异常，立刻回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void send(String content) {
        // rabbitTemplate开启channel级别的事务
        rabbitTemplate.setChannelTransacted(true);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String msg = "This is a transaction message！" + content;
        /**
         * 这里可以进行数据库操作
         */
        System.out.println("TransactionProducer: " + msg + "时间：" + sdf.format(new Date()));
        rabbitTemplate.convertAndSend("transaction", msg);
        // 模拟异常
        System.out.println(1/0);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // RabbitTemplate开启通道级别的事务,测试消息发布确认是应将该方法注释
        //rabbitTemplate.setChannelTransacted(true);
    }


    /**
     * 知识点
     * RabbitMQ中与事务有关的主要有三个方法，方法是消息发送方通过chanel调用：
     *      1.txSelect() 目的是将当前chanel设置为transaction模式, 消息代理broker用txSelect_ok()方法回应；
     *      2.txCommit() 用于提交事务，消息代理broker用txCommit_ok()方法回应；
     *      3.txRollback() 用于回滚事务，消息代理broker用txRollback_ok()方法回应；
     * 注意：
     *      1.消息发布确认和事务两者不能同时使用，只能择其一，使用事务前应该关闭发布确认，设置spring.rabbitmq.publisher-confirms=false
     *
     * springboot通常使用RabbitTemplate来操作rabbitMq事务
     *      1.需要配置RabbitTransactionManager事务管理器
     *      2.RabbitTemplate开启事务支持，rabbitTemplate.setChannelTransacted(true);
     *
     * RabbitTemplate操作rabbitMq事务分为同步事务和异步事务
     *      同步事务：消息生产者将消息发送到消息代理Broker的事务
     *      异步事务：消息消费者从消息代理Broker接受消息的事务, todo 目前没有测试通
     *
     *
     */
}
