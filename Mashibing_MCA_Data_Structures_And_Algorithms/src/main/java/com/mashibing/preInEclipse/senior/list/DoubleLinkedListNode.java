package com.mashibing.preInEclipse.senior.list;

/**
 * 双链表节点
 *
 * @author zhangzhiwang
 * @date 2022年2月14日 下午7:10:40
 */
public class DoubleLinkedListNode<T> {
	public T value;
	public DoubleLinkedListNode<T> next;
	public DoubleLinkedListNode<T> pre;

	public DoubleLinkedListNode() {
		super();
	}

	public DoubleLinkedListNode(T value) {
		super();
		this.value = value;
	}
}
