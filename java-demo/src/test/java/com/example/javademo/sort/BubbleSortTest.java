package com.example.javademo.sort;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 冒泡排序 Bubble Sort
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/03
 **/
public class BubbleSortTest extends JavaDemoApplicationTests {

    private Integer[] array1 = {9,1,5,8,3,7,4,6,2};

    private Integer[] array2 = {2,1,5,6,7,8,9,11,12};

    /**
     * 冒泡排序-初级版
     * 缺点
     *     算法效率低
     *     不算真正的冒泡排序，不满足两两比较相邻元素
     */
    @Test
    public void test1(){
        Integer[] array = array1;
        for (int i = 0; i < array.length ; i++) {
            for (int j = i + 1; j < array.length ; j++) {
                if(array[i] > array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 冒泡排序-优化版
     * 缺点
     *     如果遇到i后面都是正确排序时，不会立即停止，还会继续遍历，虽然没有发生交换
     */
    @Test
    public void test2(){
        Integer[] array = array1;
        for (int i = 0; i < array.length ; i++) {
            for (int j = array.length-2; j >=i ; j--) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 冒泡排序-最终版
     * 添加flag标识有效避免了因已经有序的情况下的无意义循环判断
     */
    @Test
    public void testAsc(){
        Integer[] array = array1;
        // 判断是否发生的交换，初始值是true
        boolean flag = true;
        // 若flag为false则退出循环
        for (int i = 0; i < array.length && flag ; i++) {
            flag = false;
            for (int j = array.length-2; j >=i ; j--) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    // 若发生元素交换则flag设置为true
                    flag = true;
                }
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    @Test
    public void testDesc(){
        Integer[] array = array1;
        // 判断是否发生的交换，初始值是true
        boolean flag = true;
        // 若flag为false则退出循环
        for (int i = 0; i < array.length && flag ; i++) {
            flag = false;
            for (int j = array.length-2; j >=i ; j--) {
                if(array[j] < array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    // 若发生元素交换则flag设置为true
                    flag = true;
                }
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }


    /**
     * 冒泡排序 Bubble Sort
     *      基本思想：两两比较相邻记录的关键字，如果反序则交换，直到没有反序的记录为止
     *
     * 根据冒泡排序最终版
     * 时间复杂度
     *      最好情况，是排序表本身就是有序的，比较次数为n-1，时间复杂度为O(n)
     *      最坏情况，是排序表所有数据都是逆序的，比较次数为：1+2+3+...+(n-1) = n*(n-1)/2,时间复杂度为O(n^2)
     * 因此冒泡排序的时间复杂度为O(n^2)
     *
     *
     *
     */
}
