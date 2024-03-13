package com.mashibing.tree;

/**
 * 判断两棵树是否完全一样
 * 完全一样就是每个节点的值一样，树的结构也一样
 * 课程：新手班课时44
 * 思路：见com.mashibing.preInEclipse.junior.tree.TreeEquals注释
 */
public class TreeEquals {
    public static void main(String[] args) {
        TreeNode<Integer> n1 = new TreeNode<>(1);
        n1.left = new TreeNode<>(2);
        n1.right = new TreeNode<>(3);

        n1.left.left = new TreeNode<>(4);
        n1.left.right = new TreeNode<>(5);
        n1.right.left = new TreeNode<>(6);
        n1.right.right = new TreeNode<>(7);

        TreeNode<Integer> n11 = new TreeNode<>(1);
        n11.left = new TreeNode<>(2);
        n11.left.left = new TreeNode<>(4);
        n11.left.left.right = new TreeNode<>(8);

        System.out.println(isTwoTreeEquals(n1, n1));

        //测试异或运算符
//        TreeEquals t1 = null;
//        TreeEquals t2 = null;
//        if(t1 == null ^ t2 == null) {
//            System.out.println(1);
//        } else {
//            System.out.println(2);
//        }
    }

    public static boolean isTwoTreeEquals(TreeNode<Integer> head1, TreeNode<Integer> head2) {
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
        if (!isTwoTreeEquals(head1.left, head2.left)) {
            return false;
        }

        if (!isTwoTreeEquals(head1.right, head2.right)) {
            return false;
        }

        return true;
    }
}
