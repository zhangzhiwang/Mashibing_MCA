package com.mashibing.tree;

/**
 * 判断一棵树是不是平衡二叉树
 * 课程：新手班课时48
 * 思路：见com.mashibing.preInEclipse.junior.tree.BalancedBinaryTree注释
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode<Integer> n1 = new TreeNode<>(1);
        n1.left = new TreeNode<>(2);
        n1.right = new TreeNode<>(3);

//        n1.left.left = new TreeNode<>(4);
//        n1.left.right = new TreeNode<>(5);
        n1.right.left = new TreeNode<>(6);
        n1.right.right = new TreeNode<>(7);
        n1.right.right.left = new TreeNode<>(8);

        BalanceTreeInfo info = isBalance(n1);
        System.out.println(info);
    }

    static class BalanceTreeInfo {
        private boolean isBalance;
        private int height;

        public BalanceTreeInfo(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "isBalance=" + isBalance +
                    ", height=" + height +
                    '}';
        }
    }

    public static BalanceTreeInfo isBalance(TreeNode<Integer> head) {
        if(head == null) {
            return new BalanceTreeInfo(true, 0);// 人为规定：当节点为空时就是平衡的，它的高度是0
        }

        BalanceTreeInfo leftInfo = isBalance(head.left);
        BalanceTreeInfo rightInfo = isBalance(head.right);

        // 判断一颗子树是不是平衡的标准是：它的左子树和右子树都是平衡的，且左子树和右子树高度差的绝对值<=1
        boolean isBalance = leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new BalanceTreeInfo(isBalance, height);
    }
}
