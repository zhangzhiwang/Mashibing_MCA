package com.mashibing.dailyPractice.round1._1_29;

import com.mashibing.tree.BinaryTreeUtil;
import com.mashibing.tree.TreeNode;

/**
 * 二叉树的前序、中序、后序遍历（递归方式）
 */
public class BinaryTree_0129 {
    public static void pre(TreeNode<Integer> node) {
        if(node == null){
            return;
        }

        System.out.print(node.value + "\t");
        pre(node.left);
        pre(node.right);
    }

    public static void mid(TreeNode<Integer> node) {
        if(node == null){
            return;
        }

        mid(node.left);
        System.out.print(node.value + "\t");
        mid(node.right);
    }

    public static void post(TreeNode<Integer> node) {
        if(node == null){
            return;
        }

        post(node.left);
        post(node.right);
        System.out.print(node.value + "\t");
    }

    public static void main(String[] args) {
        TreeNode<Integer> node1 = new TreeNode<>(1);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node3 = new TreeNode<>(3);
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node5 = new TreeNode<>(5);
        TreeNode<Integer> node6 = new TreeNode<>(6);
        TreeNode<Integer> node7 = new TreeNode<>(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        BinaryTreeUtil.printBinaryTree(node1);
//        pre(node1);
//        System.out.println();
//        mid(node1);
//        post(node1);
    }
}
