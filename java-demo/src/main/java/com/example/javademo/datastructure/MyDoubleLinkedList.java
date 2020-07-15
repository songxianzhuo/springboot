package com.example.javademo.datastructure;

import java.util.NoSuchElementException;

/**
 * 描述 双向链表
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/24
 **/
public class MyDoubleLinkedList<E> {

    private int size = 0;

    private Node<E> first;

    private Node<E> last;

    public MyDoubleLinkedList() {
    }

    public E get(int index){
        checkSize(index);
        return getNode(index).data;
    }

    public boolean add(E data){
        addLast(data);
        return true;
    }

    public void add(E data,int index){
        checkAddSize(index);
        // 头部插入
        if(index == 0){
            addFirst(data);
        }else if(index == size){// 尾部插入
            addLast(data);
        }else{// 中间插入
            // 当前结点
            Node<E> currentNode = getNode(index);
            // 前驱结点
            Node<E> prev = currentNode.prev;
            Node<E> newNode = new Node(data,prev,currentNode);
            prev.next = newNode;
            currentNode.prev = newNode;
            size++;
        }
    }

    public E remove(int index){
        checkSize(index);
        // 头部删除
        if(index == 0){
            return removeFirst();
        }else if (index == size-1){// 尾部删除
            return removeLast();
        }else{// 中间删除
            // 当前结点
            Node<E> currentNode = getNode(index);
            // 前驱结点
            Node<E> prev = currentNode.prev;
            // 后驱结点
            Node<E> next = currentNode.next;
            E data = currentNode.data;
            prev.next = next;
            next.prev = prev;
            currentNode.data = null;
            currentNode.prev = null;
            currentNode.next = null;
            size--;
            return data;
        }
    }

    public int size(){
        return size;
    }

    public void addFirst(E data){
        Node<E> f = first;
        Node<E> newNode = new Node<>(data,null,f);
        first = newNode;
        // f为null说明是空表
        if(f == null){
            last = newNode;
        }else{
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(E data){
        Node<E> l = last;
        Node<E> newNode = new Node<>(data,l,null);
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
        }else{
            // 将头部结点前驱元素置为null
            next.prev = null;
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
        Node<E> prev = last.prev;
        E data = l.data;
        l.data = null;
        l.prev = null;
        last = prev;
        // prev为null说明只有一个元素
        if(prev == null){
            first = null;
        }else{
            // 将尾部结点的后驱结点置为null
            prev.next = null;
        }
        size--;
        return data;
    }

    /**
     * 获取Node
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
        Node<E> prev;
        Node<E> next;

        public Node(E data, Node<E> prev,Node<E> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyDoubleLinkedList<Integer> myDoubleLinkedList = new MyDoubleLinkedList<>();
        for (int i = 0; i < 100; i++) {
            myDoubleLinkedList.add(i);
        }
        System.out.println(myDoubleLinkedList.removeFirst());
        System.out.println(myDoubleLinkedList.removeLast());
        System.out.println(myDoubleLinkedList.remove(10));
        myDoubleLinkedList.addFirst(1000);
        myDoubleLinkedList.addLast(1000);
        myDoubleLinkedList.add(1000,50);
        System.out.println("size:" + myDoubleLinkedList.size());
        for (int i = 0; i < myDoubleLinkedList.size; i++) {
            System.out.println(myDoubleLinkedList.get(i));
        }
    }
}
