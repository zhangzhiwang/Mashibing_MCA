package com.mashibing.dailyPractice.round5;

/**
 * 给定一棵树和一个sum值，判断这棵树是否至少存在一个完整路径（从头节点到叶子节点），该路径和等于sum。
 */
public class PathSum_0817 {
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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }

        return recurse(root, 0, targetSum);
    }

    private boolean recurse(TreeNode root, int sum, int targetSum) {
        if(root == null) {
            return false;
        }
        if(root.left == null && root.right == null) {
            return sum + root.val == targetSum;
        }

        sum += root.val;
        boolean left = recurse(root.left, sum, targetSum);
        boolean right = recurse(root.right, sum, targetSum);
        return left || right;
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/path-sum
    }
}
