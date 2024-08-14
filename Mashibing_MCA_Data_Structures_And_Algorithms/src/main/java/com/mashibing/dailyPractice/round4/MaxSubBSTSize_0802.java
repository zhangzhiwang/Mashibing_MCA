package com.mashibing.dailyPractice.round4;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;

/**
 * 给定一棵二叉树head，整棵二叉树不一定是搜索二叉树，但是它的某些子树是，返回它所有是搜索二叉树的子树中节点最多的那棵树的节点数
 */
public class MaxSubBSTSize_0802 {
    static class MaxSubBSTSizeInfo_0802 {
        private int min;
        private int max;
        private int maxCount;
        private boolean isST;

        public MaxSubBSTSizeInfo_0802(int min, int max, int maxCount, boolean isST) {
            this.min = min;
            this.max = max;
            this.maxCount = maxCount;
            this.isST = isST;
        }
    }

    public static int maxSubBSTSize(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        return recurse(root).maxCount;
    }

    private static MaxSubBSTSizeInfo_0802 recurse(TreeNode<Integer> root) {
        if(root == null) {
            return null;
        }

        MaxSubBSTSizeInfo_0802 left = recurse(root.left);
        MaxSubBSTSizeInfo_0802 right = recurse(root.right);
        boolean isLeftST = left == null ? true : left.isST && left.max < root.value;
        boolean isRightST = right == null ? true : right.isST && right.min > root.value;
        boolean isST = isLeftST && isRightST;

        int maxCount = 0;
        if(!isST) {
            if(left == null && right != null) {
                maxCount = right.maxCount;
            } else if (left != null && right == null) {
                maxCount = left.maxCount;
            } else {
                maxCount = Math.max(left.maxCount, right.maxCount);
            }
        } else {
            maxCount = (left == null ? 0 : left.maxCount) + (right == null ? 0 : right.maxCount) + 1;
        }

        int min = left == null ? root.value : Math.min(left.min, root.value);
        min = right == null ? min : Math.min(right.min, min);
        int max = left == null ? root.value : Math.max(left.max, root.value);
        max = right == null ? max : Math.max(right.max, max);
        return new MaxSubBSTSizeInfo_0802(min, max, maxCount, isST);
    }

    // 对数器
    // 为了验证
    // 对数器方法
    public static int right(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    // 为了验证
    // 对数器方法
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

    // 为了验证
    // 对数器方法
    public static void in(TreeNode<Integer> head, ArrayList<TreeNode<Integer>> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 为了验证
    // 对数器方法
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // 为了验证
    // 对数器方法
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // 为了验证
    // 对数器方法
    public static void main(String[] args) {
//        TreeNode<Integer> n4 = new TreeNode<>(3);
//        TreeNode<Integer> n3 = new TreeNode<>(2);
//        TreeNode<Integer> n8 = new TreeNode<>(3);
//        n4.left = n3;
//        n3.right = n8;
//        DuiShuQiUtil.printBinaryTree(n4);
//        System.out.println(maxSubBSTSize(n4));

        int maxLevel = 3;
        int maxValue = 10;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            int rightAnswer = right(head);
            int yourAnswer = maxSubBSTSize(head);
            if (yourAnswer != rightAnswer) {
                DuiShuQiUtil.printBinaryTree(head);
                System.out.println("出错了！");
                System.out.println("正确答案：" + rightAnswer);
                System.out.println("你的答案：" + yourAnswer);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
