package com.example.javademo.datastructure.tree;

import java.util.Arrays;

/**
 * 描述 优先队列 采用二叉堆实现
 * 最大优先队列，不管进队顺序，总是最大值优先出队 大顶堆实现
 * 最小优先队列，不管进队顺序，总是最小值优先出队
 * 以最大优先队列为例
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/13
 **/
public class MyPriorityQueue {

    private int size = 0;

    private int[] elements;

    private int default_capacity = 16;

    public MyPriorityQueue(int initCapacity) {
        elements = new int[initCapacity];
    }

    public MyPriorityQueue() {
    }

    /**
     * 入队
     * @param data
     */
    public void enQueue(int data){
        ensureCapacity(size +1);
        elements[size++] = data;
        // 队尾元素做上浮操作，以维持二叉堆状态
        upAdjust();
    }

    public int deQueue() throws Exception {
        if(size == 0){
            throw new Exception("the queue is empty");
        }
        // 获取堆顶元素
        int head = elements[0];
        // 将队尾元素移动至堆顶
        elements[0] = elements[size];
        // 让堆顶元素下浮，以维持二叉堆状态
        downAdjust();
        size--;
        return head;
    }

    private void ensureCapacity(int minCapacity){
        if(minCapacity > elements.length){
            elements = Arrays.copyOf(elements,2*size);
        }
    }

    /**
     * 上浮
     */
    public void upAdjust(){
        int childIndex = size-1;
        int parentIndex = (childIndex-1)/2;
        // temp保存插入的叶子结点值，用于最后的赋值
        int temp = elements[childIndex];
        while (childIndex > 0 && temp > elements[parentIndex]){
            // 无序真正交换，单向赋值即可
            elements[childIndex] = elements[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1)/2;
        }
        elements[childIndex] = temp;
    }

    /**
     * 下浮
     */
    public void downAdjust(){
        int parentIndex = 0;
        // temp保存父节点值，用于最后赋值
        int temp = elements[parentIndex];
        int childIndex = 2 * parentIndex +1;
        while (childIndex < size){
            // 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if (childIndex +1 < size && elements[childIndex+1] > elements[childIndex]){
                childIndex++;
            }
            // 如果父节点小于任意一个孩子的值，则直接跳出
            if (temp >= elements[childIndex]){
                break;
            }
            // 无须真正交换，单向赋值即可
            elements[parentIndex] = elements[childIndex];
            parentIndex = childIndex;
            childIndex = 2*parentIndex+1;
        }
        elements[parentIndex] = temp;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) throws Exception {
        MyPriorityQueue myPriorityQueue = new MyPriorityQueue(32);
        myPriorityQueue.enQueue(10);
        myPriorityQueue.enQueue(6);
        myPriorityQueue.enQueue(15);
        myPriorityQueue.enQueue(3);
        myPriorityQueue.enQueue(2);
        myPriorityQueue.enQueue(20);
        myPriorityQueue.enQueue(25);
        System.out.println(myPriorityQueue.deQueue());
        System.out.println(myPriorityQueue.deQueue());
        System.out.println(myPriorityQueue.deQueue());
        System.out.println(myPriorityQueue.deQueue());
        System.out.println(myPriorityQueue.deQueue());
    }
}
