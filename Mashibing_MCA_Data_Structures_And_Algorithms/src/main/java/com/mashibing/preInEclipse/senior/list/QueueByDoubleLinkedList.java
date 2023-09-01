package com.mashibing.preInEclipse.senior.list;

/**
 * 用双向链表实现队列
 *
 * @author zhangzhiwang
 * @date 2022年2月14日 下午9:40:10
 */
public class QueueByDoubleLinkedList {
	private DoubleLinkedListNode<Integer> head = null;
	private DoubleLinkedListNode<Integer> tail = null;
	private int size;

	public void add(int num) {
		// 将num封装成节点
		DoubleLinkedListNode<Integer> newNode = new DoubleLinkedListNode<Integer>(num);
		if (tail == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.pre = tail;
			tail = newNode;
		}

		size++;
	}

	public Integer pop() {
		if (head == null) {
			return null;
		}

		DoubleLinkedListNode<Integer> result = head;
		head = head.next;
		if (head != null) {
			head.pre = null;
		}

		size--;
		return result.value;
	}

	public int getSize() {
		return size;
	}

	public DoubleLinkedListNode<Integer> getHead() {
		return head;
	}

	public DoubleLinkedListNode<Integer> getTail() {
		return tail;
	}

	public static void main(String[] args) {
		QueueByDoubleLinkedList queue = new QueueByDoubleLinkedList();
		System.out.println(queue.getSize());

		queue.add(10);
		DoubleLinkedListNode<Integer> head = queue.getHead();
		System.out.println(head.value);
		System.out.println(queue.getSize());
		queue.add(20);
		System.out.println(queue.getSize());
		queue.add(30);
		System.out.println(queue.getSize());
		System.out.println("-----------");
		Integer result1 = queue.pop();
		System.out.println(result1);
		System.out.println(queue.getSize());
		result1 = queue.pop();
		System.out.println(result1);
		System.out.println(queue.getSize());
		result1 = queue.pop();
		System.out.println(result1);
		System.out.println(queue.getSize());
	}
}
