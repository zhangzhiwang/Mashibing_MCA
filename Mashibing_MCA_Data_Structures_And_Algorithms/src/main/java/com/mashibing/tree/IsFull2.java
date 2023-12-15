package com.mashibing.tree;

/**
 * 判断一棵树是不是满二叉树（非定义版）
 * 思路：
 * 在com.mashibing.tree.IsFull中是按照满二叉树的定义来做的，也可以根据满二叉树的特点来做。满二叉树的特点就是：如果以x节点为头的子树，它的左右子树都是满二叉树且高度一致，整棵树就是满二叉树。
 * 使用递归的通用套路来做。
 */
public class IsFull2 {
    static class IsFull2Info {
        public boolean isFull;// 子树是不是满的
        public int height;// 子树的高度

        public IsFull2Info(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static boolean isFull2(TreeNode head) {
        if(head == null) {
            return true;
        }

        return recurse(head).isFull;
    }

    public static IsFull2Info recurse(TreeNode x) {
        if(x == null) {
            return new IsFull2Info(true, 0);
        }

        IsFull2Info leftInfo = recurse(x.left);
        IsFull2Info rightInfo = recurse(x.right);


        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new IsFull2Info(isFull, height);
    }
}
