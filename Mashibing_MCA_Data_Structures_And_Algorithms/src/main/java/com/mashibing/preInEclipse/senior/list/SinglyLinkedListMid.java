package com.mashibing.preInEclipse.senior.list;

/**
 * 单链表找中
 * 题目：
 * 1、输入链表头结点，如果总节点个数是奇数返回中间节点，否则返回上中点
 * 2、输入链表头结点，如果总节点个数是奇数返回中间节点，否则返回下中点
 * 3、输入链表头结点，如果总节点个数是奇数返回中间节点的上一个节点，否则返回上中点的前一个节点
 * 4、输入链表头结点，如果总节点个数是奇数返回中间节点的上一个节点，否则返回上中点的后一个节点
 * 解释：
 * 如果节点个数是偶数个，比如有四个节点，第二个节点是上中点，第三个节点是下中点
 * 
 * 思路：
 * 用快慢指针，快指针一次移动两个位置，慢指针一次移动一个位置
 *
 * @author zhangzhiwang
 * @date 2022年2月19日 上午11:12:55
 */
public class SinglyLinkedListMid {
	public static void main(String[] args) {
		SinglyLinkedListNode<Integer> node1 = new SinglyLinkedListNode<>(1);
		SinglyLinkedListNode<Integer> node2 = new SinglyLinkedListNode<>(2);
		SinglyLinkedListNode<Integer> node3 = new SinglyLinkedListNode<>(3);
		SinglyLinkedListNode<Integer> node4 = new SinglyLinkedListNode<>(4);
		SinglyLinkedListNode<Integer> node5 = new SinglyLinkedListNode<>(5);
		SinglyLinkedListNode<Integer> node6 = new SinglyLinkedListNode<>(6);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
//		SinglyLinkedListNode<Integer> result = findMidOrUpMid(node1);
//		SinglyLinkedListNode<Integer> result = findMidOrDownMid(node1);
//		SinglyLinkedListNode<Integer> result = findMidPreOrUpMidPre(node1);
		SinglyLinkedListNode<Integer> result = findMidPreOrDownMidPre(node1);
		System.out.println(result.value);
	}
	
	/**
	 * 找到中点或上中点
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月19日 上午11:20:47
	 */
	private static SinglyLinkedListNode<Integer> findMidOrUpMid(SinglyLinkedListNode<Integer> head) {
		if (head == null || head.next == null || head.next.next == null) {// 头结点为空、只有一个、只有两个节点都返回返回头结点
			return head;
		}
		
		// 过了上面的判断说明至少有3个节点
		SinglyLinkedListNode<Integer> slow = head.next;// 慢指针从头结点的下个位置为起点
		SinglyLinkedListNode<Integer> fast = head.next.next;// 快指针从头结点的下两个位置为起点
		/**
		 * 为什么用fast来做循环条件？因为fast移动的步多，slow移动的步少，判断条件用快指针的下个节点和下下个节点不为空来做判断是为了保证快指针能够往下移动一步，
		 * 如果快指针能够往下移动一步那慢指针也能
		 */
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;// 一次移动一个位置
			fast = fast.next.next;// 一次移动两个位置
		}
		
		return slow;
	}
	
	/**
	 * 找到中点或下中点
	 * 
	 * @param head
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月19日 上午11:35:13
	 */
	private static SinglyLinkedListNode<Integer> findMidOrDownMid(SinglyLinkedListNode<Integer> head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		SinglyLinkedListNode<Integer> slow = head.next;// 快慢指针都从头结点的下个位置开始
		SinglyLinkedListNode<Integer> fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;// 一次移动一个位置
			fast = fast.next.next;// 一次移动两个位置
		}
		
		return slow;
	}
	
	/**
	 * 找到中点的上个节点或上中点的上个节点
	 * 
	 * @param head
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月19日 上午11:40:22
	 */
	private static SinglyLinkedListNode<Integer> findMidPreOrUpMidPre(SinglyLinkedListNode<Integer> head) {
		if(head == null || head.next == null || head.next.next == null) {// 0、1、2个节点返回null
			return null;
		}

		// 能到这里说明至少有3个节点
		SinglyLinkedListNode<Integer> slow = head;
		SinglyLinkedListNode<Integer> fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;// 一次移动一个位置
			fast = fast.next.next;// 一次移动两个位置
		}
		
		return slow;
	}
	
	/**
	 * 找到中点的上个节点或下中点的上个节点
	 * 
	 * @param head
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月19日 上午11:51:15
	 */
	private static SinglyLinkedListNode<Integer> findMidPreOrDownMidPre(SinglyLinkedListNode<Integer> head) {
		if(head == null || head.next == null || head.next.next == null) {// 0、1、2个节点返回null
			return null;
		}
		
		// 能到这里说明至少有3个节点
		SinglyLinkedListNode<Integer> slow = head;
		SinglyLinkedListNode<Integer> fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;// 一次移动一个位置
			fast = fast.next.next;// 一次移动两个位置
		}
		
		return slow;
	}
}
