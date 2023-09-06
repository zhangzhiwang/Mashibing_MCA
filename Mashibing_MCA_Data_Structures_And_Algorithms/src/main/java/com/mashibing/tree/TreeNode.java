package com.mashibing.tree;

import com.mashibing.preInEclipse.junior.tree.BinaryTree;

/**
 * 二叉树的节点
 */
public class TreeNode<T> {
    public T data;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
    }
}
