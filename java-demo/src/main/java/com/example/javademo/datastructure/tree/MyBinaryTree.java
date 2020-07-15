package com.example.javademo.datastructure.tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 描述 二叉树 链表
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/12
 **/
public class MyBinaryTree<T> {


    /**
     * 生成二叉树
     * @param list
     * @return
     */
    public TreeNode<T> createTreeNode(LinkedList<T> list){
        TreeNode node = null;
        if(list == null || list.size() == 0){
            return null;
        }
        T data = list.removeFirst();
        if(data != null){
            node = new TreeNode(data);
            node.left = createTreeNode(list);
            node.right = createTreeNode(list);
        }
        return node;
    }

    /**
     * 前序遍历
     * 从根结点开始，先访问根结点，访问左子树，最后访问右子树
     */
    public void frontOut(TreeNode<T> treeNode){
        if (treeNode == null){
            return;
        }
        System.out.println(treeNode.data);
        frontOut(treeNode.left);
        frontOut(treeNode.right);
    }

    /**
     * 中序遍历
     * 从根结点开始，先访问左子树，再访问根结点，最后访问右子树
     */
    public void middleOut(TreeNode<T> treeNode){
        if (treeNode == null){
            return;
        }
        middleOut(treeNode.left);
        System.out.println(treeNode.data);
        middleOut(treeNode.right);
    }

    /**
     * 后序遍历
     * 从根结点开始，先访问左子树，再访问右子树，最后访问根结点
     */
    public void backOut(TreeNode<T> treeNode){
        if (treeNode == null){
            return;
        }
        backOut(treeNode.left);
        backOut(treeNode.right);
        System.out.println(treeNode.data);
    }

    private static class TreeNode<T> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        MyBinaryTree myBinaryTree = new MyBinaryTree<Integer>();
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4}));
        TreeNode<Integer> treeNode = myBinaryTree.createTreeNode(list);
        myBinaryTree.frontOut(treeNode);
        System.out.println("-----------");
        myBinaryTree.middleOut(treeNode);
//        myBinaryTree.backOut(treeNode);
    }
}
