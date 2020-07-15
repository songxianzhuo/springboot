package com.example.javademo.datastructure;

import java.util.NoSuchElementException;

/**
 * 单向链表
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/24
 **/
public class MyLinkedList<E> {

    /**
     * 元素个数
     */
    private int size = 0;
    /**
     * 头部结点
     */
    private Node<E> first;
    /**
     * 尾部结点
     */
    private Node<E> last;

    public MyLinkedList() {
    }

    /**
     * 查询
     * @param index
     * @return
     */
    public E get(int index){
        checkSize(index);
        return getNode(index).data;
    }

    /**
     * 添加
     * @param data
     * @return
     */
    public boolean add(E data){
        addLast(data);
        return true;
    }

    /**
     * 插入
     * @param data
     * @param index
     * @return
     */
    public void add(E data,int index){
        checkAddSize(index);
        // 头部插入
        if(index == 0){
            addFirst(data);
        }else if(index == size){// 尾部插入
            addLast(data);
        }else{// 中间插入
            // 获取index处前驱结点
            Node<E> prev = getNode(index -1);
            Node newNode = new Node(data,prev.next);
            prev.next = newNode;
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
        // 头部删除
        if(index == 0){
            return removeFirst();
        }else if (index == size-1){// 尾部删除
            return removeLast();
        }else{// 中间删除
            // index处前驱结点
            Node<E> prev = getNode(index -1);
            Node<E> currentNode = prev.next;
            E data = currentNode.data;
            prev.next = currentNode.next;
            currentNode.data = null;
            currentNode.next = null;
            size--;
            return data;
        }
    }

    public int size(){
        return size;
    }

    /**
     * 头部添加
     * @param data
     */
    public void addFirst(E data){
        Node<E> f = first;
        Node<E> newNode = new Node<>(data,f);
        first = newNode;
        // f为null说明是空表
        if(f == null){
            last = newNode;
        }
        size++;
    }

    /**
     * 尾部添加
     * @param data
     */
    public void addLast(E data){
        Node<E> l = last;
        Node<E> newNode = new Node<>(data,null);
        last = newNode;
        // l为null说明是空表
        if(l == null){
            first = newNode;
        }else{
            l.next = newNode;
        }
        size++;
    }

    /**
     * 头部删除
     * @return
     */
    public E removeFirst(){
        if(first == null){
            throw new NoSuchElementException();
        }
        Node<E> f = first;
        Node<E> next = first.next;
        E data = f.data;
        f.data = null;
        f.next = null;
        first = next;
        // next为null说明只有一个元素
        if(next == null){
            last = null;
        }
        size--;
        return data;
    }

    /**
     * 尾部删除
     * @return
     */
    public E removeLast(){
        if(last == null){
            throw new NoSuchElementException();
        }
        Node<E> l = last;
        E data = l.data;
        // last == first说明只有一个元素
        if(size == 1){
            first = last = null;
        }else{
            Node<E>  prev = getNode(size -2);
            prev.next = null;
            last = prev;
        }
        l.data = null;
        l.next = null;
        size--;
        return data;
    }

    /**
     * 获取结点
     * @param index
     * @return
     */
    private Node<E> getNode(int index){
        Node<E> node = first;
        for (int i = 1; i <= index ; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 查询和删除检查索引
     * @param index
     */
    private void checkSize(int index){
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("超出链表结点范围");
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

    private static class Node<E>{
        E data;
        Node<E> next;

        public Node(E data,Node<E> next){
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 100; i++) {
            myLinkedList.add(i);
        }
        System.out.println(myLinkedList.removeFirst());
        System.out.println(myLinkedList.removeLast());
        System.out.println(myLinkedList.remove(10));
        myLinkedList.addFirst(1000);
        myLinkedList.addLast(1000);
        myLinkedList.add(1000,50);
        System.out.println("size:" + myLinkedList.size());
        for (int i = 0; i < myLinkedList.size; i++) {
            System.out.println(myLinkedList.get(i));
        }
    }
}
