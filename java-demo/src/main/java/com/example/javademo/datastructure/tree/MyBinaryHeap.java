package com.example.javademo.datastructure.tree;

import java.util.Arrays;

/**
 * 描述 二叉堆 数组 以小顶堆为例
 * 大顶堆：任意个父节点的值都大于等于左右孩子的值
 * 小顶堆：任意个父节点的值都小于等于左右孩子的值
 * 基本公式：
 * 在数组中，如果父节点下标是parent，则左孩子下标是2*parent+1，右孩子下标是2*parent+2；
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/13
 **/
public class MyBinaryHeap {


    /**
     * 上浮
     * @param array
     */
    public static void upAdjust(int[] array){
        int childIndex = array.length-1;
        int parentIndex = (childIndex-1)/2;
        // temp保存插入的叶子结点值，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]){
            // 无序真正交换，单向赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1)/2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下浮
     * @param array
     * @param parentIndex 父节点索引
     * @param length 堆的有效大小
     */
    public static void downAdjust(int[] array,int parentIndex,int length){
        // temp保存父节点值，用于最后赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex +1;
        while (childIndex < length){
            // 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if (childIndex +1 < length && array[childIndex+1] < array[childIndex]){
                childIndex++;
            }
            // 如果父节点小于任意一个孩子的值，则直接跳出
            if (temp <= array[childIndex]){
                break;
            }
            // 无须真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*parentIndex+1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 构建堆
     * @param array
     */
    public static void buildHeap(int[] array){
        // 从最后一个非叶子结点开始，依次做“下沉”调整
        for (int i = (array.length-2)/2; i >=0 ; i--) {
            downAdjust(array,i,array.length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{10,8,6,9,7,11,5,15,16,1};
        buildHeap(array);
        System.out.println(Arrays.toString(array));

        int[] array2 = new int[]{1,2,3,4,5,6,7,8,9,0};
        upAdjust(array2);
        System.out.println(Arrays.toString(array2));
    }
}
