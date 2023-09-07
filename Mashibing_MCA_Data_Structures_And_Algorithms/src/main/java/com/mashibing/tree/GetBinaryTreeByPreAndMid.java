package com.mashibing.tree;

/**
 * 根据先序遍历和中序遍历的结果还原整个二叉树，两种遍历的结果会以数组的形式给出来，数组的元素有可能就是树的节点，也有可能是每个节点所代表的的值，
 * 如果是第二种情况的话，构造二叉树的时候要把每一个值包装成节点。
 * 在com.mashibing.preInEclipse.junior.tree.GetBinaryTreeByPreAndMid中练习的是第一种情况，这里练习第二种情况。
 * 课程：新手班课时47
 * 思路：见com.mashibing.preInEclipse.junior.tree.GetBinaryTreeByPreAndMid注释
 */
public class GetBinaryTreeByPreAndMid {
    public static void main(String[] args) {

    }

    public static TreeNode<Integer> getBinaryTreeByPreAndMid(int[] pre, int[] mid) {
        if(!(pre != null && mid != null)) {
            return null;
        }

        if(pre.length != mid.length) {
            return null;
        }

        // 1、先序遍历的第一个节点一定是整棵树的头部，所以直接返回即可
        TreeNode<Integer> head = new TreeNode<>(pre[0]);

        // 2、找到头结点在中序遍历结果中的位置
        // 方式一：




        return head;
    }
}
