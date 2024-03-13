package com.mashibing.dailyPractice.round2._0222;

/**
 * 判断两棵树是否完全一样
 */
public class TreeEquals_0222 {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        } else if(p == null ^ q == null) {
            return false;
        }

        return recurse(p, q);
    }

    private boolean recurse(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) {
            return true;
        } else if(root1 == null ^ root2 == null) {
            return false;
        } else if(root1.val != root2.val) {
            return false;
        }

        boolean left = recurse(root1.left, root2.left);
        boolean right = recurse(root1.right, root2.right);
        return left && right;
    }
}
