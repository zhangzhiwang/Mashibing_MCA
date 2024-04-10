package com.mashibing.dailyPractice.round2._71_to_100;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;

/**
 * 给定一棵二叉树head，整棵二叉树不一定是搜索二叉树，但是它的某些子树是，返回它所有是搜索二叉树的子树中节点做多的那棵树的头
 */
public class MaxSubBSTHead_0403 {
    static class MaxSubBSTHeadInfo_0403 {
        private boolean isST;
        private int min;
        private int max;
        private int count;
        private TreeNode<Integer> head;

        public MaxSubBSTHeadInfo_0403(boolean isST, int min, int max, int count, TreeNode<Integer> head) {
            this.isST = isST;
            this.min = min;
            this.max = max;
            this.count = count;
            this.head = head;
        }
    }

    public static TreeNode<Integer> maxSubBSTHead(TreeNode<Integer> head) {
        if(head == null) {
            return null;
        }

        return recurse(head).head;
    }

    private static MaxSubBSTHeadInfo_0403 recurse(TreeNode<Integer> head) {
        if(head == null) {
            return null;
        }

        MaxSubBSTHeadInfo_0403 leftInfo = recurse(head.left);
        MaxSubBSTHeadInfo_0403 rightInfo = recurse(head.right);

        boolean leftIsST = leftInfo == null ? true : leftInfo.isST && leftInfo.max < head.value;
        boolean rightIsST = rightInfo == null ? true : rightInfo.isST && rightInfo.min > head.value;
        boolean isST = leftIsST && rightIsST;

        int min = leftInfo == null ? head.value : Math.min(leftInfo.min, head.value);
        min = rightInfo == null ? min : Math.min(min, rightInfo.min);
        int max = leftInfo == null ? head.value : Math.max(leftInfo.max, head.value);
        max = rightInfo == null ? max : Math.max(max, rightInfo.max);
        int count = 0;
        if(leftInfo == null && rightInfo == null) {
            count = 1;
        } else if (leftInfo == null) {
            count = isST ? rightInfo.count + 1 : rightInfo.count;
        } else if (rightInfo == null) {
            count = isST ? leftInfo.count + 1 : leftInfo.count;
        } else {
            count = isST ? leftInfo.count + rightInfo.count + 1 : Math.max(leftInfo.count, rightInfo.count);
        }

        TreeNode<Integer> h = null;
        if(isST) {
            h = head;
        } else if (leftInfo == null) {
            h = rightInfo.head;
        } else if (rightInfo == null) {
            h = leftInfo.head;
        } else {
            h = leftInfo.count >= rightInfo.count ? leftInfo.head : rightInfo.head;
        }

        return new MaxSubBSTHeadInfo_0403(isST, min, max, count, h);
    }

    // 对数器
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
            if (arr.get(i).value <= arr.get(i - 1).value) {
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
        int maxLevel = 2;
        int maxValue = 10;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode<Integer> head = generateRandomBST(maxLevel, maxValue);
            TreeNode<Integer> rightAnswer = maxSubBSTHead1(head);
            TreeNode<Integer> yourAnswer = maxSubBSTHead(head);
            // 注意：这个对数器有一个小bug：即当左右子树中如果存在节点数量相同的BST子树时，返回任意一个头都可以，但是本对数器只认左子树的头
            if (rightAnswer != yourAnswer) {
                System.out.println("Oops!");
                System.out.println("rightAnswer:" + rightAnswer);
                System.out.println("yourAnswer:" + yourAnswer);
                DuiShuQiUtil.printBinaryTree(head);
                break;
            }
        }
        System.out.println("finish!");
    }
}
