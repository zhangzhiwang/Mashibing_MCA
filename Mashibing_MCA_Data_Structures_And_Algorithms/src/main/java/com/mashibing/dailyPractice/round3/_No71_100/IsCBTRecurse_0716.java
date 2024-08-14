package com.mashibing.dailyPractice.round3._No71_100;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.LinkedList;

/**
 * 判断一棵二叉树是否为完全二叉树
 */
public class IsCBTRecurse_0716 {
    static class IsCBTRecurseInfo_0716 {
        private boolean isCBT;
        private boolean isFull;
        private int height;

        public IsCBTRecurseInfo_0716(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static boolean isCBTRecurse(TreeNode root) {
        if (root == null) {
            return true;
        }

        return recurse(root).isCBT;
    }

    private static IsCBTRecurseInfo_0716 recurse(TreeNode root) {
        if (root == null) {
            return new IsCBTRecurseInfo_0716(true, true, 0);
        }

        IsCBTRecurseInfo_0716 left = recurse(root.left);
        IsCBTRecurseInfo_0716 right = recurse(root.right);
        boolean p1 = left.isCBT && right.isFull && left.height - right.height == 1;
        boolean p2 = left.isFull && right.isFull && (left.height - right.height == 1 || left.height - right.height == 0);
        boolean p3 = left.isFull && right.isCBT && left.height - right.height == 0;

        boolean isCBT = p1 || p2 || p3;
        boolean isFull = left.isFull && right.isFull && left.height - right.height == 0;
        int height = Math.max(left.height, right.height) + 1;
        return new IsCBTRecurseInfo_0716(isCBT, isFull, height);
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
