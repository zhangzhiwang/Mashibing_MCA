package com.mashibing.preInEclipse.senior.stack;

import java.util.Stack;

/**
 * 面试题——实现一个特殊的栈，在基本功能的基础上，再实现查看栈中最小元素的功能。要求：
 * 1、pop、push、getMin操作的时间复杂度都是 O(1)。 
 * 2、设计的栈类型可以使用现成的栈结构。
 * 
 * 思路：
 * 设计两个栈，一个栈就是存数据的普通栈，另一个栈存最小值。
 * 设存数据的栈为A，存存最小值的栈为B，A中每加入一个元素，B中必须同步加入一个元素，所以B的大小始终等于A的大小。
 * B中加入元素的原则:
 * 1、如果A加入第一个元素，那么将这个元素同样放入B
 * 2、如果A加入的第二个元素比第一个元素小，那么将第二个元素同步放入B；如果A加入的第二个元素比第一个元素大，那么将B的第一个元素作为第二个元素再放入一遍
 * 3、弹出B栈顶的元素就是整个栈的最小值。
 * 准备第二个栈的目的就是题目要求的getMin是总能返回最小值，即每弹出一个元素之后都能返回剩下元素的最小值
 *
 * @author zhangzhiwang
 * @date 2022年2月25日 下午8:00:00
 */
public class StackGetMin {
	private Stack<Integer> dataStack = new Stack<>();// 注意dataStack和minStack要保持同步大小
	private Stack<Integer> minStack = new Stack<>();
	
	public void add(int num) {
		if (dataStack.isEmpty()) {// dataStack为空，minStack一定也为空
			minStack.push(num);
		} else if (num < minStack.peek()) {
			minStack.push(num);
		} else {// num >= minStack.peek()
			minStack.push(minStack.peek());
		}
		
		dataStack.push(num);// 这句话一定写在if判断的后面，不能写在前面，因为如果写在最前面，那么第一个if永远进不去，因为添加第一个元素时dataStack已经不是空了
	}
	
	public int pop() {
		if (dataStack.isEmpty()) {
			throw new RuntimeException("栈已空！");
		}
		
		minStack.pop();
		return dataStack.pop();
	}
	
	public int getMin() {
		if (minStack.isEmpty()) {
			throw new RuntimeException("栈已空！");
		}
		return minStack.peek();
	}
	
	public static void main(String[] args) {
		StackGetMin stackGetMin = new StackGetMin();
//		System.out.println(stackGetMin.getMin());
		
		stackGetMin.add(1);
		System.out.println(stackGetMin.getMin());
		stackGetMin.add(2);
		System.out.println(stackGetMin.getMin());
		stackGetMin.add(0);
		System.out.println(stackGetMin.getMin());
		stackGetMin.pop();
		System.out.println(stackGetMin.getMin());
	}
}
