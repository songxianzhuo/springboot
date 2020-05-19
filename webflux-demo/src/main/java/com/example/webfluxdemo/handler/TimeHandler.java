package com.example.webfluxdemo.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * 学习链接：
 *      https://blog.csdn.net/zhangjun62/article/details/91967491
 *      https://blog.csdn.net/get_set/article/details/79480233
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/05/18
 **/
@Component
public class TimeHandler {

    /**
     * 获取时间
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }

    /**
     * 获取日期
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())), String.class);
    }

    /**
     * SSE
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> sendTimePerSec(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(  // MediaType.TEXT_EVENT_STREAM表示Content-Type为text/event-stream，即SSE
                Flux.interval(Duration.ofSeconds(1)).   // 利用interval生成每秒一个数据的流
                        map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())),
                String.class);
    }
}
