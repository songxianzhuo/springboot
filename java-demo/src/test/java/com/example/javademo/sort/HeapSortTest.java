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
    private Integer[] array2 = {20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
    private Integer[] array3 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};


    /**
     * 正序
     */
    @Test
    public void testAsc(){
        Integer[] array = array3;
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
    private void heapAdjust(Integer[] array,int s,int m){
        int x = s;
        int y = 0;
        int z = 0;
        Integer temp = array[s];
        for (int j = 2 * s; j <= m ; j*=2) {
            // j<m说明array[j]不是最后一个结点
            if(j < m && array[j] < array[j+1]){
                ++j;
            }
            y++;
            // 如果temp大于等于双亲结点的左右孩子的较大者，则temp可以替换为当前结点的双亲结点
            if(temp >= array[j]){
                break;
            }
            // 左右孩子结点中较大的交换到双亲结点
            array[s] = array[j];
            // 将当前结点下标赋值为s
            s=j;
            z++;
        }
        array[s] = temp;
        System.out.println("下标：" + x + " 比较：" + 2*y + " 交换：" + z);
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
     * 堆是一棵完全二叉树，具有以下性质：
     *      每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
     *      或者每个结点的值都小于等于其左右孩子结点的值，称为小顶堆；
     * 堆按层序方式从左至右从数字1开始编号，则结点之间满足一下关系：
     *      Ki >= K2i Ki >= K2i+1
     *      或
     *      Ki <= K2i Ki <= K2i+1
     *      1 <= i <= |n/2|,n为结点数
     *      根结点一定是堆中所有结点的最大或最小值；
     * 堆排序 Heap Sort 是简单选择排序的改进版
     *      基本思想：将待排序的序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根结点，将它移走（其实就是将它与堆数组的末尾元素交换），此时末尾元素就是最大值，
     *      然后将剩余的n-1个序列重新构成一个堆，这样就会得到n个元素中的次大值，如此反复执行，便能得到一个有序序列了
     * 时间复杂度：复杂度分析链接：https://blog.csdn.net/qq_34228570/article/details/80024306
     *      运行时间主要消耗在初始构建堆和重建堆时的反复筛选上；
     *      时间复杂度为O(nlogn)
     * 性能：
     *      堆排序 > 直接插入排序法 > 简单选择排序法 > 冒泡排序法
     *
     */
}
