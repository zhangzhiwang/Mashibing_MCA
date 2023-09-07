package com.mashibing.tree;

/**
 * 求一棵树的最大深度
 * 课程：新手班课时46
 * 思路：见com.mashibing.preInEclipse.junior.tree.TreeDepth注释
 */
public class TreeDepth {
    public static void main(String[] args) {
        TreeNode<Integer> head = new TreeNode<>(1);
        head.left = new TreeNode<>(2);
        head.right = new TreeNode<>(3);
        head.right.right = new TreeNode<>(4);
        System.out.println(getTreeDepth(head));
    }

    public static int getTreeDepth(TreeNode<Integer> head) {
        if(head == null) {
            return 0;
        }

        int d1 = getTreeDepth(head.left);
        int d2 = getTreeDepth(head.right);
        return Math.max(d1, d2) + 1;
    }
}
