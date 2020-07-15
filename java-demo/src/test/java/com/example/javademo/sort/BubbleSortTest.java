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
     * 外部循环控制所有的回合，内部循环进行冒泡处理,最多比较array.length-1次
     * 缺点
     *     如果数组本身就是有序的如[1,2,3,4,5,6,7,8,9]，该方法依然会从头到尾遍历一下的
     */
    @Test
    public void testAsc(){
        Integer[] array = array1;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
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
     * 冒泡排序-优化版1
     * 优化：添加flag标识有效避免了因已经有序的情况下的无意义循环判断
     * 缺点：如果数组是部分有序，如[4,3,2,1,5,6,7,8,9]，在内层循环中依然会遍历有序部分元素
     */
    @Test
    public void testAsc1(){
        Integer[] array = array1;
        for (int i = 0; i < array.length-1; i++) {
            // 判断是否发生的交换，初始值是true
            boolean flag = true;
            for (int j = 0; j< array.length-i-1; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    // 若发生元素交换则flag设置为false
                    flag = false;
                }
            }
            // 若flag为true说明序列已全部有序，则退出循环
            if (flag){
                break;
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 冒泡排序-优化版2
     * 优化：添加数组中无序序列的边界
     * 缺点：对于已经大部分有序的数组，如[2,3,4,5,6,7,8,1,9]，其实只需要移动元素1就可以了。但是目前的冒泡排序依然要遍历8轮，可以使用鸡尾酒排序解决
     */
    @Test
    public void testAsc2(){
        Integer[] array = array1;
        // 无序边界
        int sortBorder = array.length-1;
        for (int i = 0; i < array.length-1; i++) {
            // 判断是否发生的交换，初始值是true
            boolean flag = true;
            // 最后一次交换的位置
            int lastExchangeIndex = 0;
            for (int j = 0; j < sortBorder; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    // 若发生元素交换则flag设置为false
                    flag = false;
                    // 更新最后一次交换的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            // 若flag为true说明序列已全部有序，则退出循环
            if (flag){
                break;
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 鸡尾酒排序 是冒泡排序的改版
     * 缺点：没有添加无序边界
     */
    @Test
    public void cocktailSort(){
        Integer[] array = array1;
        for (int i = 0; i < array.length/2; i++) {
            // 判断是否发生的交换，初始值是true
            boolean flag = true;
            // 奇数轮，从左至右进行比较和交换
            for (int j = i; j < array.length-i-1; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    // 若发生元素交换则flag设置为false
                    flag = false;
                }
            }
            // 若flag为true说明序列已全部有序，则退出循环
            if (flag){
                break;
            }
            flag = true;
            // 偶数轮，从右至左进行比较和交换
            for (int j = array.length-i-2; j > i; j--) {
                if(array[j] < array[j-1]){
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    // 若发生元素交换则flag设置为false
                    flag = false;
                }
            }
            // 若flag为true说明序列已全部有序，则退出循环
            if (flag){
                break;
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 鸡尾酒排序-优化版1
     * 优化：添加无序边界
     */
    @Test
    public void cocktailSort2(){
        Integer[] array = array1;
        // 正向无序边界
        int sortBorder = array.length-1;
        // 逆向无序边界
        int sortBorder1 = 0;
        for (int i = 0; i < array.length/2; i++) {
            // 判断是否发生的交换，初始值是true
            boolean flag = true;
            // 最后一次交换的位置
            int lastExchangeIndex = 0;
            // 奇数轮，从左至右进行比较和交换
            for (int j = i; j < sortBorder; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    // 若发生元素交换则flag设置为false
                    flag = false;
                    // 更新最后一次交换的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            // 若flag为true说明序列已全部有序，则退出循环
            if (flag){
                break;
            }
            flag = true;
            // 偶数轮，从右至左进行比较和交换
            for (int j = array.length-i-2; j > sortBorder1; j--) {
                if(array[j] < array[j-1]){
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    // 若发生元素交换则flag设置为false
                    flag = false;
                    // 更新最后一次交换的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder1 = lastExchangeIndex;
            // 若flag为true说明序列已全部有序，则退出循环
            if (flag){
                break;
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }


    /**
     * 冒泡排序 Bubble Sort 比较和交换是单向的
     *      基本思想：两两比较相邻记录的关键字，如果反序则交换，直到没有反序的记录为止
     *
     * 时间复杂度
     *      最好情况，是排序表本身就是有序的，比较次数为n-1，时间复杂度为O(n)
     *      最坏情况，是排序表所有数据都是逆序的，比较次数为：1+2+3+...+(n-1) = n*(n-1)/2,时间复杂度为O(n^2)
     * 因此冒泡排序的时间复杂度为O(n^2)
     *
     * 鸡尾酒排序 Cocktail Sort 比较和交换的双向的，是冒泡排序的优化版
     *      基本思想：奇数轮从左至右进行比较和交换，偶数轮从右至左进行比较和交换
     *      适用于大部分元素已经有序的情况下
     * 时间复杂度与冒泡排序一样
     *
     *
     *
     */
}
