package com.mashibing.dailyPractice.round4;

/**
 * 判断一棵树是不是平衡二叉树
 */
public class BalancedBinaryTree_0726 {
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

    class BalancedBinaryTreeInfo_0726 {
        private boolean isBT;
        private int height;

        public BalancedBinaryTreeInfo_0726(boolean isBT, int height) {
            this.isBT = isBT;
            this.height = height;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        return recurse(root).isBT;
    }

    public BalancedBinaryTreeInfo_0726 recurse(TreeNode root) {
        if(root == null) {
            return new BalancedBinaryTreeInfo_0726(true, 0);
        }

        BalancedBinaryTreeInfo_0726 left = recurse(root.left);
        BalancedBinaryTreeInfo_0726 right = recurse(root.right);

        boolean isBT = left.isBT && right.isBT && Math.abs(left.height - right.height) <= 1;
        int height = Math.max(left.height, right.height) + 1;
        return new BalancedBinaryTreeInfo_0726(isBT, height);
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/balanced-binary-tree
    }
}
