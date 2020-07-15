package com.example.javademo.datastructure;

import java.util.Arrays;

/**
 * 描述 数组
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/24
 **/
public class MyArrayList<E> {


    private int size;

    private Object[] elements;

    private final int default_init_capacity = 16;

    public MyArrayList(){
        elements = new Object[default_init_capacity];
    }


    public MyArrayList(int initCapacity){
        elements = new Object[initCapacity];
    }

    /**
     * 获取
     * @param index
     * @return
     */
    public E get(int index){
        checkSize(index);
        return (E)elements[index];
    }

    /**
     * 添加
     * @param e
     * @return
     */
    public boolean add(E e){
        ensureCapacity(size+1);
        elements[size] = e;
        size++;
        return true;
    }

    /**
     * 插入
     * @param e
     * @param index
     * @return
     */
    public void add(E e,int index){
        checkAddSize(index);
        ensureCapacity(size + 1);
        // index及之后元素往后移一位
        System.arraycopy(elements,index,elements,index+1,size-index);
        elements[index] = e;
        size++;
    }

    /**
     * 删除
     * @param index
     * @return
     */
    public E remove(int index){
        checkSize(index);
        E oldValue = get(index);
        // index之后元素往前移一位
        System.arraycopy(elements,index+1,elements,index,size-index -1);
        size--;
        return oldValue;
    }

    /**
     * 保证数组容量可以容得下新增元素
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity){
        // 数组长度不够，则进行2倍扩容
        if(elements.length < minCapacity){
            elements = Arrays.copyOf(elements,2*size);
        }
    }

    /**
     * 查询和删除检查索引
     * @param index
     */
    private void checkSize(int index){
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("超出数组边界");
        }
    }

    /**
     * 添加检查索引
     * @param index
     */
    private void checkAddSize(int index){
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("超出数组边界");
        }
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        for (int i = 0; i < 100; i++) {
            myArrayList.add(i);
        }
        myArrayList.add(101,100);
        myArrayList.remove(15);
        for (int i = 0; i < myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i));
        }
    }
}
