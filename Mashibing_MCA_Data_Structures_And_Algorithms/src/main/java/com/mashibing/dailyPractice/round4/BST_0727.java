package com.mashibing.dailyPractice.round4;

import com.mashibing.tree.TreeNode;

/**
 * 判断一棵树是不是平衡搜索二叉树
 */
public class BST_0727 {
    static class BTInfo_0727 {
        private boolean isBT;
        private int height;

        public BTInfo_0727(boolean isBT, int height) {
            this.isBT = isBT;
            this.height = height;
        }
    }

    static class STInfo_0727 {
        private boolean isST;
        private int max;
        private int min;

        public STInfo_0727(boolean isST, int max, int min) {
            this.isST = isST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean bst(TreeNode<Integer> root) {
        if(root == null) {
            return true;
        }

        return recurseBT(root).isBT && recurseST(root).isST;
    }

    private static BTInfo_0727 recurseBT(TreeNode<Integer> root) {
        if(root == null) {
            return new BTInfo_0727(true, 0);
        }

        BTInfo_0727 left = recurseBT(root.left);
        BTInfo_0727 right = recurseBT(root.right);

        boolean isBT = left.isBT && right.isBT && Math.abs(left.height - right.height) <= 1;
        int height = Math.max(left.height, right.height) + 1;
        return new BTInfo_0727(isBT, height);
    }

    private static STInfo_0727 recurseST(TreeNode<Integer> root) {
        if(root == null) {
            return null;
        }

        STInfo_0727 left = recurseST(root.left);
        STInfo_0727 right = recurseST(root.right);
        boolean isLeftST = left == null ? true : left.isST && left.max < root.value;
        boolean isRightST = right == null ? true : right.isST && right.min > root.value;
        boolean isST = isLeftST && isRightST;
        if(!isST) {
            return new STInfo_0727(false, 0, 0);
        }

        int max = left == null ? root.value : Math.max(left.max, root.value);
        max = right == null ? max : Math.max(max, right.max);
        int min = left == null ? root.value : Math.min(left.min, root.value);
        min = right == null ? min : Math.min(min, right.min);;
        return new STInfo_0727(isST, max, min);
    }

    public static void main(String[] args) {
        TreeNode<Integer> node10 = new TreeNode<>(10);
        TreeNode<Integer> node5 = new TreeNode<>(5);
        TreeNode<Integer> node6 = new TreeNode<>(6);
        TreeNode<Integer> node3 = new TreeNode<>(3);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node15 = new TreeNode<>(15);
        TreeNode<Integer> node13 = new TreeNode<>(13);
        TreeNode<Integer> node20 = new TreeNode<>(20);
        TreeNode<Integer> node1 = new TreeNode<>(1);
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node14 = new TreeNode<>(14);
        node10.left = node5;
        node10.right = node15;
        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
//        node3.right = node4;
//        node2.left = node1;
        node15.left = node13;
        node15.right = node20;
//        node15.right = node14;

        System.out.println(bst(node10));
    }
}
