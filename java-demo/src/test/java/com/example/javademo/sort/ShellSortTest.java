package com.example.javademo.sort;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 希尔排序 Shell Sort
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/03
 **/
public class ShellSortTest extends JavaDemoApplicationTests {

    private Integer[] array1 = {9,1,5,8,3,7,4,6,2,10,18,11};

    /**
     * 正序
     */
    @Test
    public void testAsc(){
        Integer[] array = array1;
        int increment = array.length;
        do{
            increment = increment / 3 + 1;
            for (int i = increment; i < array.length; i++) {
                if (array[i] < array[i-increment]){
                    int temp = array[i];
                    int j;
                    for (j = i - increment; j >=0 && array[j] > temp ; j-=increment) {
                        array[j + increment] = array[j];
                    }
                    array[j + increment] = temp;
                }
            }
        }while (increment >1);
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 逆序
     */
    @Test
    public void testDesc(){
        Integer[] array = array1;
        int increment = array.length;
        do{
            increment = increment / 3 + 1;
            for (int i = increment; i < array.length; i++) {
                if (array[i] > array[i-increment]){
                    int temp = array[i];
                    int j;
                    for (j = i - increment; j >=0 && array[j] < temp ; j-=increment) {
                        array[j + increment] = array[j];
                    }
                    array[j + increment] = temp;
                }
            }
        }while (increment >1);
        Arrays.asList(array).stream().forEach(System.out::println);
    }


    /**
     *希尔排序 Shell Sort
     *      希尔排序是直接插入排序的改进版
     *      采用跳跃分割的策略，将相距某个“增量”的记录组成一个子序列，保证在子序列内分别进行直接插入排序后得到的结果是基本有序而不是局部有序
     *      所谓序列基本有序，就是小的关键字基本在前面，大的基本在后面，不大不小的正好在中间
     * 希尔排序的关键是增量选取：目前是increment = increment/3 + 1;至于为什么，不知道
     *      大量研究表明，当增量序列为：dlta[k]=(2^(t-k+1)) - 1 其中（0<=k<=t<=|log2(n+1)|）时，可以获得不错的效率，
     * 时间复杂度
     *      为O(n^(3/2)),好于直接排序的O(n^2)
     * 性能
     *      希尔排序 > 直接插入排序法 > 简单选择排序法 > 冒泡排序法
     *
     *
     */
}
