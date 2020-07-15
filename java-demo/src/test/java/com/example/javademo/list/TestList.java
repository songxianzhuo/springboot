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
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.size());
        list.add(list.size(),5);
        for (Integer integer : list) {
            System.out.println(integer);
        }

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
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


}
