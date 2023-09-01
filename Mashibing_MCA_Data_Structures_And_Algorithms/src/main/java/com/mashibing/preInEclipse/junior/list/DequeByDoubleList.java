package com.mashibing.preInEclipse.junior.list;

/**
 * 用双向链表实现双端队列
 * 双端队列的英文是deque，是double end queue的简称
 * 双端队列就是既可以从头部插入和弹出元素，也可以从尾部插入和弹出元素
 * 
 * @author zhangzhiwang
 * @date 2022年1月21日 上午10:22:55
 */
public class DequeByDoubleList {
	public static void main(String[] args) {
		// 构造原始双向链表
		Node<Integer> node1 = new Node<>(10);
		Node<Integer> node2 = new Node<>(20);
		Node<Integer> node3 = new Node<>(30);
		node1.next = node2;
		node2.next = node3;
		node2.pre = node1;
		node3.pre = node2;

		System.out.println(node1);
		System.out.println(node1.next);
		System.out.println(node1.next.next);
		System.out.println("----------------");
		System.out.println(node3.pre);
		System.out.println(node3.pre.pre);
		System.out.println(node3.pre.pre.pre);
		System.out.println("----------------");

		MyDequeue<Integer> myDequeue = new MyDequeue<Integer>();
		System.out.println(myDequeue.popHead());
		System.out.println(myDequeue.popTail());
		myDequeue.offerHead(100);
		myDequeue.offerHead(200);
		myDequeue.offerHead(300);
		System.out.println(myDequeue.head);
		System.out.println(myDequeue.head.next);
		System.out.println(myDequeue.head.next.next);
		System.out.println(myDequeue.peekHead());
		System.out.println(myDequeue.peekTail());
		myDequeue.offerTail(400);
		System.out.println(myDequeue.peekHead());
		System.out.println(myDequeue.peekTail());
		System.out.println("size = " + myDequeue.getSize());
		Integer r1 = myDequeue.popHead();
		System.out.println(r1);
		System.out.println("size = " + myDequeue.getSize());
		System.out.println(myDequeue.peekHead());
		r1 = myDequeue.popTail();
		System.out.println(r1);
		System.out.println("size = " + myDequeue.getSize());
		System.out.println(myDequeue.peekTail());
		System.out.println(myDequeue.head);
		System.out.println(myDequeue.head.next);
		System.out.println(myDequeue.head.next.next);
		System.out.println(myDequeue.head.next.pre);
		System.out.println(myDequeue.head.next.pre.pre);
	}

	static class Node<V> {
		private V value;
		private Node<V> pre;
		private Node<V> next;

		public Node(V value, Node<V> pre, Node<V> next) {
			this.value = value;
			this.pre = pre;
			this.next = next;
		}

		public Node(V value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
	}

	static class MyDequeue<V> {
		private Node<V> head;
		private Node<V> tail;
		private int size;

		public int getSize() {
			return size;
		}

		/**
		 * 双端队列从头部插入元素
		 * 
		 * @param v
		 * @author zhangzhiwang
		 * @date 2022年1月21日 上午10:33:57
		 */
		public void  offerHead(V v) {
			/**
			 * 1 -> 2 -> 3 -> null
			 * null <=
			 */
			Node curr = new Node(v);
			if (head == null) {
				head = curr;
				tail = curr;
			} else {
				curr.next = head;
				head.pre = curr;
				head = curr;
			}

			size++;
		}

		public void offerTail(V v) {
			Node curr = new Node(v);
			if (tail == null) {
				head = curr;
				tail = curr;
			} else {
				tail.next = curr;
				curr.pre = tail;
				tail = curr;
			}

			size++;
		}

		public V popHead() {
			V result = null;
			if (head == null) {
				return result;
			}

			result = head.value;
			head = head.next;
			if (head == null) {
				tail = null;
			} else {
				head.pre = null;
			}

			size--;
			return result;
		}

		public V popTail() {
			V result = null;
			if (tail == null) {
				return result;
			}

			result = tail.value;
			tail = tail.pre;
			if (tail == null) {
				head = null;
			} else {
				tail.next = null;
			}

			size--;
			return result;
		}

		public V peekHead() {
			V result = null;
			if (head == null) {
				return result;
			}

			return head.value;
		}

		public V peekTail() {
			V result = null;
			if (tail == null) {
				return result;
			}

			return tail.value;
		}
	}
}
