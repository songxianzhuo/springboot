package com.example.javademo.atomic;

import com.example.javademo.JavaDemoApplicationTests;
import com.example.javademo.entity.Person;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.*;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/19
 **/
public class AtomicTest extends JavaDemoApplicationTests {

    /**
     * 原子更新基本类型
     */
    @SneakyThrows
    @Test
    public void atomicIntegerTest() {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("plus " + atomicInteger.incrementAndGet());
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("reduce " + atomicInteger.decrementAndGet());
                }
            }).start();
            countDownLatch.countDown();
        }
        countDownLatch.await();
        Thread.sleep(5000);
        System.out.println("最终结果：" + atomicInteger.get());
    }

    /**
     * 原子更新数组
     */
    @Test
    public void atomicIntegerArrayTest() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
        atomicIntegerArray.set(2, 10);
        atomicIntegerArray.addAndGet(8, 11);
        atomicIntegerArray.set(3, 15);
        atomicIntegerArray.accumulateAndGet(0, 8, (a, b) -> a + b);
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }
    }

    /**
     * 原子更新引用类型
     */
    @Test
    public void atomicReferenceTest() {
        Person person = Person.builder().name("songxianzhuo").age(29).build();
        Person person2 = Person.builder().name("zhagyao").age(29).build();
        Person person3 = Person.builder().name("songxian").age(29).build();
        AtomicReference<Person> atomicReference = new AtomicReference<>(person);
        System.out.println(atomicReference.compareAndSet(person, person3));
        System.out.println(atomicReference.get().getName());
    }

    /**
     * 原子更新引用类型字段
     * 注意：修改的字段类型必须是公共的，volatile类型的
     */
    @Test
    public void atomicReferenceFieldUpdaterTest() {
        Person person = Person.builder().name("songxianzhuo").age(29).build();
        AtomicReferenceFieldUpdater<Person, Integer> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Person.class, Integer.class, "age");
        atomicReferenceFieldUpdater.compareAndSet(person, 29, 30);
        System.out.println(person.getAge());
    }

    /**
     * 原子更新字段类
     * 是对AtomicReferenceFieldUpdater类的进一步封装
     */
    @Test
    public void atomicIntegerFieldUpdaterTest(){
        Person person = Person.builder().name("songxianzhuo").age(29).build();
        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class,"age");
        atomicIntegerFieldUpdater.compareAndSet(person, 29, 30);
        System.out.println(person.getAge());
    }

    @Test
    public void atomicMarkableReferenceTest(){
        Person person = Person.builder().name("songxianzhuo").age(29).build();
        Person person2 = Person.builder().name("zhagyao").age(29).build();
        AtomicMarkableReference<Person> atomicMarkableReference = new AtomicMarkableReference<>(person,Boolean.TRUE);
        System.out.println(atomicMarkableReference.isMarked());
        //atomicMarkableReference.attemptMark(person,Boolean.FALSE);
        System.out.println(atomicMarkableReference.compareAndSet(person,person2,Boolean.FALSE,Boolean.FALSE));
        System.out.println(atomicMarkableReference.isMarked());
        System.out.println(atomicMarkableReference.getReference().getName());
    }

    /**
     * 可以有效解决CAS的ABA问题
     */
    @Test
    public void atomicStampedReferenceTest(){
        Person person = Person.builder().name("songxianzhuo").age(29).build();
        Person person2 = Person.builder().name("zhagnyao").age(29).build();
        AtomicStampedReference<Person> atomicStampedReference = new AtomicStampedReference<>(person,1);
        System.out.println(atomicStampedReference.getStamp());
        System.out.println(atomicStampedReference.attemptStamp(person,2));
        System.out.println(atomicStampedReference.getStamp());
        System.out.println(atomicStampedReference.compareAndSet(person,person2,2,3));
        System.out.println(atomicStampedReference.getReference().getName());
        System.out.println(atomicStampedReference.getStamp());
    }


}