package com.mashibing.dailyPractice.round2._71_to_100._0715;

import com.mashibing.dailyPractice.round1._71_to_80.IsFull_0317;
import com.mashibing.tree.TreeNode;

/**
 * 判断一棵树是不是满二叉树
 */
public class IsFull2_0715 {
    static class IsFull2Info_0715 {
        private int height;
        private boolean isFull;

        public IsFull2Info_0715(int height, boolean isFull) {
            this.height = height;
            this.isFull = isFull;
        }
    }

    public static boolean isFull2(TreeNode root) {
        if(root == null) {
            return true;
        }

        return recurse(root).isFull;
    }

    private static IsFull2Info_0715 recurse(TreeNode x) {
        if(x == null) {
            return new IsFull2Info_0715(0, true);
        }

        IsFull2Info_0715 left = recurse(x.left);
        IsFull2Info_0715 right = recurse(x.right);
        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        int height = Math.max(left.height, right.height) + 1;
        return new IsFull2Info_0715(height, isFull);
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
        IsFull_0317.Info1 all = process1(head);
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

    public static IsFull_0317.Info1 process1(TreeNode head) {
        if (head == null) {
            return new IsFull_0317.Info1(0, 0);
        }
        IsFull_0317.Info1 leftInfo = process1(head.left);
        IsFull_0317.Info1 rightInfo = process1(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new IsFull_0317.Info1(height, nodes);
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
