package com.example.javademo.list;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  Map<K,V></>接口实现类
 *      HashMap<K,V></>
 *      SortedSet<E></>
 *      LinkedHashSet<E></>
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/05/25
 **/
public class MapTest extends JavaDemoApplicationTests {


    /**
     * HashMap<K,V></>
     *      阈值：当达到阈值时，哈希表就进行扩充
     *      新初始化哈希表时，容量为默认容量，阈值为 容量*加载因子
     *      已有哈希表扩容时，容量、阈值均翻倍
     *      如果之前这个桶的节点类型是树，需要把新哈希表里当前桶也变成树形结构
     *      复制给新哈希表中需要重新索引,
     */
    @Test
    public void mapTest(){
        Map<String,Object> map = new HashMap<>();
        map.put("1",1);
    }
}
