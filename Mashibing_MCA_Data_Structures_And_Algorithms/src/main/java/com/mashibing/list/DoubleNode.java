package com.mashibing.list;

/**
 * 双链表节点
 */
public class DoubleNode<T> {
    public T data;
    public DoubleNode<T> last;
    public DoubleNode<T> next;

    public DoubleNode(T data) {
        this.data = data;
    }
}
