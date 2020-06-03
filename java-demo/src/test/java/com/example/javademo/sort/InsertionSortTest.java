package com.example.javademo.sort;

import com.example.javademo.JavaDemoApplicationTests;

import org.junit.jupiter.api.Test;

import java.util.Arrays;


/**
 * 直接插入排序 Straight Insertion Sort
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/03
 **/
public class InsertionSortTest extends JavaDemoApplicationTests {

    private Integer[] array1 = {9,1,5,8,3,7,4,6,2};

    /**
     * 正序
     */
    @Test
    public void testAsc(){
        Integer[] array = array1;
        for (int i = 1; i < array.length; i++) {
            // 相邻元素逆序时才进行插入操作
            if(array[i] < array[i-1]){
                // 临时变量
                int temp = array[i];
                int j;
                // 如果大于临时变量，则元素右移，最终会空一位
                for (j =i-1; j >= 0 && array[j] > temp; j--) {
                    array[j+1] = array[j];
                }
                // 将临时变量插入到正确的位置
                array[j+1] = temp;
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 逆序
     */
    @Test
    public void testDesc(){
        Integer[] array = array1;
        for (int i = 1; i < array.length; i++) {
            // 相邻元素顺序时才进行插入操作
            if(array[i] > array[i-1]){
                // 临时变量
                int temp = array[i];
                int j;
                // 如果小于临时变量，则元素右移，最终会空一位
                for (j =i-1; j >= 0 && array[j] < temp; j--) {
                    array[j+1] = array[j];
                }
                // 将临时变量插入到正确的位置
                array[j+1] = temp;
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 直接插入排序 Straight Insertion Sort：
     *      基本操作：将一个记录插入到已经排好序的有序表中，从而得到一个新的、记录数增1的有序表；
     * 时间复杂度
     *      最好情况：比较次数为：n-1；移动记录为0，时间复杂度为O(n);
     *      最坏情况：比较次数为：2+3+4+...+n = (n+2)(n-1)/2,移动次数为：1+2+3+...+(n-1) = n(n-1)/2,时间复杂度为：O(n^2)
     *      因此总的时间复杂度为O(n^2)
     * 性能：
     *     直接插入排序法 > 简单选择排序法 > 冒泡排序法
     *
     *
     */
}
