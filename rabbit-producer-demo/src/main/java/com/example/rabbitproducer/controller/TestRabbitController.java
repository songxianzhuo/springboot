package com.example.rabbitproducer.controller;

import com.example.common.model.User;
import com.example.rabbitproducer.model.Person;
import com.example.rabbitproducer.producer.*;
import com.example.rabbitproducer.producer.direct.DirectProducer;
import com.example.rabbitproducer.producer.fanout.FanoutProducer;
import com.example.rabbitproducer.producer.headers.HeadersProducer;
import com.example.rabbitproducer.producer.topic.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/11
 **/
@RestController
public class TestRabbitController {

    @Autowired
    private Producer1 producer1;
    @Autowired
    private Producer2 producer2;
    @Autowired
    private DirectProducer directProducer;
    @Autowired
    private TopicProducer topicProducer;
    @Autowired
    private HeadersProducer headersProducer;
    @Autowired
    private FanoutProducer fanoutProducer;
    @Autowired
    private CallbackProducer callbackProducer;
    @Autowired
    private CallbackProducer2 callbackProducer2;
    @Autowired
    private DistributeProducer distributeProducer;
    @Autowired
    private TransactionProducer transactionProducer;

    /**
     * 单生产者-单消费者 字符串
     */
    @GetMapping("/hello")
    public void hello() {
        producer1.send("hello");
    }

    /**
     * 单生产者-单消费者 实体
     */
    @GetMapping("/testPojo")
    public void testPojo() {
        User user = new User();
        user.setName("宋献卓");
        user.setAge(25);
        producer1.send(user);
    }

    /**
     * 单生产者-单消费者 实体
     */
    @GetMapping("/testPojo2")
    public void testPojo2() {
        Person person = new Person();
        person.setName("张瑶");
        person.setAge(25);
        producer1.send(person);
    }

    /**
     * 单生产者-多消费者
     */
    @GetMapping("/oneToMany")
    public void oneToMany() {
        for (int i = 0; i < 20; i++) {
            String msg = "消息" + (i + 1);
            producer1.send(msg);
        }
    }

    /**
     * 多生产者-多消费者
     */
    @GetMapping("/manyToMany")
    public void manyToMany() {
        for (int i = 0; i < 10; i++) {
            String msg = "消息" + (i + 1);
            producer1.send(msg);
            producer2.send(msg);
        }

    }

    /**
     * 直接交换机 测试
     * @param type
     */
    @GetMapping("/testDirect")
    public void testDirect(@RequestParam(defaultValue = "A") String type){
        switch(type){
            case "A":
                directProducer.sendA();
                break;
            case "B":
                directProducer.sendB();
                break;
            case "C":
                directProducer.sendC();
                break;
            default:
                break;
        }
    }

    /**
     * 主题交换机 测试
     * @param type
     */
    @GetMapping("/testTopic")
    public void testTopic(@RequestParam(defaultValue = "A") String type){
        switch(type){
            case "A":
                topicProducer.sendA();
                break;
            case "B":
                topicProducer.sendB();
                break;
            case "C":
                topicProducer.sendC();
                break;
            default:
                break;
        }
    }

    /**
     * 扇形交换机 测试
     */
    @GetMapping("/testFanout")
    public void testFanout() {
        fanoutProducer.send();
    }

    /**
     * 头交换机 测试
     */
    @GetMapping("/testHeaders")
    public void testHeaders(@RequestParam(defaultValue = "A") String type) {
        switch(type){
            case "A":
                headersProducer.sendA();
                break;
            case "B":
                headersProducer.sendB();
                break;
            case "C":
                headersProducer.sendC();
                break;
            case "D":
                headersProducer.sendD();
                break;
            default:
                break;
        }
    }

    /**
     * 消息回调确认
     */

    @GetMapping("/testCallback")
    public void testCallback() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String content = "消息" + (i + 1);
            callbackProducer.send(content);
//            callbackProducer2.send(content);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        Long time = System.currentTimeMillis() - start;
        System.out.println("发布确认模式 耗时：" + time);
    }

    /**
     * rabbitMq消息分发机制 测试
     */
    @GetMapping("/testDistribute")
    public void testDistribute() {
        for (int i = 0; i < 20; i++) {
            String msg = "消息" + (i + 1);
            distributeProducer.send(msg);
        }
    }

    /**
     * rabbitTemplate操作rabbitMq事务 同步 测试
     */
    @GetMapping("/testTransaction")
    public void testTransaction() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            String content = "消息" + (i + 1);
            transactionProducer.send(content);
        }
        Long time = System.currentTimeMillis() - start;
        System.out.println("事务模式 耗时：" + time);
    }

}
