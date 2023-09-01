package com.mashibing.preInEclipse.senior.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列（非双端队列）实现栈
 * 
 * 思路：
 * 使用两个队列A和B，A队列接收用户的添加操作，当用户pop时，将A队列的元素都添加到B中但是最后剩一个，
 * 将整的这个元素返回给用户，然后交换A和B的位置，这样B就变成了A，每次都已A为主。
 *
 * @author zhangzhiwang
 * @date 2022年2月25日 下午9:08:57
 */
public class StackByQueue {
	private Queue<Integer> data = new LinkedList<>();
	private Queue<Integer> help = new LinkedList<>();
	
	public void add(int num) {
		data.add(num);
	}
	
	public int pop() {
		if(data.isEmpty()) {
			throw new RuntimeException("栈已空！");
		}
		
		while(data.size() > 1) {
			help.add(data.poll());
		}
		
		Integer result = data.poll();
		Queue<Integer> tmp = data;// 交换data和help的位置，每次只以data为主
		data = help;
		help = tmp;
		
		return result;
	}
	
	public int peek() {
		if(data.isEmpty()) {
			throw new RuntimeException("栈已空！");
		}
		
		while(data.size() > 1) {
			help.add(data.poll());
		}
		
		Integer result = data.peek();
		help.add(data.poll());
		Queue<Integer> tmp = data;// 交换data和help的位置，每次只以data为主
		data = help;
		help = tmp;
		
		return result;
	}
	
	public static void main(String[] args) {
		StackByQueue stackByQueue = new StackByQueue();
		stackByQueue.add(1);
		stackByQueue.add(2);
		stackByQueue.add(3);
		stackByQueue.add(4);
		stackByQueue.add(5);
		
//		System.out.println(stackByQueue.peek());
		
		System.out.println(stackByQueue.pop());
		System.out.println(stackByQueue.pop());
//		System.out.println(stackByQueue.pop());
//		System.out.println(stackByQueue.pop());
//		System.out.println(stackByQueue.pop());
		
		stackByQueue.add(6);
//		System.out.println(stackByQueue.peek());
		System.out.println("-------");
		System.out.println(stackByQueue.pop());
		System.out.println(stackByQueue.pop());
		System.out.println(stackByQueue.pop());
		System.out.println(stackByQueue.pop());
	}
}
