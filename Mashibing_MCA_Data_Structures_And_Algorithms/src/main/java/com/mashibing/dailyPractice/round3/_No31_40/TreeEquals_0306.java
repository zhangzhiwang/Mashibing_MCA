package com.mashibing.dailyPractice.round3._No31_40;

import com.mashibing.dailyPractice.round1._1_29.TreeEquals_0129;

/**
 * 判断两棵树是否完全一样
 */
public class TreeEquals_0306 {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == q) {
            return true;
        }

        if(p == null ^ q == null) {
            return false;
        }

        if(p.val != q.val) {
            return false;
        }

        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }
}
