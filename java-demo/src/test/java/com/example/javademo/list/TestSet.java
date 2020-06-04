package com.example.javademo.list;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Set<E></>接口实现类
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/05/21
 **/
public class TestSet extends JavaDemoApplicationTests {

    @Test
    public void hashSet(){
        Set<String> set = new HashSet<>();
        System.out.println(15 >>> 1);
    }

    @Test
    public void sortedSet(){
        SortedSet<String> set = new TreeSet<>();
    }

    @Test
    public void linkedHashSet(){
        Set<String> set = new HashSet<>();
        System.out.println(15 >>> 1);
    }

}
