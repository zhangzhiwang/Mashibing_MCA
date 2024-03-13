package com.mashibing.dailyPractice.round2._0222;

/**
 * 判断一棵树是不是镜面树（轴对称二叉树）
 */
public class MirrorTree_0222 {
    /**
     * leetcode
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

        return recurse(root, root);
    }

    private boolean recurse(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) {
            return true;
        } else if(root1 == null ^ root2 == null) {
            return false;
        } else if (root1.val != root2.val) {
            return false;
        }

        boolean left = recurse(root1.left, root2.right);
        boolean right = recurse(root1.right, root2.left);
        return left && right;
    }
}
