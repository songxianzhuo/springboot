package com.example.kafkademo.controller;


import com.example.kafkademo.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/14
 **/
@Slf4j
@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/send")
    public void sendMsg(@RequestParam String msg){
        kafkaProducer.send(msg);
    }
}
