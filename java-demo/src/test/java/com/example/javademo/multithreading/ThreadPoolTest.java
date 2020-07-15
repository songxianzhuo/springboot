package com.example.javademo.multithreading;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.example.javademo.JavaDemoApplicationTests;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/19
 **/
public class ThreadPoolTest extends JavaDemoApplicationTests {

    @SneakyThrows
    @Test
    public void test(){
        CountDownLatch countDownLatch = new CountDownLatch(49);
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(45);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                10,
                10000, TimeUnit.MILLISECONDS,
                arrayBlockingQueue,
                ThreadFactoryBuilder.create().setNamePrefix("song-").build(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 50 ; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            });
            Thread.sleep(10);
        }
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (countDownLatch.getCount() > 0) {
                    System.out.println("线程池信息 线程数：" + threadPoolExecutor.getPoolSize() +
                            " 最大线程数：" + threadPoolExecutor.getLargestPoolSize() +
                            " 历史任务数：" + threadPoolExecutor.getTaskCount() +
                            " 完成任务数： " + threadPoolExecutor.getCompletedTaskCount() +
                            " 活动线程数： " + threadPoolExecutor.getActiveCount() +
                            " 工作队列： " + arrayBlockingQueue.size());
                    Thread.sleep(3000);
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            });
            Thread.sleep(1000);
        }

    }
}
