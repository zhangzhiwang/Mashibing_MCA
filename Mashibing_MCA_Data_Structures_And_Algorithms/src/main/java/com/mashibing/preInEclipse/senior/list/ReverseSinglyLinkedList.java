package com.mashibing.preInEclipse.senior.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 反转单向链表
 * 思路：
 * 题目会给定head，额外设置两个变量pre和next，移动这两个变量，返回新头部
 *
 * @author zhangzhiwang
 * @date 2022年2月14日 下午7:05:02
 */
public class ReverseSinglyLinkedList {
	public static void main(String[] args) {
//		SinglyLinkedListNode<Integer> node1 = new SinglyLinkedListNode<Integer>(1);
//		SinglyLinkedListNode<Integer> node2 = new SinglyLinkedListNode<Integer>(2);
//		SinglyLinkedListNode<Integer> node3 = new SinglyLinkedListNode<Integer>(3);
//		node1.next = node2;
//		node2.next = node3;
//		System.out.println(node1.value + " -> " + node1.next.value + " -> " + node1.next.next.value + " -> " + node1.next.next.next);
//		
//		SinglyLinkedListNode<Integer> head = reverseSinglyLinkedList(node1);
//		System.out.println(head.value + " -> " + head.next.value + " -> " + head.next.next.value + " -> " + head.next.next.next);
		
		// 对数器
		int len = 50;
		int value = 100;
		int testTime = 100000;
		System.out.println("test begin!");
		for (int i = 0; i < testTime; i++) {
			SinglyLinkedListNode<Integer> node1 = generateRandomLinkedList(len, value);
			List<Integer> list1 = getLinkedListOriginOrder(node1);
			node1 = reverseSinglyLinkedList(node1);
			if (!checkLinkedListReverse(list1, node1)) {
				System.out.println("Oops1!");
			}

			SinglyLinkedListNode<Integer> node2 = generateRandomLinkedList(len, value);
			List<Integer> list2 = getLinkedListOriginOrder(node2);
			node2 = testReverseLinkedList(node2);
			if (!checkLinkedListReverse(list2, node2)) {
				System.out.println("Oops2!");
			}
		}
		System.out.println("test finish!");
	}
	
	private static SinglyLinkedListNode<Integer> reverseSinglyLinkedList(SinglyLinkedListNode<Integer> head) {
		if (head == null) {
			return null;
		}

		SinglyLinkedListNode pre = null;
		SinglyLinkedListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;// 链表题有一个整体的思路就是先把该保存的保存了，保存了之后再移动
			pre = head;// 先移动pre，再移动head
			head = next;
		}

		return pre;
	}
	
	public static SinglyLinkedListNode<Integer> generateRandomLinkedList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			return null;
		}
		size--;
		SinglyLinkedListNode<Integer> head = new SinglyLinkedListNode<Integer>((int) (Math.random() * (value + 1)));
		SinglyLinkedListNode<Integer> pre = head;
		while (size != 0) {
			SinglyLinkedListNode<Integer> cur = new SinglyLinkedListNode<Integer>((int) (Math.random() * (value + 1)));
			pre.next = cur;
			pre = cur;
			size--;
		}
		return head;
	}

	public static List<Integer> getLinkedListOriginOrder(SinglyLinkedListNode<Integer> head) {
		List<Integer> ans = new ArrayList<>();
		while (head != null) {
			ans.add(head.value);
			head = head.next;
		}
		return ans;
	}
	
	public static boolean checkLinkedListReverse(List<Integer> origin, SinglyLinkedListNode<Integer> head) {
		for (int i = origin.size() - 1; i >= 0; i--) {
			if (!origin.get(i).equals(head.value)) {
				return false;
			}
			head = head.next;
		}
		return true;
	}
	
	public static SinglyLinkedListNode<Integer> testReverseLinkedList(SinglyLinkedListNode<Integer> head) {
		if (head == null) {
			return null;
		}
		ArrayList<SinglyLinkedListNode<Integer>> list = new ArrayList<>();
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		list.get(0).next = null;
		int N = list.size();
		for (int i = 1; i < N; i++) {
			list.get(i).next = list.get(i - 1);
		}
		return list.get(N - 1);
	}
}
