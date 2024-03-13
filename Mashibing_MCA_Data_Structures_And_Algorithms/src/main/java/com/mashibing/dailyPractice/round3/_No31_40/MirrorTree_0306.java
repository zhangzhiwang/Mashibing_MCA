package com.mashibing.dailyPractice.round3._No31_40;

/**
 * 判断一棵树是不是镜面树（轴对称二叉树）
 */
public class MirrorTree_0306 {
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

    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }

        return recurse(root.left, root.right);
    }

    private boolean recurse(TreeNode root1, TreeNode root2) {
        if(root1 == root2) {
            return true;
        }

        if(root1 == null ^ root2 == null) {
            return false;
        }

        if(root1.val != root2.val) {
            return false;
        }
        boolean b1 = recurse(root1.left, root2.right);
        boolean b2 = recurse(root1.right, root2.left);
        return b1 && b2;
    }
}
