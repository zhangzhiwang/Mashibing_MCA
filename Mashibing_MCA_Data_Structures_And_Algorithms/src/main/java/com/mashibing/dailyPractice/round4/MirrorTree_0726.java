package com.mashibing.dailyPractice.round4;

/**
 * 判断一棵树是不是镜面树（轴对称二叉树）
 */
public class MirrorTree_0726 {
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
        if (root == null) {
            return true;
        }

        return recurse(root.left, root.right);
    }

    private boolean recurse(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null ^ node2 == null) {
            return false;
        } else if (node1.val != node2.val) {
            return false;
        }
        
        return recurse(node1.left, node2.right) && recurse(node1.right, node2.left);
    }

    public static void main(String[] args) {
        // leetcode：https://leetcode.com/problems/symmetric-tree
    }
}
