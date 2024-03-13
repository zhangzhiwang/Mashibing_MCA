package com.mashibing.dailyPractice.round2._0223;


/**
 * 判断一棵树是不是平衡二叉树
 */
public class BalancedBinaryTree_0223 {
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

    public class BalanceInfo {
        private boolean isBalanced;
        private int height;

        public BalanceInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        return recurse(root).isBalanced;
    }

    private BalanceInfo recurse(TreeNode root) {
        if(root == null) {
            return new BalanceInfo(true, 0);
        }

        BalanceInfo leftInfo = recurse(root.left);
        BalanceInfo rightInfo = recurse(root.right);
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new BalanceInfo(isBalanced, height);
    }
}
