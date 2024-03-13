package com.mashibing.dailyPractice.round3._No31_40;

import com.mashibing.tree.TreeNode;

import java.util.ArrayList;

/**
 * 判断一棵树是不是搜索二叉树
 */
public class SearchTree_0306 {
    static class STInfo {
        private boolean isST;
        private int min;
        private int max;

        public STInfo(boolean isST, int min, int max) {
            this.isST = isST;
            this.min = min;
            this.max = max;
        }
    }

    public static boolean searchTree(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }

        return recurse(root).isST;
    }

    private static STInfo recurse(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }

        STInfo left = recurse(root.left);
        STInfo right = recurse(root.right);
        boolean leftIsST = left == null ? true : left.isST && left.max < root.value;
        boolean rightIsST = right == null ? true : right.isST && right.min > root.value;
        boolean isST = leftIsST && rightIsST;
        if (!isST) {
            return new STInfo(false, 0, 0);
        }

        int min = left == null ? root.value : left.min;
        int max = right == null ? root.value : right.max;
        return new STInfo(isST, min, max);
    }

    // 对数器
    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static boolean isBST1(TreeNode<Integer> head) {
        if (head == null) {
            return true;
        }
        ArrayList<TreeNode<Integer>> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(TreeNode<Integer> head, ArrayList<TreeNode<Integer>> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != searchTree(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
