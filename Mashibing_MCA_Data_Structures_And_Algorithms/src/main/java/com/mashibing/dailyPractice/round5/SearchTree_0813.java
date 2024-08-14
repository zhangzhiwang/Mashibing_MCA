package com.mashibing.dailyPractice.round5;

import com.mashibing.tree.TreeNode;

import java.util.ArrayList;

/**
 * 判断一棵树是不是搜索二叉树
 */
public class SearchTree_0813 {
    static class SearchTreeInfo_0813 {
        private boolean isST;
        private int min;
        private int max;

        public SearchTreeInfo_0813(boolean isST, int min, int max) {
            this.isST = isST;
            this.min = min;
            this.max = max;
        }
    }

    public static boolean isST(TreeNode<Integer> root) {
        if(root == null) {
            return true;
        }

        return recurse(root).isST;
    }

    private static SearchTreeInfo_0813 recurse(TreeNode<Integer> root) {
        if(root == null) {
            return null;
        }

        SearchTreeInfo_0813 left = recurse(root.left);
        SearchTreeInfo_0813 right = recurse(root.right);
        boolean isLeftST = left == null ? true : left.isST && left.max < root.value;
        boolean isRightST = right == null ? true : right.isST && right.min > root.value;
        boolean isST = isLeftST && isRightST;

        int min = left == null ? root.value : Math.min(left.min, root.value);
        min = right == null ? min : Math.min(min, right.min);
        int max = left == null ? root.value : Math.max(left.max, root.value);
        max = right == null ? max : Math.max(max, right.max);;
        return new SearchTreeInfo_0813(isST, min, max);
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
            if (isBST1(head) != isST(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
