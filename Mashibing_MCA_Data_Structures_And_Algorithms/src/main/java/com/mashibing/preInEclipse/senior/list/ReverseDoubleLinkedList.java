package com.mashibing.preInEclipse.senior.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 反转双向链表
 * 思路：
 * 题目会给定head，额外设置两个变量pre和next，移动这两个变量，返回新头部
 *
 * @author zhangzhiwang
 * @date 2022年2月14日 下午7:05:02
 */
public class ReverseDoubleLinkedList {
	public static void main(String[] args) {
//		DoubleLinkedListNode<Integer> node1 = new DoubleLinkedListNode<Integer>(1);
//		DoubleLinkedListNode<Integer> node2 = new DoubleLinkedListNode<Integer>(2);
//		DoubleLinkedListNode<Integer> node3 = new DoubleLinkedListNode<Integer>(3);
//		node1.next = node2;
//		node2.next = node3;
//		node2.pre = node1;
//		node3.pre = node2;
//		System.out.println(node1.value + " -> " + node1.next.value + " -> " + node1.next.next.value + " -> " + node1.next.next.next);
//		System.out.println(node3.value + " -> " + node3.pre.value + " -> " + node3.pre.pre.value + " -> " + node3.pre.pre.pre);
//		
//		System.out.println("--------------");
//		DoubleLinkedListNode<Integer> head = reverseDoubleLinkedList(node1);
//		System.out.println(head.value + " -> " + head.next.value + " -> " + head.next.next.value + " -> " + head.next.next.next);
//		System.out.println(head.next.next.value + " -> " + head.next.next.pre.value + " -> " + head.next.next.pre.pre.value + " -> " + head.next.next.pre.pre.pre);
		
		// 对数器
		int len = 50;
		int value = 100;
		int testTime = 100000;
		System.out.println("test begin!");
		for (int i = 0; i < testTime; i++) {
			DoubleLinkedListNode<Integer> node3 = generateRandomDoubleList(len, value);
			List<Integer> list3 = getDoubleListOriginOrder(node3);
			node3 = reverseDoubleLinkedList(node3);
			if (!checkDoubleListReverse(list3, node3)) {
				System.out.println("Oops3!");
			}

			DoubleLinkedListNode<Integer> node4 = generateRandomDoubleList(len, value);
			List<Integer> list4 = getDoubleListOriginOrder(node4);
			node4 = reverseDoubleLinkedList(node4);
			if (!checkDoubleListReverse(list4, node4)) {
				System.out.println("Oops4!");
			}

		}
		System.out.println("test finish!");
	}
	
	private static DoubleLinkedListNode<Integer> reverseDoubleLinkedList(DoubleLinkedListNode<Integer> head) {
		if (head == null) {
			return null;
		}

		DoubleLinkedListNode<Integer> pre = null;
		DoubleLinkedListNode<Integer> next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;// 链表题有一个整体的思路就是先把该保存的保存了，保存了之后再移动
			head.pre = next;
			pre = head;// 先移动pre，再移动head
			head = next;
		}

		return pre;
	}
	
	public static DoubleLinkedListNode<Integer> generateRandomDoubleList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			return null;
		}
		size--;
		DoubleLinkedListNode<Integer> head = new DoubleLinkedListNode<Integer>((int) (Math.random() * (value + 1)));
		DoubleLinkedListNode<Integer> pre = head;
		while (size != 0) {
			DoubleLinkedListNode<Integer> cur = new DoubleLinkedListNode<Integer>((int) (Math.random() * (value + 1)));
			pre.next = cur;
			cur.pre = pre;
			pre = cur;
			size--;
		}
		return head;
	}
	
	public static boolean checkDoubleListReverse(List<Integer> origin, DoubleLinkedListNode<Integer> head) {
		DoubleLinkedListNode<Integer> end = null;
		for (int i = origin.size() - 1; i >= 0; i--) {
			if (!origin.get(i).equals(head.value)) {
				return false;
			}
			end = head;
			head = head.next;
		}
		for (int i = 0; i < origin.size(); i++) {
			if (!origin.get(i).equals(end.value)) {
				return false;
			}
			end = end.pre;
		}
		return true;
	}
	
	public static List<Integer> getDoubleListOriginOrder(DoubleLinkedListNode<Integer> head) {
		List<Integer> ans = new ArrayList<>();
		while (head != null) {
			ans.add(head.value);
			head = head.next;
		}
		return ans;
	}
}
