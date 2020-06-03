package com.example.javademo.sort;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 简单选择排序 Simple Selection Sort
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/03
 **/
public class SelectionSortTest extends JavaDemoApplicationTests {

    private Integer[] array1 = {9,1,5,8,3,7,4,6,2};

    /**
     * 正序
     */
    @Test
    public void testAsc(){
        Integer[] array = array1;
        for (int i = 0; i < array.length; i++) {
            int min = i;
            // 从n-i个记录总选出最小的记录下标赋值给min
            for (int j = i + 1; j < array.length; j++) {
                if(array[min] > array[j]){
                    min = j;
                }
            }
            // 循环后，如果min发生变化，说明array[i]不是最小值，将array[i]与array[min]互换
            if(min != i){
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
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
        for (int i = 0; i < array.length; i++) {
            int min = i;
            // 从n-i个记录总选出最小的记录下标赋值给min
            for (int j = i + 1; j < array.length; j++) {
                if(array[min] < array[j]){
                    min = j;
                }
            }
            // 循环后，如果min发生变化，说明array[i]不是最小值，将array[i]与array[min]互换
            if(min != i){
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 简单选择排序 Simple Selection Sort：
     *      基本思想：通过n-i次关键字间的比较，从n-i个记录中选出关键字最小的记录，并和第i(1<=i<=n)个记录交换之
     * 时间复杂度：
     *      最好最坏情况下，比较次数一样，为：1+2+3+...+(n-1) = n*(n-1)/2,时间复杂度为O(n^2)
     *      交换次数，最好情况是0次，最坏情况是初始降序时，是n-1次
     *      因此，总的时间复杂度为O(n^2)
     * 性能：
     *      简单选择排序法 > 冒泡排序法
     *
     *
     *
     */
}
