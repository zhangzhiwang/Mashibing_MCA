package com.mashibing.preInEclipse.junior.list;

/**
 * 反转双向链表
 * 
 * @author zhangzhiwang
 * @date 2022年1月20日 下午5:48:36
 */
public class ReverseDoubleList {
	public static void main(String[] args) {
		// 构造双向链表
		Node node1 = new Node("node_1");
		Node node2 = new Node("node_2");
		Node node3 = new Node("node_3");
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setPre(node2);
		node2.setPre(node1);
		
		System.out.println("打印原始链表：");
		System.out.println(node1);
		System.out.println(node1.next);
		System.out.println(node1.next.next);
		System.out.println("---------------");
		System.out.println(node3.pre);
		System.out.println(node3.pre.pre);
		System.out.println("================");
		
		node1 = reverseDoubleList(node1);
		System.out.println("反转之后：");
		System.out.println(node1);
		System.out.println(node1.next);
		System.out.println(node1.next.next);
		System.out.println("---------------");
		System.out.println(node1.next.next.pre);
		System.out.println(node1.next.pre);
		System.out.println(node1.pre);
		System.out.println("---------------");
	}
	
	static class Node {
		private String value;
		private Node pre;
		private Node next;

		public Node(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Node getPre() {
			return pre;
		}

		public void setPre(Node pre) {
			this.pre = pre;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
	}
	
	/**
	 * 反转双向链表
	 * 
	 * @param head 传入原始链表的头部节点
	 * @return 返回反转后的链表的头部节点
	 * @author zhangzhiwang
	 * @date 2022年1月20日 下午6:39:28
	 */
	public static Node reverseDoubleList(Node head) {
		/**
		 * 	<-  1 => 2 => 3 => null
		 *    	       
		 *    	                 h
		 *                       n
		 *           		p
		 */
		Node pre = null;
		Node next = null;
		while(head != null) {
			/*
			在断开连接前保存next以防断开之后找不到了。双链表反转临时保存一下next节点就可以了，不需要刻意保存last节点，
			因为再将head往下移之前，head指向的就是当前节点，也就是下一轮的上一个节点，所以让pre指向head就相当于保存了下一轮的last节点
			 */
			next = head.next;
			head.next = pre;
			head.pre = next;
			pre = head;
			head = next;
			// head、pre、next每次循环都要往后移动，但要注意他们仨的移动顺序，先移谁后移谁
		}
		
		return pre;
	}
}
