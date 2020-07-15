package com.example.javademo.datastructure.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/26
 **/
public class MyArrayStack<E> {

    /**
     * top变量指向栈顶元素所在下标
     */
    private int top = -1;

    private Object[] elements;

    public MyArrayStack(int initCapacity) {
        this.elements = new Object[initCapacity];
    }

    /**
     * 进栈
     * @param data
     * @return
     */
    public boolean push(E data){
        // 栈满
        if(top == elements.length -1){
            return false;
        }
        elements[++top] = data;
        return true;
    }

    /**
     * 出栈
     * @return
     */
    public E pop(){
        if(top == -1){
            throw new EmptyStackException();
        }
        int index = top;
        top--;
        return (E)elements[index];
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public E getTop(){
        if(top == -1){
            throw new EmptyStackException();
        }
        return (E)elements[top];
    }

    /**
     * 栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return top == -1 ? true : false;
    }

    /**
     * 栈的元素个数
     * @return
     */
    public int stackSize(){
        return top == -1 ? 0 : top+1;
    }

    /**
     * 清空栈
     */
    public void clearStack(){
        elements = new Object[elements.length];
        top = -1;
    }

    public static void main(String[] args) {
        MyArrayStack<Integer> myArrayStack = new MyArrayStack<>(16);
        for (int i = 0; i < 15; i++) {
            myArrayStack.push(i);
        }
        System.out.println(myArrayStack.push(20));
        System.out.println(myArrayStack.push(21));
        System.out.println(myArrayStack.pop());
        System.out.println(myArrayStack.pop());
        System.out.println(myArrayStack.pop());
        System.out.println(myArrayStack.getTop());
        System.out.println(myArrayStack.isEmpty());
        System.out.println(myArrayStack.stackSize());
        myArrayStack.clearStack();
        System.out.println(myArrayStack.stackSize());
        System.out.println(myArrayStack.isEmpty());
        System.out.println(myArrayStack.getTop());
    }

}
