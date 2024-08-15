package com.mashibing.dailyPractice.round5;

/**
 * 计算一棵树的深度
 */
public class TreeDepth_0815 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return recurse(root);
    }

    private int recurse(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = recurse(root.left);
        int right = recurse(root.right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/maximum-depth-of-binary-tree
    }
}
