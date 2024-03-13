package com.mashibing.dailyPractice.round2._0223;

/**
 * 给定一棵树和一个sum值，判断这棵树是否至少存在一个完整路径（从头节点到叶子节点），该路径和等于sum。
 */
public class PathSum_0223 {
    /**
     * leetcode
     */
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
        if (root == null) {
            return false;
        }

        return recurse(root, targetSum, 0);
    }

    private boolean recurse(TreeNode root, int targetSum, int sum) {
        if (root.left == null && root.right == null) {
            return sum + root.val == targetSum;
        }

        sum += root.val;
        boolean left = false;
        if (root.left != null) {
            left = recurse(root.left, targetSum, sum);
        }
        if (left) {
            return true;
        }

        boolean right = false;
        if (root.right != null) {
            right = recurse(root.right, targetSum, sum);
        }
        return right;
    }
}
