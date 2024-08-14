package com.mashibing.dailyPractice.round3._No41_50;

/**
 * 给定一棵树和一个sum值，判断这棵树是否至少存在一个完整路径（从头节点到叶子节点），该路径和等于sum。
 */
public class PathSum_0306 {
    /**
     * 由于本题目要在leetcode上去验证，所以要使用leetcode给出的节点类
     */
    public class TreeNode {
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
        if (root == null) {// base case1
            return false;
        }
        if(root.left == null && root.right == null) {// base case2
            return sum + root.val == targetSum;
        }

        sum += root.val;
        boolean left = recurse(root.left, targetSum, sum);
        if(left) {
            return true;
        }
        boolean right = recurse(root.right, targetSum, sum);
        return right;
    }
}
