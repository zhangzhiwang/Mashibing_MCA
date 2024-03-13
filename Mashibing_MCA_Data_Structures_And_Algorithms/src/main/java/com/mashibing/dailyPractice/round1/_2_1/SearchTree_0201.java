package com.mashibing.dailyPractice.round1._2_1;

import com.mashibing.tree.TreeNode;

import java.util.ArrayList;

/**
 * 判断一棵树是不是搜索二叉树
 */
public class SearchTree_0201 {
    public static boolean searchTree(TreeNode<Integer> head) {
        if(head == null){
            return true;
        }

        return recurse(head).isSearchTree;
    }

    public static Info recurse(TreeNode<Integer> head) {
        if(head == null) {
            return null;
        }

        Info leftInfo = recurse(head.left);
        Info rightInfo = recurse(head.right);

        boolean isLeftValid = leftInfo == null ? true : leftInfo.isSearchTree && leftInfo.max < head.value;
        boolean isRightValid = rightInfo == null ? true : rightInfo.isSearchTree && rightInfo.min > head.value;
        if(!isLeftValid || !isRightValid) {
            return new Info(false, 0, 0);
        }

        return new Info(true,
                leftInfo == null ? head.value : leftInfo.min,
                rightInfo == null ? head.value : rightInfo.max);
    }

    static class Info {
        public boolean isSearchTree;
        public int min;
        public int max;

        public Info(boolean isSearchTree, int min, int max) {
            this.isSearchTree = isSearchTree;
            this.min = min;
            this.max = max;
        }
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
