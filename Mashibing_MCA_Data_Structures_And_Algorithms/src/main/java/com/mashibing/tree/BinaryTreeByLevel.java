package com.mashibing.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树按层遍历
 * 思路：使用栈，有左孩子就把左孩子加入栈，有右孩子就把右孩子加入栈
 * 课程：体系班课时90
 */
public class BinaryTreeByLevel {
    public static void binaryTreeByLevel(TreeNode<Integer> head) {
        if(head == null) {
            return;
        }

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        while(!queue.isEmpty()) {
            TreeNode<Integer> poll = queue.poll();
            System.out.print(poll.value + "\t");
            if(poll.left != null) {
                queue.add(poll.left);
            }
            if(poll.right != null) {
                queue.add(poll.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode<Integer> n1 = new TreeNode<>(1);
        TreeNode<Integer> n2 = new TreeNode<>(2);
        TreeNode<Integer> n3 = new TreeNode<>(3);
        TreeNode<Integer> n4 = new TreeNode<>(4);
        TreeNode<Integer> n5 = new TreeNode<>(5);
        TreeNode<Integer> n6 = new TreeNode<>(6);
        TreeNode<Integer> n7 = new TreeNode<>(7);
        TreeNode<Integer> n8 = new TreeNode<>(8);
        TreeNode<Integer> n9 = new TreeNode<>(9);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n4.right = n7;
        n5.left = n8;
        n3.left = n6;
        n6.right = n9;

        binaryTreeByLevel(n1);
    }
}
