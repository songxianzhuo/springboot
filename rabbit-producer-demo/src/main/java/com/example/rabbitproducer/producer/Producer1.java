package com.example.rabbitproducer.producer;

import com.example.common.model.User;
import com.example.rabbitproducer.model.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/11
 **/
@Component
public class Producer1 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 传递 字符串
     * @param content
     */
    public void send(String content) {
        System.out.println("Producer1_string: " + content);
        // 没有指定交换机，会使用默认交换机（default exchange），是一个名称为空字符串的直接交换机,此时routing-key值与消费者监听的queue的名字保持一致，消息可以投递成功
        rabbitTemplate.convertAndSend("","",content);
    }

    /**
     * 传递 实体对象
     * 使用的是默认的SimpleMessageConverter消息转换器
     * @param user
     */
    public void send(User user){
        System.out.println("Producer1_pojo_1: " + user.toString());
        rabbitTemplate.convertAndSend("user", user);
    }

    /**
     * 传递 实体对象
     * 使用的是自定义Jackson2JsonMessageConverter消息转换器
     * @param person
     */
    public void send(Person person){
        System.out.println("Producer1_pojo_2: " + person.toString());
        rabbitTemplate.convertAndSend("person", person);
    }

    /**
     * 知识点
     * 默认交换机（default exchange）：是一个由消息代理预先声明好的没有名字（名字为空字符串）的直连交换机
     *      特殊属性：每个新建队列（queue）都会自动绑定到默认交换机上，绑定的路由键（routing key）名称与队列名称相同
     * SimpleMessageConverter是rabbitMq的默认消息转换器
     *      在传递对象时，java对象需要实现序列化接口，而且发送者和接收者的bean定义必须一模一样，包括bean路径
     * 自定义消息转化器：Jackson2JsonMessageConverter
     *      该转化器可以将消息转化为JSON字符串，再进行传递
     * 多个监听一个队列的消费者每次只有一个消费者可以收到消息，消息的负载均衡也是发生在消费者之间的，即RabbitMQ会通过消息轮询机制来选择消费者
     *
     */
}
