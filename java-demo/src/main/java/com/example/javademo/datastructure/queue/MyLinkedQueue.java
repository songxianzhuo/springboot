package com.example.javademo.datastructure.queue;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/26
 **/
public class MyLinkedQueue<E> {

    private int size;

    private Node<E> front;

    private Node<E> rear;

    public MyLinkedQueue() {
    }

    /**
     * 入队
     * @param data
     */
    public void enQueue(E data){
        Node<E> r = rear;
        Node<E> newNode = new Node<>(data,null);
        rear = newNode;
        if(r == null){
            front = newNode;
        }else {
            r.next = newNode;
        }
        size++;
    }

    /**
     * 出队
     * @return
     * @throws Exception
     */
    public E deQueue() throws Exception {
        if(front == null){
            throw new Exception("队列为空");
        }
        Node<E> f = front;
        Node<E> next = front.next;
        front = next;
        if(next == null){
            rear = null;
        }
        E data = f.data;
        f.data = null;
        f.next = null;
        size--;
        return data;
    }

    /**
     * 获取队头结点
     * @return
     * @throws Exception
     */
    public E getFront() throws Exception {
        if(front == null){
            throw new Exception("队列为空");
        }
        return front.data;
    }

    /**
     * 队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return front == null ? true : false;
    }

    /**
     * 队列长度
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 清空队列
     */
    public void clearQueue(){
        size = 0;
        front = rear = null;
    }

    private static class Node<E>{
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        MyLinkedQueue<Integer> myLinkedQueue = new MyLinkedQueue<>();
        for (int i = 0; i < 50; i++) {
            myLinkedQueue.enQueue(i);
        }
        try {
            System.out.println(myLinkedQueue.deQueue());
            System.out.println(myLinkedQueue.deQueue());
            System.out.println(myLinkedQueue.deQueue());
            System.out.println(myLinkedQueue.deQueue());
            System.out.println(myLinkedQueue.isEmpty());
            myLinkedQueue.enQueue(101);
            myLinkedQueue.enQueue(102);
            System.out.println(myLinkedQueue.size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
