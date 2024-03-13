package com.mashibing.dailyPractice.round1._1_29;

/**
 * 判断两棵树是否完全一样
 */
public class TreeEquals_0129 {
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

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null ^ q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        if (!isSameTree(p.left, q.left)) {
            return false;
        }

        if (!isSameTree(p.right, q.right)) {
            return false;
        }

        // 最后两个判断可以简写成下面这句代码，就是阅读起来不方便理解，debug的时候也不容易调试
//        return treeEquals(head1.left, head2.left) && treeEquals(head1.right, head2.right);
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(false ^ false);
//        System.out.println(true ^ true);
//        System.out.println(false ^ true);

        // leetcode：https://leetcode.com/problems/same-tree
    }
}
