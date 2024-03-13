package com.mashibing.dailyPractice.round1._2_1;

/**
 * 判断一棵树是不是平衡二叉树
 */
public class BalancedBinaryTree_0201 {
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

    public class Info {
        public int height;
        public boolean isBalanced;

        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        return recurse(root).isBalanced;
    }

    private Info recurse(TreeNode head) {
        if(head == null) {
            return new Info(0, true);
        }

        Info leftInfo = recurse(head.left);
        Info rightInfo = recurse(head.right);
        return new Info(Math.max(leftInfo.height, rightInfo.height) + 1,
                leftInfo.isBalanced && rightInfo.isBalanced && (Math.abs(leftInfo.height - rightInfo.height) <= 1));
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/balanced-binary-tree
    }
}
