package com.mashibing.dailyPractice.round4.secondPractice;

/**
 * 给定一棵树和一个sum值，判断这棵树是否至少存在一个完整路径（从头节点到叶子节点），该路径和等于sum。
 */
public class PathSum_0731 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
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

        return recurse1(root, targetSum, 0);
    }

    private static boolean recurse1(TreeNode root, int targetSum, int sum) {
        if(root == null) {
            return false;
        }

        if(root.left == null && root.right == null) {
            return sum + root.val == targetSum;
        }

        sum += root.val;
        boolean left = recurse1(root.left, targetSum, sum);
        return left ? true : recurse1(root.right, targetSum, sum);
    }

    private static boolean recurse2(TreeNode root, int targetSum, int sum) {
        if(root.left == null && root.right == null) {
            return sum + root.val == targetSum;
        }

        sum += root.val;
        boolean left = false;
        if (root.left != null) {
            left = recurse1(root.left, targetSum, sum);
        }
        if(left) {
            return true;
        }

        boolean right = false;
        if (root.right != null) {
            right = recurse1(root.right, targetSum, sum);
        }

        return right;
    }
}
