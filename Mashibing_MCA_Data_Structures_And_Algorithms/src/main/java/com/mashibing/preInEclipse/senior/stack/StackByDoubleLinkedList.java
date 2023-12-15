package com.mashibing.preInEclipse.senior.stack;

import com.mashibing.preInEclipse.senior.list.DoubleLinkedListNode;

/**
 * 双向链表实现栈
 *
 * @author zhangzhiwang
 * @date 2022年2月14日 下午10:03:17
 */
public class StackByDoubleLinkedList {
	private DoubleLinkedListNode<Integer> head;
	private int size;

	public void add(int num) {
		DoubleLinkedListNode<Integer> newNode = new DoubleLinkedListNode<Integer>(num);
		if (head == null) {
			head = newNode;
		} else {
			head.next = newNode;
			newNode.pre = head;
			head = newNode;
		}

		size++;
	}

	public Integer pop() {
		if (head == null) {
			return null;
		}

		DoubleLinkedListNode<Integer> result = head;
		head = head.pre;
		if(head != null) {
			head.next = null;// 避免被弹出的元素内存泄漏
		}
		size--;
		return result.value;
	}

	public DoubleLinkedListNode<Integer> getHead() {
		return head;
	}

	public int getSize() {
		return size;
	}

	public static void main(String[] args) {
		StackByDoubleLinkedList stack = new StackByDoubleLinkedList();
		System.out.println(stack.getSize());
		stack.add(10);
		System.out.println(stack.getSize());
		stack.add(20);
		System.out.println(stack.getSize());
		stack.add(30);
		System.out.println(stack.getSize());
		System.out.println("--------");
		
		stack.pop();
		
//		System.out.println(stack.pop());
	}
}
