package com.mashibing.preInEclipse.senior.stack;

import java.util.Stack;

/**
 * 使用栈实现队列（非双端队列）
 * 
 * 思路：
 * 准备两个栈A和B，A用来接收用户的添加行为，B用来接收用的弹出行为，从A里面弹出元素，按照弹出的顺序放入到B里面，再从B弹出的时候顺序就是FIFO的顺序了。
 * 注意：
 * 1、一旦要从A往B里面倒数据那么必须一次性倒完，直到A倒空为止。
 * 2、什么时候倒数据？必须是B为空的时候，当B不为空的时候A绝对不能往B倒数据。
 *
 * @author zhangzhiwang
 * @date 2022年2月25日 下午9:08:57
 */
public class QueueByStack {
	private Stack<Integer> pushStack = new Stack<>();
	private Stack<Integer> popStack = new Stack<>();
	
	public void add(int num) {
		pushStack.add(num);
	}
	
	public int pop() {
		movePushToPop();
		if (popStack.isEmpty()) {// 移动完之后仍然为空抛异常
			throw new RuntimeException("队列为空！");
		}
		
		return popStack.pop();
	}
	
	public int peek() {
		movePushToPop();
		if (popStack.isEmpty()) {// 移动完之后仍然为空抛异常
			throw new RuntimeException("队列为空！");
		}
		return popStack.peek();
	}
	
	private void movePushToPop() {
		if (!popStack.isEmpty()) {
			return;
		}
		
		while (!pushStack.isEmpty()) {
			popStack.add(pushStack.pop());
		}
	}
	
	public static void main(String[] args) {
		QueueByStack queueByStack = new QueueByStack();
		queueByStack.add(1);
		queueByStack.add(2);
		queueByStack.add(3);
		queueByStack.add(4);
		queueByStack.add(5);
		
//		System.out.println(queueByStack.peek());
		System.out.println(queueByStack.pop());
		System.out.println(queueByStack.pop());
//		System.out.println(queueByStack.pop());
//		System.out.println(queueByStack.pop());
//		System.out.println(queueByStack.pop());
		
		queueByStack.add(6);
//		System.out.println(queueByStack.peek());
		System.out.println("--------");
		System.out.println(queueByStack.pop());
		System.out.println(queueByStack.pop());
		System.out.println(queueByStack.pop());
		System.out.println(queueByStack.pop());
	}
}
