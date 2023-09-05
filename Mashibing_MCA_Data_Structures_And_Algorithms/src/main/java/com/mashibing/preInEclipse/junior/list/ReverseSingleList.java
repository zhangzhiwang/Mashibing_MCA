package com.mashibing.preInEclipse.junior.list;

/**
 * 反转单链表
 * 
 * @author zhangzhiwang
 * @date 2022年1月20日 下午5:48:36
 */
public class ReverseSingleList {
	public static void main(String[] args) {
		// 构造单链表
		System.out.println("原始链表：");
		Node node1 = new Node("node_1");
		Node node2 = new Node("node_2");
		Node node3 = new Node("node_3");
		node1.setNext(node2);
		node2.setNext(node3);
		System.out.println(node1);
		System.out.println(node1.next);
		System.out.println(node1.next.next);
		System.out.println("==================");
		
		node1 = reverseSingleList(node1);
		System.out.println("反转之后：");
		System.out.println(node1);
		System.out.println(node1.next);
		System.out.println(node1.next.next);
	}
	
	/**
	 * 反转单链表
	 * 
	 * @param head 传入原始链表的头部节点
	 * @return 返回反转后的链表的头部节点。
	 *         传入的head是一个引用拷贝，在本方法里面怎么变化都是在折腾这个拷贝的引用，原引用head保持不变，
	 *         所以最后一定要返回新的头部并在调用的地方赋值给head引用。
	 * @author zhangzhiwang
	 * @date 2022年1月20日 下午6:39:28
	 */
	public static Node reverseSingleList(Node head) {
		/**
		 *  <-1 <- 2 <- 3 -> null
		 *  	   	    	h
		 *  	 	  		n
		 *    	   		p  
		 */
		Node pre = null;
		Node next = null;
		while(head != null) {
			next = head.next;// 首先要将下一个节点保存起来，避免断开连接之后找不到下一个节点
			head.next = pre;// 让当前节点的下一个节点指向前一个节点
			pre = head;// 在上一步完成后将前一节点指向当前节点，因为接下来当前节点也要往后移
			head = next;// 将当前节点指向下一节点
			// head、pre、next每次循环都要往后移动，但要注意他们仨的移动顺序，先移谁后移谁
		}
		
		return pre;
	}

	static class Node {
		private String value;
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

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
}
