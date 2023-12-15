package com.mashibing.tree;

import java.util.LinkedList;

/**
 * 判断一棵二叉树是否为完全二叉树（递归实现）
 * 思路：
 * 一棵以x节点为头的子树，它是完全二叉树的情况：
 * 情况一：x的左子树是满二叉树，右子树也是满二叉树，且左右子树的高度一样，或者左子树比右子树的高度大1
 * 情况二：x的左子树是完全二叉树，右子树是满二叉树，左子树的高度比右子树的高度大1
 * 情况三：x的左子树是满二叉树，右子树是完全二叉树，左子树的高度和右子树的高度一样
 * 根据递归的通用套路，x节点向它的左右子树要的信息有：是否是完全二叉树、是否是满二叉树、高度，使用递归的通用思路即可。
 *
 * 课程：体系班课时107
 */
public class IsCBTRecurse {
    static class IsCBTRecurseInfo {
        public boolean isCBT;// 子树是否是完全二叉树
        public boolean isFull;// 子树是否是满二叉树
        public int height;// 子树高度

        public IsCBTRecurseInfo(boolean isCBT, boolean isFull, int height) {
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

    public static IsCBTRecurseInfo recurse(TreeNode x) {
        if(x == null) {
            return new IsCBTRecurseInfo(true, true, 0);// 人为规定：空既是完全二叉树又是满二叉树
        }

        IsCBTRecurseInfo leftInfo = recurse(x.left);
        IsCBTRecurseInfo rightInfo = recurse(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isFull = leftInfo.isFull && rightInfo.isFull && (leftInfo.height == rightInfo.height);

        boolean isCBT = false;
        if(isFull) {// 情况一的前半部分
            isCBT = true;// 如果x是满二叉树那么它一定是完全二叉树
        } else if(leftInfo.isFull && rightInfo.isFull && (leftInfo.height - rightInfo.height == 1)) {// 情况一的后半部分
            isCBT = true;
        } else if(leftInfo.isCBT && rightInfo.isFull && (leftInfo.height - rightInfo.height == 1)) {// 情况二
            isCBT = true;
        } else if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {// 情况二
            isCBT = true;
        }

        return new IsCBTRecurseInfo(isCBT, isFull, height);
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
//            if (isCBT1(head) != isCBTRecurse(head)) {
//                System.out.println("Oops!");
//            }
            if (IsCBT.isCBT(head) != isCBTRecurse(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
