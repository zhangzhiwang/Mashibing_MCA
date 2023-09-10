package com.mashibing.tree;

import sun.reflect.generics.tree.Tree;

/**
 * 判断达标路径和
 * 题目：给定一棵树和一个sum值，判断这棵树是否至少存在一个完整路径（从头到节点到叶子节点），该路径和等于sum。
 * 课程：新手班课时51
 * 思路：见com.mashibing.preInEclipse.junior.tree.PathSum注释
 */
public class PathSum {
    public static void main(String[] args) {
        TreeNode<Integer> n1 = new TreeNode<>(1);
        n1.left = new TreeNode<>(2);
        n1.right = new TreeNode<>(3);

        n1.left.left = new TreeNode<>(4);
        n1.left.right = new TreeNode<>(5);

        n1.right.left = new TreeNode<>(6);
        n1.right.right = new TreeNode<>(7);

        System.out.println(f(n1, 7));
    }

    public static boolean f(TreeNode<Integer> head, int targetSum) {
        if(head == null) {
            return false;
        }

        return isPathSum(head, 0, targetSum);
    }

    /**
     *
     * @param head 输的头结点
     * @param preSum 当前节点之前的所有节点的路径和（不包括当前节点的值）
     * @param targetSum 目标路径和
     * @return
     */
    public static boolean isPathSum(TreeNode<Integer> head, int preSum, int targetSum) {
        // base case是判断叶节点
        if(head.left == null && head.right == null) {
            if(preSum + head.data == targetSum) {
                System.out.println("叶子节点是：" + head.data);
                return true;
            }
            return false;
        }

        preSum += head.data;// 把当前节点的值给加上，方便往下传递
        if(head.left != null) {// 当前节点的累加和只往下级不为空的节点传递，因为当左子树为空时说明叶子节点肯定不在左侧。这里要控制好左子树不为空，否则会在base case那里出现head为null的情况
            boolean isTarget = isPathSum(head.left, preSum, targetSum);
            if(isTarget) {
                return true;
            }
        }
        if(head.right != null) {// 当前节点的累加和只往下级不为空的节点传递，因为当右子树为空时说明叶子节点肯定不在右侧。这里要控制好左子树不为空，否则会在base case那里出现head为null的情况
            boolean isTarget = isPathSum(head.right, preSum, targetSum);
            if(isTarget) {
                return true;
            }
        }

        return false;
    }
}
