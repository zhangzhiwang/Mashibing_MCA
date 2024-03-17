package com.mashibing.dailyPractice.round1._71_to_80;

import com.mashibing.tree.TreeNode;

import java.util.LinkedList;

/**
 * 判断一棵二叉树是否为完全二叉树
 */
public class IsCBTRecurse_0316 {
    static class IsCBTRecurseInfo_0316 {
        private boolean isCBT;
        private boolean isFull;
        private int height;

        public IsCBTRecurseInfo_0316(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static boolean isCBTRecurse(TreeNode head) {
        if(head == null) {
            return true;
        }

        return recurse(head).isCBT;
    }

    private static IsCBTRecurseInfo_0316 recurse(TreeNode head) {
        if(head == null) {
            return new IsCBTRecurseInfo_0316(true, true, 0);
        }

        IsCBTRecurseInfo_0316 leftInfo = recurse(head.left);
        IsCBTRecurseInfo_0316 rightInfo = recurse(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = isFull ||
                (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) ||
                (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) ||
                (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height);

        return new IsCBTRecurseInfo_0316(isCBT, isFull, height);
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
            if (isCBT1(head) != isCBTRecurse(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
