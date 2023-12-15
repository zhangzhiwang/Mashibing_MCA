package com.mashibing.tree;

/**
 * 判断一棵树是不是满二叉树（定义版）
 * 解释：满二叉树：如果一棵树的高度是h，那么这棵树一共有2^h-1个节点
 * 思路：
 * 使用递归的通用思路：
 * 1、随便找一个节点x
 * 2、x向它的左树收集信息，信息包括：左树的高度h和左树的节点数nodeCount；同理x向它的右树也收集h和nodeCount；x也需要向上返回以自己为头结点的树的h和nodeCount
 *
 * 课程：体系班课时103
 */
public class IsFull {
    static class FullInfo {
        public int height;// 子树高度
        public int nodeCount;// 子树的节点数

        public FullInfo(int height, int nodeCount) {
            this.height = height;
            this.nodeCount = nodeCount;
        }
    }

    public static boolean isFull(TreeNode head) {
        if(head == null) {
            return true;// 人为规定空树就是满二叉树，也可以规定不是
        }

        FullInfo fullInfo = recurse(head);
        return fullInfo.nodeCount == (int)Math.pow(2, fullInfo.height) - 1;
    }

    public static FullInfo recurse(TreeNode x) {
        if(x == null) {
            return new FullInfo(0, 0);
        }

        FullInfo leftInfo = recurse(x.left);
        FullInfo rightInfo = recurse(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodeCount = leftInfo.nodeCount + rightInfo.nodeCount + 1;
        return new FullInfo(height, nodeCount);
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
//            if (isFull1(head) != isFull(head)) {
//                System.out.println("出错了!");
//            }
            if (isFull(head) != IsFull2.isFull2(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
