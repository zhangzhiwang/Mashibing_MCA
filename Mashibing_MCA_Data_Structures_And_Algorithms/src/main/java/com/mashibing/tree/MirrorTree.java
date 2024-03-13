package com.mashibing.tree;

/**
 * 判断一棵树是不是镜面树
 * 课程：新手班课时45
 * 思路：见com.mashibing.preInEclipse.junior.tree.MirrorTree注释
 */
public class MirrorTree {
    public static void main(String[] args) {
        TreeNode<Integer> n1 = new TreeNode<>(1);
        n1.left = new TreeNode<>(2);
        n1.right = new TreeNode<>(2);
        n1.left.left = new TreeNode<>(3);
        n1.right.right = new TreeNode<>(3);

        System.out.println(isMirrorTree(n1, n1));
    }

    /**
     * 代码和com.mashibing.tree.TreeEquals#isTwoTreeEquals()方法一样，不一样的是：
     * 1、入参head1和head2都传入同一棵树的头节点，把一棵树当做两棵树来比较
     * 2、子树的比较是左子树和右子树比较，右子树和左子树比较
     * @param head1
     * @param head2
     * @return
     */
    public static boolean isMirrorTree(TreeNode<Integer> head1, TreeNode<Integer> head2) {
        if (head1 == null ^ head2 == null) {// head1和head2不同时为空
            return false;
        }

        if (head1 == null & head2 == null) {// 人为规定：都为空则认为一样
            return true;
        }

        // 走到这说明都不为空
        // 先判断值是不是一样
        if (head1.value != head2.value) {
            return false;
        }

        // 走到这说明值一样，递归判断左子树和右子树是不是一样
        if (!isMirrorTree(head1.left, head2.right)) {
            return false;
        }

        if (!isMirrorTree(head1.right, head2.left)) {
            return false;
        }

        return true;
    }
}
