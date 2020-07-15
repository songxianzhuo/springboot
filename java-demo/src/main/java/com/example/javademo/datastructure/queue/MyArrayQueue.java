package com.example.javademo.datastructure.queue;

/**
 * 描述 循环队列
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/26
 **/
public class MyArrayQueue<E> {

    private int front = 0;

    private int rear = 0;

    private Object[] elements;

    public MyArrayQueue(int initCapacity) {
        elements = new Object[initCapacity];
    }

    /**
     * 入队
     * @param data
     * @return
     */
    public void enQueue(E data) throws Exception {
        // 队列是否已满
        if((rear+1) % elements.length == front){
            throw new Exception("队列已满");
        }
        elements[rear] = data;
        // rear指针向后移一位
        rear = (rear+1) % elements.length;
    }

    /**
     * 出队
     * @return
     */
    public E deQueue() throws Exception {
        // 队列是否为空
        if(size() == 0){
            throw new Exception("队列为空");
        }
        E data = (E)elements[front];
        elements[front] = null;
        // front指针向前后一位
        front = (front+1) % elements.length;
        return data;
    }

    /**
     * 获取队头元素
     * @return
     * @throws Exception
     */
    public E getFront() throws Exception {
        // 队列是否为空
        if(size() == 0){
            throw new Exception("队列为空");
        }
        return (E)elements[front];
    }

    /**
     * 队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return size() == 0 ? true : false;
    }

    /**
     * 队列长度
     * @return
     */
    public int size(){
        return (rear-front+elements.length) % elements.length;
    }

    /**
     * 清空队列
     */
    public void clearQueue(){
        elements = new Object[elements.length];
        front = rear = 0;
    }

    public static void main(String[] args) {
        MyArrayQueue<Integer> myArrayQueue = new MyArrayQueue<>(16);
        for (int i = 0; i < 15; i++) {
            try {
                myArrayQueue.enQueue(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println(myArrayQueue.deQueue());
            System.out.println(myArrayQueue.deQueue());
            System.out.println(myArrayQueue.deQueue());
            System.out.println(myArrayQueue.deQueue());
            System.out.println(myArrayQueue.getFront());
            myArrayQueue.enQueue(20);
            myArrayQueue.enQueue(21);
            System.out.println(myArrayQueue.isEmpty());
            System.out.println(myArrayQueue.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
