package com.example.javademo.list;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * List<E>接口实现类
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/05/21
 **/
public class TestList extends JavaDemoApplicationTests {

    @Test
    public void arrayList(){
        List<String> list = new ArrayList<>();

    }

    @Test
    public void vector(){
        List<String> list = new Vector<>();
    }

    @Test
    public void linkedList(){
        List<String> list = new LinkedList<>();
        list.add("song");
        list.add("xian");
        list.add("zhuo");
        list.add("xian");
        list.add(2,"xian");
        list.add("xian");
        list.add(null);
        list.stream().forEach(System.out::println);

    }


}
