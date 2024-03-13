package com.mashibing.tree;

/**
 * 二叉树及其遍历
 * 课程：新手班课时44
 * 思路：见com.mashibing.preInEclipse.junior.tree.BinaryTree注释
 */
public class BinaryTree {
    public static void main(String[] args) {
        TreeNode<Integer> head = createBinaryTree();
//        pre(head);
//        mid(head);
        post(head);
    }

    public static TreeNode<Integer> createBinaryTree() {
        TreeNode<Integer> n1 = new TreeNode<>(1);
        n1.left = new TreeNode<>(2);
        n1.right = new TreeNode<>(3);

        n1.left.left = new TreeNode<>(4);
        n1.left.right = new TreeNode<>(5);
        n1.right.left = new TreeNode<>(6);
        n1.right.right = new TreeNode<>(7);

        return n1;
    }

    public static <T> void pre(TreeNode<T> head) {
        if (head == null) {
            return;
        }

        // 打印位置在pos1就是前序遍历，在pos2就是中序遍历，在pos3就是后序遍历
        // pos1
        System.out.println(head.value);
        pre(head.left);
        // pos2
        pre(head.right);
        // pos3
    }

    public static <T> void mid(TreeNode<T> head) {
        if (head == null) {
            return;
        }

        // 打印位置在pos1就是前序遍历，在pos2就是中序遍历，在pos3就是后序遍历
        // pos1
        mid(head.left);
        // pos2
        System.out.println(head.value);
        mid(head.right);
        // pos3
    }

    public static <T> void post(TreeNode<T> head) {
        if (head == null) {
            return;
        }

        // 打印位置在pos1就是前序遍历，在pos2就是中序遍历，在pos3就是后序遍历
        // pos1
        post(head.left);
        // pos2
        post(head.right);
        // pos3
        System.out.println(head.value);
    }
}
