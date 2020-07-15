package com.example.javademo.datastructure.stack;

import java.util.EmptyStackException;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/26
 **/
public class MyLinkedStack<E> {

    private int size = 0;

    private Node<E> top;

    public MyLinkedStack() {
    }

    /**
     * 进栈
     * @param data
     * @return
     */
    public boolean push(E data){
        top = new Node<>(data,top);
        size++;
        return true;
    }

    /**
     * 出栈
     * @return
     */
    public E pop(){
        if(size == 0){
            throw new EmptyStackException();
        }
        E data = top.data;
        top = top.next;
        size--;
        return data;
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public E getTop(){
        if(size == 0){
            throw new EmptyStackException();
        }
        return top.data;
    }

    /**
     * 栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0 ? true : false;
    }

    /**
     * 栈的元素个数
     * @return
     */
    public int stackSize(){
        return size;
    }

    /**
     * 清空栈
     */
    public void clearStack(){
        top = null;
        size = 0;
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
        MyLinkedStack<Integer> myLinkedStack = new MyLinkedStack<>();
        for (int i = 0; i < 15; i++) {
            myLinkedStack.push(i);
        }
        System.out.println(myLinkedStack.push(20));
        System.out.println(myLinkedStack.push(21));
        System.out.println(myLinkedStack.pop());
        System.out.println(myLinkedStack.pop());
        System.out.println(myLinkedStack.pop());
        System.out.println(myLinkedStack.getTop());
        System.out.println(myLinkedStack.isEmpty());
        System.out.println(myLinkedStack.stackSize());
        myLinkedStack.clearStack();
        System.out.println(myLinkedStack.stackSize());
        System.out.println(myLinkedStack.isEmpty());
        System.out.println(myLinkedStack.getTop());
    }
}
