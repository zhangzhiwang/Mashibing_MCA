package com.mashibing.preInEclipse.junior.list;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * jdk自带的优先级队列
 * 
 * @author zhangzhiwang
 * @date 2022年1月25日 下午7:23:17
 */
public class PriorityQueueTest {
	public static void main(String[] args) {
		/**
		 * 优先级队列，默认按照各元素的大小从小到大排序
		 */
//		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
//		heap.add(1);// 无序add但到了PriorityQueue里面会自动排好序
//		heap.add(3);
//		heap.add(5);
//		heap.add(7);
//		heap.add(9);
//		heap.add(2);
//		heap.add(4);
//		heap.add(6);
//		heap.add(8);
//		System.out.println("size = " + heap.size());
//		System.out.println(heap.peek());// 查询第一个元素（默认是最小的）但并不弹出
//		System.out.println("size = " + heap.size());
//		Integer poll = heap.poll();// 弹出第一个元素（默认是最小的）
//		System.out.println(poll);
//		System.out.println("size = " + heap.size());
//		poll = heap.poll();// 弹出第一个元素（默认是最小的）
//		System.out.println(poll);
//		System.out.println("size = " + heap.size());

		/**
		 * 优先级队列也可以按照自定义的逻辑排序，需要自己实现Comparator接口并传入到PriorityQueue的构造器中
		 */
//		PriorityQueue<Integer> heap2 = new PriorityQueue<Integer>(new MyComparator());
//		heap2.add(1);
//		heap2.add(3);
//		heap2.add(5);
//		heap2.add(7);
//		heap2.add(9);
//		heap2.add(2);
//		heap2.add(4);
//		heap2.add(6);
//		heap2.add(8);
//		while (!heap2.isEmpty()) {
//			System.out.println(heap2.poll());
//		}

		PriorityQueue<User> heap3 = new PriorityQueue<>(new UserIdComparator());
		User user1 = new User(1, "zhangsan", 10);
		User user2 = new User(2, "lisi", 20);
		User user3 = new User(3, "wangwu", 30);
		heap3.add(user1);
		heap3.add(user2);
		heap3.add(user3);

		while (!heap3.isEmpty()) {
			System.out.println(heap3.poll());
		}
	}

	static class MyComparator implements Comparator<Integer> {
		/**
		 * 无论compare方法入参是两个什么对象，只要是返回负数第一个参数对象排在第二个参数对象前面，如果返回正数则第二个参数对象在第一个参数对象前面，相等谁前谁后无所谓。
		 * 这里实现的目标是按照倒叙排序
		 */
		@Override
		public int compare(Integer o1, Integer o2) {
			if (o1 > o2) {
				return -1;
			} else if (o1 < o2) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	static class User {
		private int id;
		private String name;
		private int age;

		public User(int id, String name, int age) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
		}
	}

	static class UserAgeComparator implements Comparator<User> {

		/**
		 * 按照年龄降序排序
		 */
		@Override
		public int compare(User o1, User o2) {
			if (o1.age < o2.age) {
				return 1;
			} else if (o1.age > o2.age) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	static class UserIdComparator implements Comparator<User> {
		
		/**
		 * 按照年龄降序排序
		 */
		@Override
		public int compare(User o1, User o2) {
			if (o1.id < o2.id) {
				return -1;
			} else if (o1.id > o2.id) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
