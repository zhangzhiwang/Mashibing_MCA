package com.mashibing.dailyPractice.round2._0223;

import com.mashibing.tree.TreeNode;

import java.util.ArrayList;

/**
 * 判断一棵树是不是搜索二叉树
 */
public class SearchTree_0223 {
    static class SearchTreeInfo {
        private boolean isST;
        private int min;
        private int max;

        public SearchTreeInfo(boolean isST, int min, int max) {
            this.isST = isST;
            this.min = min;
            this.max = max;
        }
    }

    public static boolean isST(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }

        return recurse(root).isST;
    }

    private static SearchTreeInfo recurse(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }

        SearchTreeInfo leftInfo = recurse(root.left);
        SearchTreeInfo rightInfo = recurse(root.right);
        boolean leftIsST = leftInfo == null ? true : leftInfo.isST && leftInfo.max < root.value;
        boolean rightIsST = rightInfo == null ? true : rightInfo.isST && rightInfo.min > root.value;
        boolean isST = leftIsST && rightIsST;
        if (!isST) {
            return new SearchTreeInfo(false, 0, 0);
        }

        int min = leftInfo == null ? root.value : leftInfo.min;
        int max = rightInfo == null ? root.value : rightInfo.max;
        return new SearchTreeInfo(isST, min, max);
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
