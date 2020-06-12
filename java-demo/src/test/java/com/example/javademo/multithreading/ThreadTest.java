package com.example.javademo.multithreading;

import com.example.javademo.JavaDemoApplicationTests;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/11
 **/
public class ThreadTest  extends JavaDemoApplicationTests {

    @SneakyThrows
    @Test
    public void test(){
        MyThread myThread = new MyThread();
        new Thread(myThread).start();
        new Thread(myThread).start();
        new Thread(myThread).start();
        new Thread(myThread).start();
    }
}
