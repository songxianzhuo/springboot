package com.example.webfluxdemo.router;

import com.example.webfluxdemo.handler.TimeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/05/18
 **/
@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> timeRouter(TimeHandler timeHandler){
        return RouterFunctions.route(RequestPredicates.GET("/time"),timeHandler::getTime)
                .andRoute(RequestPredicates.GET("/date"),timeHandler::getDate)
                .andRoute(RequestPredicates.GET("/times"),timeHandler::sendTimePerSec);
    }
}
