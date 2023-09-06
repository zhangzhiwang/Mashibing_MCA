package com.mashibing.preInEclipse.junior.list;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.mashibing.preInEclipse.junior.array.sort.SortCommon;

/**
 * 合并多个有序链表
 * 题目：给定一个数组，这个数组里面的元素都是一个有序链表（单链表）的头部，即每一个元素代表一个有序链表，将这些链表融合到一起形成一个新的有序链表，返回新链表的头部
 * 举例：
 * 有三个链表：1-3-5，2-4-6，7-8-9，给定的数组arr=[1,2,7]，将这三条链表合并为1-2-3-4-5-6-7-8-9，返回头部节点1。
 * 
 * 思路：
 * 1、将数组的所有元素（各个链表的头部）全部放到优先级队列里面，使用小根堆
 * 2、弹出优先级队列里面的最小节点，然后将被弹出的节点的下一个节点放入队列中，如果下一个节点是空就不放，如此循环，直到队列不为空为止
 * 3、将弹出的节点依次穿起来就是排好序的最终链表，返回头部。
 * 
 * @author zhangzhiwang
 * @date 2022年1月23日 上午10:24:52
 */
public class MergeNList {
	public static void main(String[] args) {
		// 构造已知条件
		Node list1_1 = new Node(1);
		Node list1_2 = new Node(3);
		Node list1_3 = new Node(5);
		list1_1.next = list1_2;
		list1_2.next = list1_3;
		System.out.println("list1 = " + list1_1.value + " -> " + list1_1.next.value + " -> " + list1_1.next.next.value);

		Node list2_1 = new Node(2);
		Node list2_2 = new Node(4);
		Node list2_3 = new Node(6);
		list2_1.next = list2_2;
		list2_2.next = list2_3;
		System.out.println("list2 = " + list2_1.value + " -> " + list2_1.next.value + " -> " + list2_1.next.next.value);

		Node list3_1 = new Node(7);
		Node list3_2 = new Node(8);
		Node list3_3 = new Node(9);
		list3_1.next = list3_2;
		list3_2.next = list3_3;
		System.out.println("list3 = " + list3_1.value + " -> " + list3_1.next.value + " -> " + list3_1.next.next.value);

		Node[] nodeArr = { list1_1, list2_1, list3_1 };
		Node head = mergeNList(nodeArr);
		System.out.println("合并后：");
		System.out.println(head.value + " -> " + head.next.value + " -> " + head.next.next.value + " -> "
				+ head.next.next.next.value + " -> " + head.next.next.next.next.value + " -> "
				+ head.next.next.next.next.next.value + " -> " + head.next.next.next.next.next.next.value + " -> "
				+ head.next.next.next.next.next.next.next.value + " -> "
				+ head.next.next.next.next.next.next.next.next.value);
	}

	static class Node {
		private int value;
		private Node next;

		public Node(int value) {
			super();
			this.value = value;
		}
	}

	private static Node mergeNList(Node[] arr) {
		if (SortCommon.arrIsEmpty(arr)) {
			return null;
		}

		PriorityQueue<Node> heap = new PriorityQueue<>(new MyComparator());
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {// 防止原数组中有空元素
				heap.add(arr[i]);
			}
		}

		if (heap.isEmpty()) {
			return null;
		}

		Node head = heap.poll();// 从heap里面弹出来的第一个肯定是所有链表头部最小的，也是最后整个链表的头部
		/**
		 * 链表题目一般都要求返回最终链表的head，所以当确定下最终返回的链表的head之后，就不要再动了，用一个临时变量保存这个head，后续的所有操作都用这个临时变量，
		 * 哪个被指向的head只用于最后返回，这是一般链表题目通用的方法。
		 */
		Node cur = head;
		while (!heap.isEmpty()) {
			if (cur.next != null) {
				heap.add(cur.next);
			}
			cur.next = heap.poll();
			cur = cur.next;
		}

		return head;
	}

	static class MyComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			return o1.value - o2.value;
		}
	}
}
