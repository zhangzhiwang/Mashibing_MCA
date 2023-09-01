package com.mashibing.preInEclipse.senior.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * java.util.PriorityQueue优先级队列，实质上是堆
 *
 * @author zhangzhiwang
 * @date 2022年2月15日 下午4:29:37
 */
public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();// PriorityQueue的泛型如果是基本数据类型那么默认是小根堆，复合类型必须得传入比较器或者该复合类型本身就是比较器，大根堆还是小根堆取决于比较器
		heap.add(7);
		heap.add(1);
		heap.add(3);
		heap.add(5);
		heap.add(1);
		//		System.out.println(heap.peek());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());

		System.out.println("---------------");
		// 如何把小根堆变成大根堆？重新定义一个比较器即可
		heap = new PriorityQueue<Integer>(new IntegerComparatorTest());// PriorityQueue默认是小根堆
		heap.add(7);
		heap.add(1);
		heap.add(3);
		heap.add(5);
		heap.add(1);
		//		System.out.println(heap.peek());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
	}

	static class IntegerComparatorTest implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;// 实现整数的倒序排序从而实现大根堆
		}
	}
}
