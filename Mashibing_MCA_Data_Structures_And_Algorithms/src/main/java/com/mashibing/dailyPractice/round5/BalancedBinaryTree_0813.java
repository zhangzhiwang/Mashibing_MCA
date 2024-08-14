package com.mashibing.dailyPractice.round5;

/**
 * 判断一棵树是不是平衡二叉树
 */
public class BalancedBinaryTree_0813 {
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

    class TreeNodeInfo {
        private boolean isBT;
        private int height;

        public TreeNodeInfo(boolean isBT, int height) {
            this.isBT = isBT;
            this.height = height;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return recurse(root).isBT;
    }

    private TreeNodeInfo recurse(TreeNode root) {
        if (root == null) {
            return new TreeNodeInfo(true, 0);
        }

        TreeNodeInfo left = recurse(root.left);
        TreeNodeInfo right = recurse(root.right);

        boolean isBT = left.isBT && right.isBT && Math.abs(left.height - right.height) <= 1;
        int height = Math.max(left.height, right.height) + 1;
        return new TreeNodeInfo(isBT, height);
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/balanced-binary-tree
    }
}
