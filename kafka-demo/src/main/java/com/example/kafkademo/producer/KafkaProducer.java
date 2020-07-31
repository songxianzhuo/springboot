package com.example.kafkademo.producer;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/14
 **/
@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    //自定义topic
    public static final String TOPIC_TEST = "topic.test";

    //消费者分组
    public static final String TOPIC_GROUP1 = "topic.group1";

    //消费者分分组
    public static final String TOPIC_GROUP2 = "topic.group2";


    public void send(Object obj) {
        String obj2String = JSONObject.toJSONString(obj);
        log.info("准备发送消息为：{}", obj2String);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST, obj);
        // 添加发布回调，第一种写法
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }

    /**
     * kafka事务提交
     * 声明事务：后面报错消息不会发出去
     * 不声明事务：后面报错但前面消息已经发送成功了
     * @param obj
     */
    public void sendTransaction(String obj){
        String obj2String = JSONObject.toJSONString(obj);
        log.info("准备发送消息为：{}", obj2String);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.executeInTransaction(operations -> {
            operations.send(TOPIC_TEST,obj);
            throw new RuntimeException("fail");
        });
        // 添加发布回调，第二种写法
        future.addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            //成功的处理
            log.info(TOPIC_TEST + " - 生产者 发送消息成功：" + topic + "-" + partition + "-" + offset);
        },failure -> {
            //发送失败的处理
            log.info(TOPIC_TEST + " - 生产者 发送消息失败：" + failure.getMessage());
        });
    }
}
