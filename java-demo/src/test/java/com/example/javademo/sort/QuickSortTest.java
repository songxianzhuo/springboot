package com.example.javademo.sort;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 快速排序 Quick Sort
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/06
 **/
public class QuickSortTest extends JavaDemoApplicationTests {

    private Integer[] array1 = {9,1,5,8,3,7,4,6,2};

    @Test
    public void testAsc(){
        Integer[] array = array1;
        quickSort(array,0,array.length -1);
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    private void quickSort(Integer[] array,int startIndex,int endIndex){
        if(startIndex >= endIndex){
            return;
        }
        // 获取枢轴值下标
        int pivotIndex = partition(array,startIndex,endIndex);
        // 以枢轴值为分界线，将数组一分为二
        quickSort(array,startIndex,pivotIndex -1);
        quickSort(array,pivotIndex +1,endIndex);
    }

    /**
     * 分而治之-双边循环法
     * 将第一个元素作为基准元素，然后使得基准元素左边的元素比它小，右边的元素比它大
     * 注意：基准元素也可以随机选择，如果原本是逆序的数组改为正序，如果采用第一个元素，就起不到分而治之的效果
     * @param array
     * @param startIndex 开始索引
     * @param endIndex 结束索引
     * @return
     */
    private int partition(Integer[] array,int startIndex,int endIndex){
        // 将序列的第一个元素当做基准元素
        int pivot = array[startIndex];
        // 左指针
        int left = startIndex;
        // 右指针
        int right = endIndex;
        // 从序列的两端交替向中间扫描
        while (left < right){
            // 从右至左遍历
            while (left < right && array[right] >= pivot){
                right--;
            }
            // 从左至右遍历
            while (left < right && array[left] <= pivot){
                left++;
            }
            // 此时，左指针元素大于基准元素，右指针元素小于基准元素，所有将左右指针元素互换
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
        // 将基准元素和left指针元素互换
        array[startIndex] = array[left];
        array[left] = pivot;
        return left;
    }


    /**
     * 分而治之-单边循环法
     * 将第一个元素作为基准元素，然后使得基准元素左边的元素比它小，右边的元素比它大
     * @param array
     * @param startIndex
     * @param endIndex
     * @return
     */
    public int partition2(Integer[] array,int startIndex,int endIndex){
        // 将序列的第一个元素当做基准元素
        int pivot = array[startIndex];
        // mark指针指向数列起始位置，mark指针代表小于基准元素的区域边界
        int mark = startIndex;
        for (int i = startIndex+1; i <= endIndex; i++) {
            // 当遍历元素小于基准元素时，将遍历元素和mark指针元素进行互换
            if(array[i] < pivot){
                // mark +1,标识小于基准元素的边界增加1
                mark++;
                int temp = array[mark];
                array[mark] = array[i];
                array[i] = temp;
            }
        }
        // 将基准元素和left指针元素互换
        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    /**
     * 元素交换
     * @param array
     * @param low
     * @param high
     */
    private void swap(Integer[] array,int low,int high){
        int temp = array[high];
        array[high] = array[low];
        array[low] = temp;
    }

    /**
     * 快速排序 Quick Sort 是冒泡排序的改进版
     *      基本思想：分而治之，每轮挑选一个基准元素，通过一趟排序将待排序记录分割成独立的两部分，其中一部分记录的关键字比基准元素小，
     *                  另一部分的关键字比基准元素大，则可以分别对这两部分记录继续分而治之，以达到整个序列有序的目的
     *  时间复杂度
     *      平均时间复杂度是O(nlogn)
     *      最坏时间复杂度是O(n^2)：
     *
     */
}
