package com.mashibing.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵二叉树是否为完全二叉树（非递归实现）
 * 解释：完全二叉树：
 * 1、情况一：任意节点既有左孩子又有右孩子
 * 2、情况二：任意节点既没有左孩子又没有右孩子，即叶子节点
 * 3、情况三：任意节点可以只有左孩子没有右孩子，这个叫做该节点在变满的路上
 * 如果任意子树都满足上面的三种情况之一，整棵树就是完全二叉树，或者简言之：完全二叉树就是所有节点要么是满的，要么在变满的路上（按层从上到下，每一层从左到右的顺序变满的路上）。
 *
 * 思路：
 * 1、按层级遍历二叉树（借助队列）
 * 2、设置一个布尔类型的变量，该变量表示在遍历二叉树的过程中是否遇到过在变满路上的节点（只有左孩子没有右孩子），如果遇到过这样的节点，那么在该节点之后都必须是叶子节点
 *
 * 课程：体系班课时99
 */
public class IsCBT {// Complete Binary Tree
    public static boolean isCBT(TreeNode<String> head) {
        if(head == null) {
            return true;// 人为规定空树是完全二叉树
        }

        Queue<TreeNode<String>> queue = new LinkedList<>();
        queue.add(head);
        boolean leaf = false;
        while(!queue.isEmpty()) {// 按层级遍历二叉树
            TreeNode<String> treeNode = queue.poll();
            if(treeNode.left == null && treeNode.right != null) {// 如果一个节点没有左孩子只有右孩子，那么以这个节点为头的子树就不是完全二叉树，那么整棵树就不是完全二叉树
                return false;
            }
            if(leaf && (treeNode.left != null || treeNode.right != null)) {// 如果在遍历到本节点之前遇到过叶子节点，那么本节点必须是叶子节点
                return false;
            }

            if(treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if(treeNode.right != null) {
                queue.add(treeNode.right);
            }

            /*
            中间是或者不是并且，或者包括两种情况：
            1、treeNode是叶子节点
            2、treeNode有左孩子但是没有右孩子（在路上）
            3、treeNode没有左孩子但是有右孩子，这种情况不存在，因为如果是这样在上面就已经返回false了
             */
            if((treeNode.left == null || treeNode.right == null)) {
                leaf = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
//        TreeNode<String> a = new TreeNode<>("a");
//        TreeNode<String> b = new TreeNode<>("b");
//        TreeNode<String> c = new TreeNode<>("c");
//        TreeNode<String> d = new TreeNode<>("d");
////        TreeNode<String> e = new TreeNode<>("e");
//        a.left = b;
//        a.right = c;
//        b.right = d;
////        c.left = e;
//
//        boolean result = isCBT(a);
//        System.out.println(result);
    }
}
