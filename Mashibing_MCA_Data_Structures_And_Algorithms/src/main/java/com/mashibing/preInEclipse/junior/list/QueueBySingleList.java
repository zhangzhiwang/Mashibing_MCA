package com.mashibing.preInEclipse.junior.list;

/**
 * 使用单链表构造队列
 * 
 * @author zhangzhiwang
 * @date 2022年1月20日 下午8:11:48
 */
public class QueueBySingleList {
	public static void main(String[] args) {
		MyQueue<Integer> myQueue = new MyQueue<>();
//		System.out.println(myQueue.size);
		
		myQueue.offer(1);
		myQueue.offer(2);
		myQueue.offer(3);
		System.out.println(myQueue.size);
		
		Integer result1 = myQueue.pop();
		System.out.println("result = " + result1);
		System.out.println("size = " + myQueue.size);
		Integer result2 = myQueue.pop();
		System.out.println("result = " + result2);
		System.out.println("size = " + myQueue.size);
		
		Integer peek = myQueue.peek();
		System.out.println("peek = " + peek);
		Integer result3 = myQueue.pop();
		System.out.println("result = " + result3);
		System.out.println("size = " + myQueue.size);
	}
	
	static class Node<V> {
		private V value;
		private Node<V> next;

		public Node(V value) {
			this.value = value;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}

	static class MyQueue<V> {
		private Node<V> head;// 注意：由于队列是从队尾插入从队头弹出，所以队尾和队头都要进行元素的操作，所以在队列的结构体里面要维护头结点和尾结点。
		private Node<V> tail;
		private int size;

		public MyQueue() {
			head = null;
			tail = null;
			size = 0;
		}

		public int getSize() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		/**
		 * 向队尾添加元素
		 * 
		 * @param value
		 * @author zhangzhiwang
		 * @date 2022年1月20日 下午8:16:47
		 */
		public void offer(V value) {
			// 将值包装进节点中
			Node cur = new Node(value);
			// 头节点不动，每次向尾部添加元素
			if (tail == null) {// 判断head或者tail是否为空都可以
				head = cur;
				tail = cur;
			} else {
				tail.next = cur;
				tail = cur;
			}
			
			size++;
		}

		/**
		 * 从队头弹出元素
		 * 
		 * @return
		 * @author zhangzhiwang
		 * @date 2022年1月20日 下午8:17:24
		 */
		public V pop() {
			if(head == null) {
				return null;
			}
			
			V result = head.value;
			head = head.next;
			if(head == null) {// 说明本次弹出队头元素后队列就没有元素了，由于从队头弹出元素，tail始终没有动，当队列中没有元素后要把tail置为null，否则tail原来指向的队尾元素不能被JVM回收
				tail = null;
			}
			
			size--;
			return result;
		}

		/**
		 * 返回队尾元素但不弹出
		 * 
		 * @return
		 * @author zhangzhiwang
		 * @date 2022年1月20日 下午8:18:11
		 */
		public V peek() {
			if(head == null) {
				return null;
			}
			
			V result = head.value;
			
			return result;
		}
	}
}
