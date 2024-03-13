package com.mashibing.dailyPractice.round2._0223;

import com.mashibing.tree.TreeNode;

/**
 * 判断一棵树是不是平衡搜索二叉树
 */
public class BST_0223 {
    static class BT {
        private boolean isBT;
        private int height;

        public BT(boolean isBT, int height) {
            this.isBT = isBT;
            this.height = height;
        }
    }

    static class ST {
        private boolean isST;
        private int min;
        private int max;

        public ST(boolean isST, int min, int max) {
            this.isST = isST;
            this.min = min;
            this.max = max;
        }
    }

    public static boolean isBST(TreeNode<Integer> root) {
        if(root == null) {
            return true;
        }

//        return isBTRecurse(root).isBT && isSTRecurse(root).isST;
        // 为了测试方便打一些日志
        boolean isBT = isBTRecurse(root).isBT;
        boolean isST = isSTRecurse(root).isST;
        if(!isBT) {
            System.out.println("不是平衡树");
        }

        if(!isST) {
            System.out.println("不是搜索树");
        }

        return isBT && isST;
    }

    private static BT isBTRecurse(TreeNode<Integer> root) {
        if(root == null) {
            return new BT(true, 0);
        }

        BT left = isBTRecurse(root.left);
        BT right = isBTRecurse(root.right);

        boolean isBT = left.isBT && right.isBT && Math.abs(left.height - right.height) <= 1;
        int height = Math.max(left.height, right.height) + 1;
        return new BT(isBT, height);
    }

    private static ST isSTRecurse(TreeNode<Integer> root) {
        if(root == null) {
            return null;
        }

        ST left = isSTRecurse(root.left);
        ST right = isSTRecurse(root.right);
        boolean isLeftST = left == null ? true : left.isST && left.max < root.value;
        boolean isRightST = right == null ? true : right.isST && right.min > root.value;
        boolean isST = isLeftST && isRightST;
        if(!isST) {
            return new ST(false, 0, 0);
        }

        int min = left == null ? root.value : left.min;
        int max = right == null ? root.value : right.max;
        return new ST(isST, min, max);
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
        node10.left = node5;
        node10.right = node15;
        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node15.left = node13;
        node15.right = node20;

        System.out.println(isBST(node10));
    }
}
