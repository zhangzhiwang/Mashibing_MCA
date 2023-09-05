package com.mashibing.list;

/**
 * 双链表节点
 */
public class DoubleNode<T> {
    public T data;
    public DoubleNode last;
    public DoubleNode next;

    public DoubleNode(T data) {
        this.data = data;
    }
}
