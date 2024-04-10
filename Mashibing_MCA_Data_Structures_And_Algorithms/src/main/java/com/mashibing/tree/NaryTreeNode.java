package com.mashibing.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树节点
 * @param <T>
 */
public class NaryTreeNode<T> {
    public T data;
    public List<NaryTreeNode<T>> children = new ArrayList<>();

    public NaryTreeNode(T data) {
        this.data = data;
    }

    public NaryTreeNode(T data, List<NaryTreeNode<T>> children) {
        this.data = data;
        this.children = children;
    }

    @Override
    public String toString() {
        return "NaryTreeNode{" +
                "data=" + data +
                ", children=" + children +
                '}';
    }
}