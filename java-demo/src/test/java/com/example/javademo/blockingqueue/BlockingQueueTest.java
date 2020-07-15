package com.example.javademo.blockingqueue;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/22
 **/
public class BlockingQueueTest extends JavaDemoApplicationTests {

    @Test
    public void test(){
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
        DelayQueue delayQueue = new DelayQueue();
        LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue();
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
    }
}
