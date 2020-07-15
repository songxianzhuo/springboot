package com.example.javademo.datastructure;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 静态链表
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/24
 **/
public class MyStaticLinkedList<E> {

    private int size;
    /**
     * 备用连表首个元素下标
     */
    private int first = 0;
    /**
     * 首个有值元素下标
     */
    private int last = -1;

    private Node<E>[] elements;

    private final int default_init_capacity = 16;

    public MyStaticLinkedList() {
        elements = new Node[default_init_capacity];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Node<>(null,i+1,i);
        }
    }

    public E get(int index){
        checkSize(index);
        return getNode(index).data;
    }

    /**
     * 添加
     * @param data
     * @return
     */
    public Boolean add(E data){
        ensureCapacity(size +1);
        addLast(data);
        return true;
    }

    /**
     * 插入
     * @param data
     * @param index
     */
    public void add(E data,int index){
        checkAddSize(index);
        ensureCapacity(size +1);
        if(index == 0){
            addFirst(data);
        }else if(index == size){
            addLast(data);
        }else{
            // 首个备用结点
            Node<E> spareNode = elements[first];
            // 前驱结点
            Node<E> prev = getNode(index-1);
            // first往后移位
            first = spareNode.next;
            // 备用结点变有值结点
            spareNode.data = data;
            spareNode.next = prev.next;
            // 前驱结点的next指向新结点下标
            prev.next = spareNode.index;
            size++;
        }
    }

    /**
     * 删除
     * @param index
     * @return
     */
    public E remove(int index){
        checkSize(index);
        if(index == 0){
            return removeFirst();
        }else if(index == size -1){
            return removeLast();
        }else{
            // 尾部结点的前驱结点
            Node<E> prev = getNode(index-1);
            // 当前结点
            Node<E> currentNode = elements[prev.next];
            E data = currentNode.data;
            // 前驱结点指向当前结点的后驱结点下标
            prev.next = currentNode.next;
            // 当前结点变为备用链表的首结点
            currentNode.next = first;
            currentNode.data = null;
            // first指向当前结点下标
            first = currentNode.index;
            size--;
            return data;
        }
    }

    /**
     * 获取Node
     * @param index
     * @return
     */
    private Node<E> getNode(int index){
        int next = last;
        for (int i = 1; i <= index; i++) {
            next = elements[next].next;
        }
        return elements[next];
    }

    /**
     * 头部添加
     * @param data
     */
    public void addFirst(E data){
        ensureCapacity(size +1);
        // 首个备用结点
        Node<E> spareNode = elements[first];
        // first往后移位
        first = spareNode.next;
        // 备用结点变有值结点
        spareNode.data = data;
        spareNode.next = last;
        // last执行新的头结点
        last = spareNode.index;
        size++;
    }

    /**
     * 尾部添加
     * @param data
     */
    public void addLast(E data){
        ensureCapacity(size +1);
        // 首个备用结点
        Node<E> spareNode = elements[first];
        // first往后移位
        first = spareNode.next;
        // 备用结点变有值结点
        spareNode.data = data;
        spareNode.next = -1;
        // last=-1说明是空表，那么新结点也就变成头结点
        if(last == -1){
            // last执行新的头结点
            last = spareNode.index;
        }else{
            // 获取最后结点的前驱结点
            Node<E> prev = getNode(size-1);
            // 前一结点的next指向新结点下标
            prev.next = spareNode.index;
        }
        size++;
    }

    /**
     * 头部删除
     * @return
     */
    public E removeFirst(){
        if(last == -1){
            throw new NoSuchElementException();
        }
        // 头部结点
        Node<E> currentNode = elements[last];
        // last指向头部结点的后驱结点
        last = currentNode.next;
        E data = currentNode.data;
        // 头部结点变成备用结点
        currentNode.data = null;
        currentNode.next = first;
        first = currentNode.index;
        size--;
        return data;
    }

    /**
     * 尾部删除
     * @return
     */
    public E removeLast(){
        if(last == -1){
            throw new NoSuchElementException();
        }
        // 尾部结点
        Node<E> currentNode = getNode(size -1);
        // 只有一个元素
        if(size == 1){
            last = -1;
        }else{
            // 尾部结点的前驱结点
            Node<E> prev = getNode(size-2);
            // 前驱结点指向-1；
            prev.next = -1;
        }
        E data = currentNode.data;
        currentNode.next = first;
        currentNode.data = null;
        first = currentNode.index;
        size--;
        return data;
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
     * 插入检查索引
     * @param index
     */
    private void checkAddSize(int index){
        if(index > size || index >= elements.length || index < 0){
            throw new IndexOutOfBoundsException("超出数组边界");
        }
    }

    /**
     *
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity){
        if(minCapacity > elements.length){
            elements = Arrays.copyOf(elements,2*size);
            for (int i = size; i < elements.length; i++) {
                elements[i] = new Node<>(null,i+1,i);
            }
            first = size;
        }
    }

    public int size(){
        return size;
    }

    private static class Node<E> {
        E data;
        int next;
        int index;
        public Node(E data,int next,int index){
            this.data = data;
            this.next = next;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        MyStaticLinkedList<Integer> myStaticLinkedList = new MyStaticLinkedList<>();
        for (int i = 0; i < 100; i++) {
            myStaticLinkedList.add(i);
        }
        System.out.println(myStaticLinkedList.removeFirst());
        System.out.println(myStaticLinkedList.removeLast());
        System.out.println(myStaticLinkedList.remove(10));
        myStaticLinkedList.addFirst(1000);
        myStaticLinkedList.addLast(1000);
        myStaticLinkedList.add(1000,50);
        System.out.println("size:" + myStaticLinkedList.size());
        for (int i = 0; i < myStaticLinkedList.size; i++) {
            System.out.println(myStaticLinkedList.get(i));
        }
    }
}
