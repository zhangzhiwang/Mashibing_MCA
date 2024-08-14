package com.mashibing.dailyPractice.round2._71_to_100._0715;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.LinkedList;

/**
 * 判断一棵二叉树是否为完全二叉树
 */
public class IsCBTRecurse_0715 {
    static class IsCBTRecurseInfo_0715 {
        private int height;
        private boolean isCBT;
        private boolean isFull;

        public IsCBTRecurseInfo_0715(int height, boolean isCBT, boolean isFull) {
            this.height = height;
            this.isCBT = isCBT;
            this.isFull = isFull;
        }
    }

    public static boolean isCBTRecurse(TreeNode root) {
        if (root == null) {
            return true;
        }

        return recurse(root).isCBT;
    }

    private static IsCBTRecurseInfo_0715 recurse(TreeNode x) {
        if (x == null) {
            return new IsCBTRecurseInfo_0715(0, true, true);
        }

        IsCBTRecurseInfo_0715 left = recurse(x.left);
        IsCBTRecurseInfo_0715 right = recurse(x.right);

        boolean p1 = left.isCBT && right.isFull && left.height - right.height == 1;
        boolean p2 = left.isFull && right.isFull && (left.height - right.height == 0 || left.height - right.height == 1);
        boolean p3 = left.isFull && right.isCBT && left.height - right.height == 0;
        boolean isCBT = p1 || p2 || p3;
        boolean isFull = left.isFull && right.isFull && left.height - right.height == 0;

        int height = Math.max(left.height, right.height) + 1;
        return new IsCBTRecurseInfo_0715(height, isCBT, isFull);
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

    public static boolean isCBT1(TreeNode head) {
        if (head == null) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null))
                            ||
                            (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            boolean correctAns = isCBT1(head);
            boolean myAns = isCBTRecurse(head);
            if (correctAns != myAns) {
                DuiShuQiUtil.printBinaryTree(head);
                System.out.println("correct ans:" + correctAns);
                System.out.println("my ans:" + myAns);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }
}
