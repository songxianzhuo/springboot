package com.example.javademo.multithreading.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/15
 **/
public class LockService {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Object object = new Object();

    public void methodA(){
        synchronized(object){
            lock.lock();
            try{
                Thread.sleep(50000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                lock.unlock();
            }
        }

    }

    public void methodB(){
        synchronized(object){
            lock.lock();
            try{
                System.out.println("1" + Thread.currentThread().getState());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }
}
