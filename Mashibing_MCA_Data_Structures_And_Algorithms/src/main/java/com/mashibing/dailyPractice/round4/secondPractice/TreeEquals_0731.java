package com.mashibing.dailyPractice.round4.secondPractice;

/**
 * 判断两棵树是否完全一样
 */
public class TreeEquals_0731 {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        } else if (p == null ^ q == null) {
            return false;
        }

        return recurse(p, q);
    }

    private static boolean recurse(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        } else if (p == null ^ q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }

        return recurse(p.left, q.left) && recurse(p.right, q.right);
    }

    public static void main(String[] args) {
        // leetcode：https://leetcode.com/problems/same-tree
    }
}
