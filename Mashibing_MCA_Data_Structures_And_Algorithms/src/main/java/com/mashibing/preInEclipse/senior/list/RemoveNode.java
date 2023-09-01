package com.mashibing.preInEclipse.senior.list;

/**
 * 删除具有指定值的节点（单链表）
 * 思路：
 * 1、先找到第一个不为指定值的节点作为新头
 * 2、找到新头后向后遍历所有节点，遇到指定值跳过，直到遇到不是指定值的节点，然后往上挂关系
 * 
 * 
 * @author zhangzhiwang
 * @date 2022年2月14日 下午7:53:02
 */
public class RemoveNode {
	public static void main(String[] args) {
		SinglyLinkedListNode<Integer> node2_0 = new SinglyLinkedListNode<Integer>(2);
		SinglyLinkedListNode<Integer> node2_1 = new SinglyLinkedListNode<Integer>(2);
		SinglyLinkedListNode<Integer> node2_2 = new SinglyLinkedListNode<Integer>(2);
		SinglyLinkedListNode<Integer> node4 = new SinglyLinkedListNode<Integer>(4);
		SinglyLinkedListNode<Integer> node2_3 = new SinglyLinkedListNode<Integer>(2);
		SinglyLinkedListNode<Integer> node2_4 = new SinglyLinkedListNode<Integer>(2);
		SinglyLinkedListNode<Integer> node1 = new SinglyLinkedListNode<Integer>(1);
		SinglyLinkedListNode<Integer> node2_5 = new SinglyLinkedListNode<Integer>(2);
		SinglyLinkedListNode<Integer> node5 = new SinglyLinkedListNode<Integer>(5);
		node2_0.next = node2_1;
		node2_1.next = node2_2;
		node2_2.next = node4;
		node4.next = node2_3;
		node2_3.next = node2_4;
		node2_4.next = node1;
		node1.next = node2_5;
		node2_5.next = node5;

		System.out.println(node2_0.value + " -> " + node2_0.next.value + " -> " + node2_0.next.next.value + " -> " + node2_0.next.next.next.value + " -> " + node2_0.next.next.next.next.value + " -> " + node2_0.next.next.next.next.next.value + " -> " + node2_0.next.next.next.next.next.next.value + " -> " + node2_0.next.next.next.next.next.next.next.value + " -> " + node2_0.next.next.next.next.next.next.next.next.value + " -> " + node2_0.next.next.next.next.next.next.next.next.next);

		node2_0 = removeNode(node2_0, 2);
		System.out.println(node2_0.value + " -> " + node2_0.next.value + " -> " + node2_0.next.next.value + " -> " + node2_0.next.next.next);
	}

	/**
	 * 删除指定值num的Node
	 * 
	 * @param head 链表新的头部
	 * @param num
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月14日 下午8:02:06
	 */
	private static SinglyLinkedListNode<Integer> removeNode(SinglyLinkedListNode<Integer> head, int num) {
		if (head == null) {
			return null;
		}

		while (head != null) {// 找新头，第一个不为num的节点的就是新头
			if (head.value == num) {
				head = head.next;
				continue;
			}
			break;
		}

		if (head == null) {// 上面的while循环结束head可能为null，就是所有的节点都是指定值
			return null;
		}

		SinglyLinkedListNode<Integer> pre = head;
		SinglyLinkedListNode<Integer> cur = head;// 链表的通用思路：找到言返回的头结点后，用一个变量（比如cur）保存这个head，head就不要再动了，后面的操作都由这个临时变量来替代
		SinglyLinkedListNode<Integer> next = null;
		while (cur != null) {
			if (cur.next != null && cur.next.value == num) {
				next = cur.next;
				cur = next;
				continue;
			}
			next = cur.next;
			cur = next;
			pre.next = cur;
			pre = cur;
		}

		return head;
	}
}
