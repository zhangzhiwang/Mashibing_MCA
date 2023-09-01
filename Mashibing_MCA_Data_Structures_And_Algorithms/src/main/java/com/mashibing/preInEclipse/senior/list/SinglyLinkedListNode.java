package com.mashibing.preInEclipse.senior.list;

/**
 * 单链表节点
 *
 * @author zhangzhiwang
 * @date 2022年2月14日 下午7:10:40
 */
public class SinglyLinkedListNode<T> {
	public T value;
	public SinglyLinkedListNode<T> next;

	public SinglyLinkedListNode(T value) {
		super();
		this.value = value;
	}
}
