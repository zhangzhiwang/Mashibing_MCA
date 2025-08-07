package com.mashibing.dailyPractice.round5;

import com.mashibing.tree.TreeNode;

/**
 * 二叉树的前序、中序、后序遍历（递归方式）
 */
public class BinaryTree_0830 {
    public static void pre(TreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        System.out.print(root + "\t");
        pre(root.left);
        pre(root.right);
    }

    public static void in(TreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        in(root.left);
        System.out.print(root + "\t");
        in(root.right);
    }

    public static void post(TreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        post(root.left);
        post(root.right);
        System.out.print(root + "\t");
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

        pre(node1);
        System.out.println();
        in(node1);
        System.out.println();
        post(node1);
        System.out.println();
    }
}
