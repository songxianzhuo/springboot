package com.example.rabbitproducer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;


/**
 * 描述: rabbitMq 生产者端 配置类
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/11
 **/
@Configuration
public class MqConfig {

    /**
     Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
     Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列,一个交换机可以绑定多个消息队列
     Queue:消息的载体,每个消息都会被投到一个或多个队列。
     Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
     Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
     vhost:虚拟主机, ，用作不同用户的权限分离。
     Producer:消息生产者,就是投递消息的程序.
     Consumer:消息消费者,就是接受消息的程序.
     Channel:消息通道,在客户端的每个连接里,可建立多个channel.
     */

    /**
     * rabbitMq事务管理器，rabbitMq事务使用
     * @return
     */
    @Bean
    public RabbitTransactionManager rabbitTransactionManager(final ConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }

    /**
     * 注意：
     *      1.如果使用默认注册的RabbitTemplate需将此Bean注释
     *      2.如果希望每一个消息发布者单独有一套ConfirmCallback或ReturnCallback,则当前Bean的scope属性应该使用原型模式（ConfigurableBeanFactory.SCOPE_PROTOTYPE），即每次调用都会创建一个新的Bean实例
     *      3.自己注册RabbitTemplate时，则application.yml中的spring.rabbitmq.template.*的属性不会自动映射，需要手动配置
     */
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        // 可以在这配置rabbitTemplate的一些属性
//        return rabbitTemplate;
//    }

    /**
     * 消息转换器，自动关联到默认的AmqpTemplate
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue transactionQueue(){
        return new Queue("transaction");
    }

    @Bean
    public Queue callbackQueue(){
        Map<String, Object> args = new HashMap<>();
        //声明死信交换器
        args.put("x-dead-letter-exchange", "dlxExchange");
        //声明死信路由键
        args.put("x-dead-letter-routing-key", "deadMessage" );
        //声明队列消息过期时间 5000ms
        args.put("x-message-ttl", 30*60*1000);
        return new Queue("callback",true,false,false,args);
    }

    @Bean
    public Queue distributeQueue(){
        return new Queue("distribute");
    }

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @Bean
    public Queue userQueue(){
        return new Queue("user");
    }

    @Bean
    public Queue personQueue(){
        return new Queue("person");
    }

    /**
     * 死信队列
     */
    @Bean
    public Queue dlxQueue(){
        return new Queue("dlxQueue");
    }

    /**
     * 死信交换机
     */
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange("dlxExchange");
    }

    /**
     * 死信交换机
     */
    @Bean
    public DirectExchange callbackExchange() {
        return new DirectExchange("callback");
    }

    /**
     * 死信交换机和死信队列绑定
     */
    @Bean
    public Binding dlxBinding(Queue dlxQueue, DirectExchange dlxExchange) {
        return BindingBuilder.bind(dlxQueue).to(dlxExchange).with("deadMessage");
    }

    @Bean
    public Binding callbackBinding(Queue callbackQueue, DirectExchange callbackExchange) {
        return BindingBuilder.bind(callbackQueue).to(callbackExchange).with("callback");
    }


    //=============================直接交换机================================

    @Bean
    public Queue directQueueA(){
        return new Queue("direct.A");
    }

    @Bean
    public Queue directQueueB(){
        return new Queue("direct.B");
    }

    @Bean
    public Queue directQueueC(){
        return new Queue("direct.C");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct");
    }

    @Bean
    public Binding directBindingA(Queue directQueueA, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueA).to(directExchange).with("direct.a");
    }

    @Bean
    public Binding directBindingB(Queue directQueueB, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueB).to(directExchange).with("direct.b");
    }

    @Bean
    public Binding directBindingC(Queue directQueueC, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueC).to(directExchange).with("direct.c");
    }

    @Bean
    public Binding directBindingCallback(Queue callbackQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(callbackQueue).to(directExchange).with("direct.callback");
    }

    //==============================主题交换机===========================

    @Bean
    public Queue topicQueueA() {
        return new Queue("topic.A");
    }

    @Bean
    public Queue topicQueueB() {
        return new Queue("topic.B");
    }

    @Bean
    public Queue topicQueueC() {
        return new Queue("topic.C");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic");
    }

    /**
     * 将队列topic.A与exchange绑定，binding_key为topic.a,就是完全匹配
     */
    @Bean
    public Binding topicBindingA(Queue topicQueueA, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueA).to(topicExchange).with("topic.a");
    }

    /**
     * 将队列topic.B与exchange绑定，binding_key为topic.#,模糊匹配
     * #号表示一个或多个，或零个单词
     * *表示一个单词
     */
    @Bean
    public Binding topicBindingB(Queue topicQueueB, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueB).to(topicExchange).with("topic.#");
    }

    /**
     * 将队列topic.C与exchange绑定，binding_key为*.a,模糊匹配
     */
    @Bean
    public Binding topicBindingC(Queue topicQueueC, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueC).to(topicExchange).with("*.a");
    }

    //===========================扇形交换机============================

    @Bean
    public Queue fanoutQueueA() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue fanoutQueueB() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue fanoutQueueC() {
        return new Queue("fanout.C");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout");
    }

    /**
     * 扇形交换机绑定不需要routing-key
     */
    @Bean
    public Binding fanoutBindingA(Queue fanoutQueueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueA).to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBindingB(Queue fanoutQueueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueB).to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBindingC(Queue fanoutQueueC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueC).to(fanoutExchange);
    }

    //==============================头交换机===============================

    @Bean
    public Queue headersQueueA() {
        return new Queue("headers.A");
    }

    @Bean
    public Queue headersQueueB() {
        return new Queue("headers.B");
    }

    @Bean
    public Queue headersQueueC() {
        return new Queue("headers.C");
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headers");
    }

    /**
     * 头交换机
     * x-match有两种类型
     *      all:表示所有的键值对都匹配才能接受到消息
     *      any:表示只要有键值对匹配就能接受到消息
     */
    @Bean
    public Binding headersBindingA(Queue headersQueueA, HeadersExchange headersExchange) {
        return BindingBuilder.bind(headersQueueA).to(headersExchange).where("age").exists();
    }

    /**
     * 只校验headers中的key，满足任意一个就可以投递成功
     */
    @Bean
    public Binding headersBindingB(Queue headersQueueB, HeadersExchange headersExchange) {
        return BindingBuilder.bind(headersQueueB).to(headersExchange).whereAny("name","type").exist();
    }

    /**
     * 即校验headers中的key，还要校验key所对应的value
     */
    @Bean
    public Binding headersBindingC(Queue headersQueueC, HeadersExchange headersExchange) {
        Map<String,Object> keyMap = new HashMap<>();
        keyMap.put("name","rabbitMq");
        keyMap.put("type","headers");
        return BindingBuilder.bind(headersQueueC).to(headersExchange).whereAll(keyMap).match();
    }


    /**
     * rabbit的四种交换机说明
     *
     * Direct Exchange (直接交换机): 单播路由（unicast routing），交换机根据message携带的routing-key，将消息投递到对应的队列中，是精确匹配模式，
     * Topic Exchange (主题交换机): 多播路由（multicast routing），也是发布/订阅模式，交换机根据message携带的routing-key，采用正则匹配，将消息投递到一个或多个队列，是正则匹配模式
     * Headers Exchange (头交换机):自定义匹配规则，不使用routing-key，交换机根据队列绑定消息6头中“x-match”的匹配规则, 将消息投递到满足队列消息头匹配规则的队列中，是消息头（键值对）模式
     * Fanout Exchange (扇形交换机): 广播路由（broadcast routing），忽略路由键routingkey，会把消息发送给绑定它的所有队列 不需要匹配
     *
     * 总结：
     * 直达交换机：先匹配，再投递，即在绑定时设定一个routing_key，消息的routing_key完全匹配时，才会被交换机投递到绑定的队列中。
     * 扇形交换机：与routing_key无关，把所有消息投递给所有绑定的队列中。
     * 主题交换机：绑定routing_key，在匹配routing_key时按照正则配置的规则投递消息到队列中，这个也是最灵活的交换机。
     * 头交换机：使用多个消息属性来替代路由键建立路由规则，可以实现部分匹配或全部匹配
     */
}
