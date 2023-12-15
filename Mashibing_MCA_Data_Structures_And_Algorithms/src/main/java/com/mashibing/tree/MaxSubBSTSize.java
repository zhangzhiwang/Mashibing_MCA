package com.mashibing.tree;

import java.util.ArrayList;

/**
 * 题目：给定一棵二叉树head，整棵二叉树不一定是搜索二叉树，但是它的某些子树是，返回它所有是搜索二叉树的子树中节点做多的那棵树的节点数。
 * 思路：
 * 1、首先要结合前面的题知道怎么判断一棵树是不是搜索二叉树
 * 2、分两种情况：
 * （1）情况一：以x为头的树不是搜索二叉树，但是x的左子树或者右子树中有搜索二叉树
 * （2）情况二；以x为头的树是搜索二叉树
 * 3、用递归通用思路：x分别向左右子树要信息：子树中满足搜索二叉树的最大节点数、子树是不是搜索二叉树、子树的最大值，子树的最小值
 *
 * 课程：体系班课时104-105
 */
public class MaxSubBSTSize {
    static class MaxSubBSTSizeInfo {
        public int bstMaxNodeCount;// 子树中是搜索二叉树的最大节点数
        public boolean isBst;// 子树是否为bst
        public int min;// 子树最小值
        public int max;// 子树最大值

        public MaxSubBSTSizeInfo(int bstMaxNodeCount, boolean isBst, int min, int max) {
            this.bstMaxNodeCount = bstMaxNodeCount;
            this.isBst = isBst;
            this.min = min;
            this.max = max;
        }
    }

    public static int maxSubBSTSize(TreeNode<Integer> head) {
        if(head == null) {
            return 0;
        }

        return recurse(head).bstMaxNodeCount;
    }

    public static MaxSubBSTSizeInfo recurse(TreeNode<Integer> x) {
        /*
         base case中，当head为空时，不好设置MaxSubBSTSizeInfo信息，因为MaxSubBSTSizeInfo中的最大和最小值不好设置
         在base case返回值不好设置的时候就返回空，让上游去判断空
         */
        if(x == null) {
            return null;
        }

        MaxSubBSTSizeInfo leftInfo = recurse(x.left);
        MaxSubBSTSizeInfo rightInfo = recurse(x.right);

        boolean leftIsBst = leftInfo == null ? true : leftInfo.isBst;
        boolean rightIsBst = rightInfo == null ? true : rightInfo.isBst;
        boolean leftMaxLessThanX = leftInfo == null ? true : (leftInfo.max < x.data);
        boolean rightMinMoreThanX = rightInfo == null ? true : (rightInfo.min > x.data);

        boolean xIsBst = leftIsBst && rightIsBst && leftMaxLessThanX && rightMinMoreThanX;

        int bstMaxNodeCount = 0;
        if(xIsBst) {// 如果以x为头的子树是搜索二叉树，那么x的bstMaxNodeCount就是x子树所有节点的数量
            // 如果左子树是搜索二叉树，那么左子数的bstMaxNodeCount就是左子树所有节点的数量，右子树同理
            bstMaxNodeCount += leftInfo == null ? 0 : leftInfo.bstMaxNodeCount;
            bstMaxNodeCount += rightInfo == null ? 0 : rightInfo.bstMaxNodeCount;
            bstMaxNodeCount++;
        } else {// 如果以x为头的子树不是搜索二叉树，那么就找左子树和右子树最大的bstMaxNodeCount
            bstMaxNodeCount = leftInfo == null ? 0 : Math.max(leftInfo.bstMaxNodeCount, 0);
            bstMaxNodeCount = rightInfo == null ? bstMaxNodeCount : Math.max(rightInfo.bstMaxNodeCount, bstMaxNodeCount);
        }

        // 以x为头的子树的全局最小值
        int min = leftInfo == null ? x.data : Math.min(leftInfo.min, x.data);
        min = rightInfo == null ? min : Math.min(rightInfo.min, min);

        // 以x为头的子树的全局最大值
        int max = leftInfo == null ? x.data : Math.max(leftInfo.max, x.data);
        max = rightInfo == null ? max : Math.max(rightInfo.max, max);

        return new MaxSubBSTSizeInfo(bstMaxNodeCount, xIsBst, min, max);
    }

    // 以下是对数器
    // for test
    public static TreeNode<Integer> generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode<Integer> generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode<Integer> head = new TreeNode<Integer>((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static int maxSubBSTSize1(TreeNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    public static int getBSTSize(TreeNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode<Integer>> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).data <= arr.get(i - 1).data) {
                return 0;
            }
        }
        return arr.size();
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
            TreeNode<Integer> head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
