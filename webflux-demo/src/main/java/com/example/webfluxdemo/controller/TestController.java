package com.example.webfluxdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * webflux提供了基于webmvc相同的一套注解来定义请求的处理
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/05/18
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("Hello webflux");
    }

    /**
     * Mono<T>能但返回0-1个对象
     * @return
     */
    @GetMapping("/mono")
    public Mono<Object> mono() {
        // Mono表示包含0-1个元素的异步序列
        return Mono.create(monoSink -> {
            log.info("创建 Mono");
            monoSink.success("hello webflux");
        })
            .doOnSubscribe(subscription -> { //当订阅者去订阅发布者的时候，该方法会调用
                log.info("1_{}",subscription);
            }).doOnNext(o -> { //当订阅者收到数据时，该方法会调用
                log.info("2_{}",o);
            });
    }

    /**
     * spring webmvv
     * 等待耗时操作完成后在返回
     * @return
     */
    @GetMapping("/commonHandle")
    public String commonHandle(){
        log.info("common-start");
        //执行耗时操作
        String result = doThing("common handler");
        log.info("common-end");
        return result;
    }

    /**
     * spring webflux
     * 不会等待耗时操作完成后在返回
     * @return
     */
    @GetMapping("/monoHandle")
    public Mono<String> monoHandle(){
        log.info("mono-start");
        //执行耗时操作
        Mono<String> mono = Mono.fromSupplier(() -> doThing("mono handle"));
        log.info("mono-end");
        //Mono表示包含0或1个元素的异步序列
        return mono;
    }

    //定义耗时操作
    private String doThing(String msg) {
        try {
            System.out.println(msg);
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }

    private String[] cities = new String[]{"beijing", "shanghai", "gunagzhou", "shenzhen"};

    /**
     * Flux<T>能但返回0-N个对象
     * @return
     */
    @GetMapping("/flux")
    public Flux<String> fluxHandle1(){
        //Flux表示包含0-n个元素的异步序列
        return Flux.just("beijing", "shanghai", "gunagzhou", "shenzhen");
    }

    /**
     * 数组转flux
     * @return
     */
    @GetMapping("/array")
    public Flux<String> fluxHandle2(){
        //Flux表示包含0-n个元素的异步序列
        return Flux.fromArray(cities);
    }

    /**
     * 集合转 Flux
     * @return
     */
    @GetMapping("/list")
    public Flux<String> fluxHandle3(){
        List<String> list = Arrays.asList("beijing", "shanghai", "gunagzhou", "shenzhen");
        //将List转为Stream,再将Stream转为Flux
        return Flux.fromStream(list.stream());
    }

    /**
     * Flux  底层不会阻塞处理器执行
     * @return
     */
    @GetMapping("/time1")
    public Flux<String> timeHandle(){
        log.info("flux-start");
        //将Flux的每个元素映射为一个doThing耗时操作
        Flux<String> flux = Flux.fromStream(Arrays.asList("beijing", "shanghai", "gunagzhou", "shenzhen").stream().map(i -> doThing("elem-" + i)));
        log.info("flux-end");
        return flux;
    }
}
