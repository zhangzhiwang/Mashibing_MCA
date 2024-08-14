package com.mashibing.dailyPractice.round4;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 给定一棵树和一个sum值，判断这棵树是否至少存在一个完整路径（从头节点到叶子节点），该路径和等于sum。
 */
public class PathSum_0727 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }

        return recurse(root, 0, targetSum);
    }

    private static boolean recurse(TreeNode root, int sum, int targetSum) {
        if(root == null) {
            return false;
        }

        if(root.left == null && root.right == null) {
            return sum + root.val == targetSum;
        }

        sum += root.val;
        boolean left = recurse(root.left, sum, targetSum);
        return left ? left : recurse(root.right, sum, targetSum);
    }

    private static void test(int i) {
        i = i + 1;
        System.out.println("in test i = " + i);
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/path-sum

//        int i = 10;
//        test(i);
//        System.out.println("i = " + i);
        
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        System.out.println(new PathSum_0727().hasPathSum(node1, 10));
    }
}
