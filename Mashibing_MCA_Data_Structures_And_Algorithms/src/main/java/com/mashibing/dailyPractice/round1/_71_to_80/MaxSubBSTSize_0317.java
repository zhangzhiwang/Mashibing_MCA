package com.mashibing.dailyPractice.round1._71_to_80;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.tree.TreeNode;

import java.util.ArrayList;

/**
 * 给定一棵二叉树head，整棵二叉树不一定是搜索二叉树，但是它的某些子树是，返回它所有是搜索二叉树的子树中节点最多的那棵树的节点数
 */
public class MaxSubBSTSize_0317 {
    static class MaxSubBSTSizeInfo_0317 {
        private boolean isBST;
        private int min;
        private int max;
        private int maxCount;

        public MaxSubBSTSizeInfo_0317(boolean isBST, int min, int max, int maxCount) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.maxCount = maxCount;
        }
    }

    public static int maxSubBSTSize(TreeNode<Integer> head) {
        if(head == null) {
            return 0;
        }

        return recurse(head).maxCount;
    }

    private static MaxSubBSTSizeInfo_0317 recurse(TreeNode<Integer> x) {
        if(x == null) {
            return null;
        }

        MaxSubBSTSizeInfo_0317 leftInfo = recurse(x.left);// T,7,7,1
        MaxSubBSTSizeInfo_0317 rightInfo = recurse(x.right);
        boolean isLeftBST = leftInfo == null ? true : leftInfo.isBST && leftInfo.max < x.value;
        boolean isRightBST = rightInfo == null ? true : rightInfo.isBST && rightInfo.min > x.value;
        boolean isBST = isLeftBST && isRightBST;
        int maxCount = 0;
        /*
        isLeftBST的含义是以x为头的树的左子树是不是搜索二叉树，但是这里已经把x本身算进去了，因为上面对leftInfo.max和x.value的值做了比较，
        这里要说明的是当isLeftBST为false时不代表左子树不是搜索二叉树，而是代表x的左子树加上x自己整体不是搜索二叉树，有可能去掉x自己只看左子树那么左子树就是一棵BST。
        再说简单点：虽然左子树不是BST但是左子树中包含了BST（就像题目中描述的那样），那么无论isLeftBST是否为true都必须计算一次maxCount，右子树同理。
        所以下面在计算maxCount的值时不能说当isLeftBST是BST时就计算左边的maxCount，
         */
//        if(isLeftBST) {
//            maxCount = leftInfo == null ? maxCount : Math.max(leftInfo.maxCount, maxCount);
//        }
//        if(isRightBST) {
//            maxCount = rightInfo == null ? maxCount : Math.max(rightInfo.maxCount, maxCount);
//        }
        if(isBST) {
            maxCount = Math.max((leftInfo == null ? 0 : leftInfo.maxCount) +
                    (rightInfo == null ? 0 : rightInfo.maxCount) +
                    1, maxCount);
        } else {
//            maxCount = Math.max(leftInfo == null ? 1 : leftInfo.maxCount, maxCount);
            maxCount = leftInfo == null ? 1 : leftInfo.maxCount;
            maxCount = Math.max(rightInfo == null ? maxCount : rightInfo.maxCount, maxCount);
        }


        /*
        这道题和“判断以一颗数是不是搜索二叉树”那道题不一样，因为那道题一旦发现isBST为false就立即返回了，能走到计算min和max这里就说明isBST为true。
        而本题在isBST为false时不返回，所以min和max的值不能像那道题那样计算。
         */
//        int min = leftInfo == null ? x.value : leftInfo.min;
//        int max = rightInfo == null ? x.value : rightInfo.max;
        int min = leftInfo == null ? x.value : Math.min(leftInfo.min, x.value);
        min = rightInfo == null ? min : Math.min(rightInfo.min, min);
        int max = leftInfo == null ? x.value : Math.max(leftInfo.max, x.value);
        max = rightInfo == null ? max : Math.max(rightInfo.max, max);

        return new MaxSubBSTSizeInfo_0317(isBST, min, max, maxCount);
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
