package com.example.javademo.multithreading.callable;

import com.example.javademo.multithreading.MyRunnable;

import java.util.concurrent.*;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/17
 **/
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1/0;
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MyCallable myCallable = new MyCallable();
        try{
            Future<Integer> future = executorService.submit(myCallable);
            System.out.println(future.get());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
