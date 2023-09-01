package com.mashibing.preInEclipse.senior.list;

import com.mashibing.preInEclipse.junior.array.sort.SortCommon;

/**
 * 用数组实现队列
 * 这里的数组是循环数组，队列是从队尾进从队头出，假设数组的长度是5，假设数组已经满了，现在要从头部也就是索引为0的元素弹出一个元素之后，如果再加入元素那么新元素要放到0的位置，那么0就是队尾，因为是循环数组。
 * 使用循环数组在弹出元素后不需要移动元素。
 * 
 * 思路：
 * 准备三个变量：head、tail和size，都是指数组的索引。这里用固定长度的数组来实现，当数组满之后不进行扩容而是报错。
 * size来控制能不能加入新元素和弹出元素，如果弹出元素head++，size--；如果加入元素，tail++，size++。
 * 
 * 
 * @author zhangzhiwang
 * @date 2022年2月25日 下午7:03:53
 */
public class QueueByArray {
	private int[] arr;
	private int head = -1;// 链表头元素在数组中的索引
	private int tail = -1;// 同上
	private int size;

	public QueueByArray(int length) {
		super();
		arr = new int[length];
	}

	public void add(int num) {// 队列是从尾部加入
		if (size == arr.length) {
			throw new RuntimeException("队列已满！");
		}

		if (tail + 1 == arr.length) {
			tail = -1;
		}
		arr[++tail] = num;
		size++;
	}

	/**
	 * pop元素不需要在数组中删除，只要维护好head和tail的边界，那么外面只能访问到[head,tail]的范围
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月25日 下午7:47:18
	 */
	public int pop() {
		if (size == 0) {
			throw new RuntimeException("队列已空！");
		}
		
		if (head + 1 == arr.length) {
			head = -1;
		}
		int result = arr[++head];
		size--;

		return result;
	}
	
	public int getSize() {
		return size;
	}
	
	public void printTest() {
		SortCommon.printArr(arr);
	}
	
	public static void main(String[] args) {
		QueueByArray queueByArray = new QueueByArray(5);
		queueByArray.add(1);
		queueByArray.add(2);
		queueByArray.add(3);
		queueByArray.add(4);
		queueByArray.add(5);
		queueByArray.printTest();
//		System.out.println(queueByArray.getSize());
		
		System.out.println(queueByArray.pop());
////		System.out.println(queueByArray.getSize());
//		queueByArray.printTest();
//		
		queueByArray.add(6);
		queueByArray.printTest();
		
		System.out.println(queueByArray.pop());
		queueByArray.add(7);
		queueByArray.printTest();
	}
}
