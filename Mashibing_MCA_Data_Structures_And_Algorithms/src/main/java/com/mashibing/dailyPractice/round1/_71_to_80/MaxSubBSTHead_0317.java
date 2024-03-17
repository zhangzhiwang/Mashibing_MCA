package com.mashibing.dailyPractice.round1._71_to_80;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;

/**
 * 给定一棵二叉树head，整棵二叉树不一定是搜索二叉树，但是它的某些子树是，返回它所有是搜索二叉树的子树中节点做多的那棵树的头
 */
public class MaxSubBSTHead_0317 {
    static class MaxSubBSTHeadInfo_0317 {
        private boolean isBST;
        private int min;
        private int max;
        private int maxCount;
        private TreeNode<Integer> head;

        public MaxSubBSTHeadInfo_0317(boolean isBST, int min, int max, int maxCount) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.maxCount = maxCount;
        }

        public MaxSubBSTHeadInfo_0317(boolean isBST, int min, int max, int maxCount, TreeNode<Integer> head) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.maxCount = maxCount;
            this.head = head;
        }
    }

    public static TreeNode<Integer> maxSubBSTHead(TreeNode<Integer> head) {
        if(head == null) {
            return null;
        }

        return recurse(head).head;
    }

    private static MaxSubBSTHeadInfo_0317 recurse(TreeNode<Integer> x) {
        if(x == null) {
            return null;
        }

        MaxSubBSTHeadInfo_0317 leftInfo = recurse(x.left);// T,5,5,1,5
        MaxSubBSTHeadInfo_0317 rightInfo = recurse(x.right);// T,2,2,1,2
        boolean leftBST = leftInfo == null ? true : leftInfo.isBST && leftInfo.max < x.value;
        boolean rightBST = rightInfo == null ? true : rightInfo.isBST && rightInfo.min > x.value;
        boolean isBST = leftBST && rightBST;

        int maxCount = 0;
        TreeNode<Integer> head = null;
        if(isBST) {
            maxCount = (leftInfo == null ? 0 : leftInfo.maxCount) + (rightInfo == null ? 0 : rightInfo.maxCount) + 1;
            head = x;
        } else {
            if(leftInfo != null && leftInfo.isBST) {
                head = leftInfo.head;
            }
            maxCount = leftInfo == null ? 1 : leftInfo.maxCount;

            /*
             下面的if判断中最后的一个判断条件“head == null”不写也可以，因为左右子树中是BST的子树的数量最大值如果相等，那么头设置为任意一个即可，
             但是这种情况对数器只认左子树的头，所以为了应和对数器加了这个判断条件
             */
            if(rightInfo != null && rightInfo.isBST && rightInfo.maxCount >= maxCount && head == null) {
                head = rightInfo.head;
            }
            maxCount = rightInfo == null ? maxCount : Math.max(rightInfo.maxCount, maxCount);
        }

        int min = leftInfo == null ? x.value : Math.min(leftInfo.min, x.value);
        min = rightInfo == null ? min : Math.min(rightInfo.min, min);
        int max = leftInfo == null ? x.value : Math.max(leftInfo.max, x.value);
        max = rightInfo == null ? max : Math.max(rightInfo.max, max);
        return new MaxSubBSTHeadInfo_0317(isBST, min, max, maxCount, head);
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
