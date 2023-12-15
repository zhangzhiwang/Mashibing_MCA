package com.mashibing.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树节点
 */
public class MultiTreeNode<T> {
    public T data;
    public List<MultiTreeNode<T>> children = new ArrayList<>();

    public MultiTreeNode(T data) {
        this.data = data;
    }

    public MultiTreeNode(List<MultiTreeNode<T>> children) {
        this.children = children;
    }

    public MultiTreeNode(T data, List<MultiTreeNode<T>> children) {
        this.data = data;
        this.children = children;
    }
}
