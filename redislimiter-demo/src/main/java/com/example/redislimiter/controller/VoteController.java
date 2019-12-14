package com.example.redislimiter.controller;

import com.example.redislimiter.util.VoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: VoteController
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/6 15:56
 * @Version: 1.0
 */
@RestController
public class VoteController {

    @Autowired
    private VoteUtil voteUtil;

    private static Integer count = 0;

    @PostMapping("/vote")
    public void vote(@RequestParam("voter") String voter, @RequestParam("player") String player) throws InterruptedException {
        while (true){
            if (voteUtil.vote(voter, player)){
                count++;
            }
            TimeUnit.SECONDS.sleep(2);
            System.out.println(count + " " + LocalTime.now());
        }
    }
}
