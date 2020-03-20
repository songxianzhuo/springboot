package com.example.rabbitproducer.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/17
 **/
@Component
public class CallbackProducer2 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String content) {
//        setupCallbacks();
        String msg = "i am callback producer" + content;
        System.out.println("CallbackProducer2 : " + msg);
        // 用UUID为消息添加唯一标识
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("CallbackProducer2_UUID : " + correlationData.getId());
        /**
         * 通过指定交换机exchange的name和路由关键字routingkey，投递到对应的消息队列queue中
         * 没有指定交换机时，会使用默认的交换机
         * 交换机的名称错误，消息投递失败
         *              confirm方法中ack=false，cause为失败原因,
         *
         * 路由关键字错误，confirm中ack=true，说明消息已发送到交换机，
         *              returnedMessage有返回，说明交换机没有能够成功的将消息投递到对应的消息队列中，replyText为失败原因
         */
        rabbitTemplate.convertAndSend("direct","direct.d", msg,correlationData);
    }

    /**
     * 测试多个生产者多套ConfirmCallback或ReturnCallback时可以打开注释
     */
//    private void setupCallbacks() {
//        // 开启强制性消费
//        this.rabbitTemplate.setMandatory(true);
//        /*
//         * Confirms/returns enabled in application.properties - add the callbacks here.
//         */
//        this.rabbitTemplate.setConfirmCallback((correlation, ack, reason) -> {
//            if (correlation != null) {
//                System.out.println("Received " + (ack ? " ack " : " nack ") + "for correlation: " + correlation);
//            }
//        });
//        this.rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            System.out.println("Returned: " + message + "\nreplyCode: " + replyCode
//                    + "\nreplyText: " + replyText + "\nexchange/rk: " + exchange + "/" + routingKey);
//        });
//    }
}
