package com.mashibing.dailyPractice.round2._71_to_100._0715;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;

/**
 * 给定一棵二叉树head，整棵二叉树不一定是搜索二叉树，但是它的某些子树是，返回它所有是搜索二叉树的子树中节点最多的那棵树的头
 */
public class MaxSubBSTHead_0715 {
    static class MaxSubBSTHeadInfo_0715 {
        private int maxCount;
        private TreeNode<Integer> maxHead;
        private boolean isST;
        private int min;
        private int max;

        public MaxSubBSTHeadInfo_0715(int maxCount, TreeNode<Integer> maxHead, boolean isST, int min, int max) {
            this.maxCount = maxCount;
            this.maxHead = maxHead;
            this.isST = isST;
            this.min = min;
            this.max = max;
        }
    }

    public static TreeNode<Integer> maxSubBSTHead(TreeNode<Integer> root) {
        if(root == null) {
            return null;
        }

        return recurse(root).maxHead;
    }

    private static MaxSubBSTHeadInfo_0715 recurse(TreeNode<Integer> x) {
        if(x == null) {
            return null;
        }

        MaxSubBSTHeadInfo_0715 left = recurse(x.left);
        MaxSubBSTHeadInfo_0715 right = recurse(x.right);
        boolean isLeftST = left == null || (left.isST && left.max < x.value);
        boolean isRightST = right == null || (right.isST && right.min > x.value);
        boolean isST = isLeftST && isRightST;

        int min = left == null ? x.value : left.min;
        int max = right == null ? x.value : right.max;

        int leftMaxCount = left == null ? 0 : left.maxCount;
        int rightMaxCount = right == null ? 0 : right.maxCount;
        TreeNode<Integer> maxHead = null;
        int maxCount = 0;
        if(isST) {
            maxCount = leftMaxCount + rightMaxCount + 1;
            maxHead = x;
        } else if(leftMaxCount >= rightMaxCount) {
            maxCount = leftMaxCount;
            maxHead = left == null ? null : left.maxHead;
        } else {
            maxCount = rightMaxCount;
            maxHead = right == null ? null : right.maxHead;
        }

        return new MaxSubBSTHeadInfo_0715(maxCount, maxHead, isST, min, max);
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
