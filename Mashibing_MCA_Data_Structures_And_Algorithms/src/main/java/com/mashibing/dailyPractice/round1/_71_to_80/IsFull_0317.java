package com.mashibing.dailyPractice.round1._71_to_80;

import com.mashibing.tree.MaxDistance;
import com.mashibing.tree.TreeNode;

/**
 * 判断一棵树是不是满二叉树
 */
public class IsFull_0317 {
    static class IsFullInfo_0317 {
        private boolean isFull;
        private int height;

        public IsFullInfo_0317(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static boolean isFull(TreeNode head) {
        if(head == null) {
            return true;
        }

        return recurse(head).isFull;
    }

    private static IsFullInfo_0317 recurse(TreeNode x) {
        if(x == null) {
            return new IsFullInfo_0317(true, 0);
        }

        IsFullInfo_0317 leftInfo = recurse(x.left);
        IsFullInfo_0317 rightInfo = recurse(x.right);

        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new IsFullInfo_0317(isFull, height);
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

    // 第一种方法
    // 收集整棵树的高度h，和节点数n
    // 只有满二叉树满足 : 2 ^ h - 1 == n
    public static boolean isFull1(TreeNode head) {
        if (head == null) {
            return true;
        }
        Info1 all = process1(head);
        return (1 << all.height) - 1 == all.nodes;
    }

    public static class Info1 {
        public int height;
        public int nodes;

        public Info1(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static Info1 process1(TreeNode head) {
        if (head == null) {
            return new Info1(0, 0);
        }
        Info1 leftInfo = process1(head.left);
        Info1 rightInfo = process1(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info1(height, nodes);
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
