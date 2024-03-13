package com.mashibing.preInEclipse.junior.list;

/**
 * 合并两个有序链表
 * 题目：给定两个有序链表的头部h1和h2，合并这两个链表后组成一个新的有序链表，返回新链表的头部
 * 
 * 思路：
 * 比如两个链表：l1：1 -> 3 -> 5
 * l2：2 -> 4 -> 6 -> 8
 * 1、比较两个链表的头部的大小，谁小谁当新链表的头部
 * 2、准备两个临时变量（next1和next2）分别保存小头链表的下一个节点和大头链表的头部，还有一个变量保存当前节点
 * 3、next1和next2作比较，假如next1小，那么next1就是当前节点的下一个节点，让next1后移，next2不动，直到遍历完其中一个链表为止
 * 4、将尾结点连上
 * 
 * @author zhangzhiwang
 * @date 2022年1月23日 上午10:24:52
 */
public class MergeTwoList {
	public static void main(String[] args) {
		/**
		 * 1 -> 3
		 * 2 -> 4 -> 6 -> 8
		 */
		Node l1_1 = new Node(1);
		Node l1_3 = new Node(3);
		l1_1.next = l1_3;
		System.out.println("l1:");
		System.out.println(l1_1.value + " -> " + l1_1.next.value);

		Node l2_2 = new Node(2);
		Node l2_4 = new Node(4);
		Node l2_6 = new Node(6);
		Node l2_8 = new Node(8);
		l2_2.next = l2_4;
		l2_4.next = l2_6;
		l2_6.next = l2_8;
		System.out.println("l2:");
		System.out.println(l2_2.value + " -> " + l2_2.next.value + " -> " + l2_2.next.next.value + " -> "
				+ l2_2.next.next.next.value);

		Node head = mergeTwoList(l2_2, l1_1);
		System.out.println("合并后：");
		System.out.println(head.value + " -> " + head.next.value + " -> " + head.next.next.value + " -> "
				+ head.next.next.next.value + " -> " + head.next.next.next.next.value + " -> " + head.next.next.next.next.next.value);
	}

	static class Node {
		private int value;
		private Node next;

		public Node(int value) {
			this.value = value;
		}
	}

	/**
	 * 合并两个有序链表
	 * 
	 * @param head1 有序链表1的头部
	 * @param head2 有序链表2的头部
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月23日 上午10:34:39
	 */
	private static Node mergeTwoList(Node head1, Node head2) {
		/**
		 * 1 -> 3
		 * 2 -> 4 -> 6 -> 8
		 */
		if (head1 == null || head2 == null) {
			return head1 == null ? head2 : head1;
		}

		// 判断两个链表谁的头部小，那么这个头部就是最终返回的头部
		Node resultHead = head1.value <= head2.value ? head1 : head2;
		Node cur = resultHead;
		// 准备两个变量分别保存小头链表的下一个节点和大头链表的头部
		Node next1 = cur.next;
		Node next2 = cur == head1 ? head2 : head1;
		Node pre = cur;
		while (next1 != null && next2 != null) {
			if (next1.value <= next2.value) {
				pre.next = next1;
				pre = next1;
				next1 = next1.next;
			} else {
				pre.next = next2;
				pre = next2;
				next2 = next2.next;
			}
		}

		pre.next = next1 == null ? next2 : next1;
		return resultHead;
	}
}
