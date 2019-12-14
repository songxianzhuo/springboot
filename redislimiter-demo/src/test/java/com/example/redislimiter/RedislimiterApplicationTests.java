package com.example.redislimiter;

import com.example.redislimiter.util.VoteUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedislimiterApplicationTests {

    @Autowired
    private VoteUtil voteUtil;

    @Test
    void contextLoads() {
    }

    @Test
    public void testVote(){
        String vote = "发明家";
        String player = "宋献卓";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 20; j++) {
                player = player + j;
                System.out.println(voteUtil.vote(vote,player));
                player = "宋献卓";
            }
        }
    }

}
