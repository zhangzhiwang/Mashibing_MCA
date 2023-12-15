package com.mashibing.tree;

import java.util.ArrayList;

/**
 * 判断一棵树是不是搜索树
 * 课程：新手班课时49、50
 * 思路：见com.mashibing.preInEclipse.junior.tree.BinarySearchTree注释
 */
public class SearchTree {
    public static void main(String[] args) {
//        TreeNode<Integer> n1 = new TreeNode<>(5);
//        n1.left = new TreeNode<>(3);
//        n1.right = new TreeNode<>(7);
//
//        n1.left.left = new TreeNode<>(1);
//        n1.left.right = new TreeNode<>(4);
//        n1.left.left.right = new TreeNode<>(2);
//
//        n1.right.left = new TreeNode<>(6);
//        n1.right.right = new TreeNode<>(9);
//        n1.right.right.left = new TreeNode<>(8);
//
//        SearchTreeInfo searchTreeInfo = isSearchTree(n1);
//        System.out.println(searchTreeInfo.isSearchTree);

        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isSearchTree(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    static class SearchTreeInfo {
        public boolean isSearchTree;
        public int max;
        public int min;

        public SearchTreeInfo(boolean isSearchTree, int max, int min) {
            this.isSearchTree = isSearchTree;
            this.max = max;
            this.min = min;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "isSearchTree=" + isSearchTree +
                    ", max=" + max +
                    ", min=" + min +
                    '}';
        }
    }

    public static boolean isSearchTree(TreeNode<Integer> head) {
        if(head == null) {
            return true;
        }

        return f(head).isSearchTree;
    }

    public static SearchTreeInfo f(TreeNode<Integer> head) {
        if(head == null) {
            return null;
        }

        SearchTreeInfo leftInfo = f(head.left);// true 1 1
        SearchTreeInfo rightInfo = f(head.right);// true 3 3

        boolean isLeftValid = leftInfo == null ? true : leftInfo.isSearchTree && leftInfo.max < head.data;
        if(!isLeftValid) {
            return new SearchTreeInfo(false, 0, 0);
        }
        boolean isRightValid = rightInfo == null ? true : rightInfo.isSearchTree && rightInfo.min > head.data;
        if(!isRightValid) {
            return new SearchTreeInfo(false, 0, 0);
        }

        return new SearchTreeInfo(true,
                rightInfo == null ? head.data : rightInfo.max,
                leftInfo == null ? head.data : leftInfo.min);
    }

    // 以下是对数器
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
            if (arr.get(i).data <= arr.get(i - 1).data) {
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
}
