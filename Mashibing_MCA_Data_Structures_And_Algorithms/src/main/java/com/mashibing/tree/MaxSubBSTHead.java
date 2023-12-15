package com.mashibing.tree;

import java.util.ArrayList;

/**
 * 题目：给定一棵二叉树head，整棵二叉树不一定是搜索二叉树，但是它的某些子树是，返回它所有是搜索二叉树的子树中节点做多的那棵树的头。
 * 思路：
 * 这个和com.mashibing.tree.MaxSubBSTSize大同小异，一个是返回最大节点数，一个是返回具有最大节点数的那棵搜索二叉子树的头
 *
 * 课程：体系班课时108
 */
public class MaxSubBSTHead {
    static class MaxSubBSTHeadInfo {
        public int bstMaxNodeCount;// 子树中是搜索二叉树的最大节点数
        public TreeNode<Integer> head;// 具有bstMaxNodeCount那棵树的头
        public boolean isBst;// 子树是否为bst
        public int min;// 子树最小值
        public int max;// 子树最大值

        public MaxSubBSTHeadInfo(int bstMaxNodeCount, TreeNode<Integer> head, boolean isBst, int min, int max) {
            this.bstMaxNodeCount = bstMaxNodeCount;
            this.head = head;
            this.isBst = isBst;
            this.min = min;
            this.max = max;
        }
    }

    public static TreeNode<Integer> maxSubBSTHead(TreeNode<Integer> head) {
        if(head == null) {
            return null;
        }

        return recurse(head).head;
    }

    public static MaxSubBSTHeadInfo recurse(TreeNode<Integer> x) {
        if(x == null) {
            return null;
        }

        MaxSubBSTHeadInfo leftInfo = recurse(x.left);
        MaxSubBSTHeadInfo rightInfo = recurse(x.right);

        boolean leftIsBst = leftInfo == null ? true : leftInfo.isBst;
        boolean rightIsBst = rightInfo == null ? true : rightInfo.isBst;
        boolean leftMaxLessThanX = leftInfo == null ? true : (leftInfo.max < x.data);
        boolean rightMinMoreThanX = rightInfo == null ? true : (rightInfo.min > x.data);

        boolean xIsBst = leftIsBst && rightIsBst && leftMaxLessThanX && rightMinMoreThanX;
        TreeNode<Integer> head = x;
        int bstMaxNodeCount = 0;
        if(!xIsBst) {
            if(leftInfo != null && rightInfo == null) {
                head = leftInfo.head;
                bstMaxNodeCount = leftInfo.bstMaxNodeCount;
            } else if (leftInfo == null && rightInfo != null) {
                head = rightInfo.head;
                bstMaxNodeCount = rightInfo.bstMaxNodeCount;
            } else if (leftInfo != null && rightInfo != null) {
                head = leftInfo.bstMaxNodeCount >= rightInfo.bstMaxNodeCount ? leftInfo.head : rightInfo.head;
                bstMaxNodeCount = Math.max(leftInfo.bstMaxNodeCount, rightInfo.bstMaxNodeCount);
            }
        } else {
            if(leftInfo != null) {
                bstMaxNodeCount += leftInfo.bstMaxNodeCount;
            }
            if(rightInfo != null) {
                bstMaxNodeCount += rightInfo.bstMaxNodeCount;
            }
            bstMaxNodeCount++;
        }

        int min = leftInfo == null ? x.data : Math.min(leftInfo.min, x.data);
        min = rightInfo == null ? min : Math.min(rightInfo.min, min);
        int max = leftInfo == null ? x.data : Math.max(leftInfo.max, x.data);
        max =  rightInfo == null ? max : Math.max(rightInfo.max, max);

        return new MaxSubBSTHeadInfo(bstMaxNodeCount, head, xIsBst, min, max);
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

    public static TreeNode<Integer> maxSubBSTHead1(TreeNode<Integer> head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        TreeNode<Integer> leftAns = maxSubBSTHead1(head.left);
        TreeNode<Integer> rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
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
            TreeNode<Integer> retHead1 = maxSubBSTHead1(head);
            TreeNode<Integer> retHead2 = maxSubBSTHead(head);
            if (retHead1 != retHead2) {
                BinaryTreeUtil.printBinaryTree(head);
                System.out.println("Oops!");
                System.out.println("对数器：" + retHead1.data);
                System.out.println("maxSubBSTHead：" + retHead2.data);
                return;
            }
        }
        System.out.println("finish!");
    }
}
