package com.mashibing.dailyPractice.round4;

/**
 * 计算一棵树的深度
 */
public class TreeDepth_0726 {
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

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return recurse(root);
    }

    public int recurse(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftDepth = recurse(root.left);
        int rightDepth = recurse(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/maximum-depth-of-binary-tree
    }
}
