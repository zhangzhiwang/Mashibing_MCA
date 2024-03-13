package com.mashibing.dailyPractice.round1._2_1;

/**
 * 给定一棵树和一个sum值，判断这棵树是否至少存在一个完整路径（从头节点到叶子节点），该路径和等于sum。
 */
public class PathSum_0201 {
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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }

        return recurse(root, 0, targetSum);
    }

    private boolean recurse(TreeNode head, int sum, int targetSum) {
        if(head.left == null && head.right == null) {
            return sum + head.val == targetSum;
        }

        if(head.left != null) {
            boolean b = recurse(head.left, sum + head.val, targetSum);
            if(b) {
                return b;
            }
        }

        if(head.right != null) {
            boolean b = recurse(head.right, sum + head.val, targetSum);
            if(b) {
                return b;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/path-sum
    }
}
