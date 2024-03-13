package com.mashibing.dailyPractice.round3._No31_40;

import com.mashibing.dailyPractice.round1._2_1.BalancedBinaryTree_0201;

/**
 * 判断一棵树是不是平衡二叉树
 */
public class BalancedBinaryTree_0306 {
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

    class BTInfo {
        private boolean isBT;
        private int height;

        public BTInfo(boolean isBT, int height) {
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

    private BTInfo recurse(TreeNode root) {
        if(root == null) {
            return new BTInfo(true, 0);
        }

        BTInfo left = recurse(root.left);
        BTInfo right = recurse(root.right);

        boolean isBT = left.isBT && right.isBT && Math.abs(left.height - right.height) <= 1;
        int height = Math.max(left.height, right.height) + 1;
        return new BTInfo(isBT, height);
    }
}
