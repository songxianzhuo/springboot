package com.example.javademo.sort;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 堆排序 Heap Sort
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/03
 **/
public class HeapSortTest extends JavaDemoApplicationTests {

    private Integer[] array1 = {9,1,5,8,3,7,4,6,2};


    /**
     * 正序
     */
    @Test
    public void testAsc(){
        Integer[] array = array1;
        // 构造初始大顶堆,从第一个非叶子节点开始调整,左右孩子结点中较大的交换到双亲结点中，完成后，堆顶结点就是序列中的最大值
        for (int i = array.length / 2; i >= 0; i--) {
            heapAdjust(array,i,array.length - 1);
        }
        for (int i = array.length -1; i > 0 ; i--) {
            // 将堆顶结点和堆尾结点交换，打破大顶堆平衡
            Integer temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            // 当当前索引大于1时，才会重构大顶堆
            if(i > 1){
                // 让堆尾之前的结点重新构成一个新的大顶堆
                heapAdjust(array,0,i - 1);
            }
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 构建大顶堆
     * @param array
     * @param s 当前结点对应序列下标
     * @param m 堆尾结点对应序列下标
     */
    private void      heapAdjust(Integer[] array,int s,int m){
        Integer temp = array[s];
        for (int j = 2 * s; j <= m ; j*=2) {
            // j<m说明array[j]不是最后一个结点
            if(j < m && array[j] < array[j+1]){
                ++j;
            }
            // 如果temp大于等于双亲结点的左右孩子的较大者，则temp可以替换为当前结点的双亲结点
            if(temp >= array[j]){
                break;
            }
            // 左右孩子结点中较大的交换到双亲结点
            array[s] = array[j];
            // 将当前结点下标赋值为s
            s=j;
        }
        array[s] = temp;
    }

    /**
     * 逆序
     */
    @Test
    public void testDesc(){
        Integer[] array = array1;
        // 构造初始小顶堆,从第一个非叶子节点开始调整,左右孩子结点中较小的交换到双亲结点中，完成后，堆顶结点就是序列中的最小值
        for (int i = array.length / 2; i >= 0; i--) {
            heapAdjust2(array,i,array.length - 1);
        }
        for (int i = array.length -1; i > 0 ; i--) {
            // 将堆顶结点和堆尾结点交换，打破小顶堆平衡
            Integer temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            // 让堆尾之前的结点重新构成一个新的小顶堆
            heapAdjust2(array,0,i - 1);
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 构建小顶堆
     * @param array
     * @param s 当前结点对应序列下标
     * @param m 堆尾结点对应序列下标
     */
    private void heapAdjust2(Integer[] array,int s,int m){
        Integer temp = array[s];
        for (int j = 2 * s; j <= m ; j*=2) {
            // j<m说明array[j]不是最后一个结点
            if(j < m && array[j] > array[j+1]){
                ++j;
            }
            // 如果temp小于等于双亲结点的左右孩子的较大者，则temp可以替换为当前结点的双亲结点
            if(temp <= array[j]){
                break;
            }
            // 左右孩子结点中较小的交换到双亲结点
            array[s] = array[j];
            // 将当前结点下标赋值为s
            s=j;
        }
        array[s] = temp;
    }

    /**
     * 堆排序 Heap Sort
     *      是简单选择排序的改进版
     *
     *
     *
     *
     *
     */
}
