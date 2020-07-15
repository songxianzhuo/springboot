package com.example.javademo.datastructure.tree;

/**
 * 描述 二叉排序树
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/13
 **/
public class MyBinarySearchTree<E> {

    private TreeNode<E> root;


    public void insert(){

    }

    public E get(E key){
        if(root == null){
            return null;
        }

        return null;
    }

    private TreeNode<E> search(E key,TreeNode node,TreeNode<E> parentNode){
        return null;
    }

    private static class TreeNode<E>{
        E data;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode() {
        }
    }
}
