package com.mashibing.dailyPractice.round1._2_1;

import com.mashibing.tree.TreeNode;

/**
 * 判断一棵树是不是平衡搜索二叉树
 * 这道题即使"判断是否是平衡树"和"判断是否是搜索树"这两道题的结合，这两道题的结果与一下就是这道题的结果
 */
public class BST_0201 {
    public static boolean isBST(TreeNode<Integer> head) {
        if(head == null) {
            return true;
        }

        boolean isBalanced = balanceRecurse(head).isBalanced;
        boolean isSearchBinary = searchRecurse(head).isSearchBinary;
        return isBalanced && isSearchBinary;
    }

    private static BalanceInfo balanceRecurse(TreeNode<Integer> head) {
        if(head == null) {
            return new BalanceInfo(0, true);
        }

        BalanceInfo leftInfo = balanceRecurse(head.left);
        BalanceInfo rightInfo = balanceRecurse(head.right);

        return new BalanceInfo(Math.max(leftInfo.height, rightInfo.height) + 1,
                leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) <= 1);
    }

    private static SearchInfo searchRecurse(TreeNode<Integer> head) {
        if(head == null) {
            return null;
        }

        SearchInfo leftInfo = searchRecurse(head.left);
        SearchInfo rightInfo = searchRecurse(head.right);

        boolean isLeftValid = leftInfo == null ? true : leftInfo.isSearchBinary && leftInfo.max < head.value;
        boolean isRightValid = rightInfo == null ? true : rightInfo.isSearchBinary && rightInfo.min > head.value;
        if(!isLeftValid || !isRightValid) {
            return new SearchInfo(false, 0, 0);
        }

        return new SearchInfo(true,
                leftInfo == null ? head.value : leftInfo.min,
                rightInfo == null ? head.value : rightInfo.max);
    }

    static class BalanceInfo {
        public int height;
        public boolean isBalanced;

        public BalanceInfo(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    static class SearchInfo {
        public boolean isSearchBinary;
        public int min;
        public int max;

        public SearchInfo(boolean isSearchBinary, int min, int max) {
            this.isSearchBinary = isSearchBinary;
            this.min = min;
            this.max = max;
        }
    }
}
