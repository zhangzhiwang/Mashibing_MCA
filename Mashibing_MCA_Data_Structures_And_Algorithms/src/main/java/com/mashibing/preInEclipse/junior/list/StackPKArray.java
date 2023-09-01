package com.mashibing.preInEclipse.junior.list;

import java.util.Stack;

/**
 * 栈 vs 数组
 * 
 * @author zhangzhiwang
 * @date 2022年2月4日 下午2:36:56
 */
public class StackPKArray {
	public static void main(String[] args) {
		// 栈是先进后出的结构，当明确知道总共元素有多少且不会变的时候，用数组实现先进后出的功能比栈效率要高
		// 比如：明确知道总共有100万的元素且总个数不会变，那么以下是使用Stack和数组来实现先进后出功能的效率对比：
		// 使用Stack
		Stack<Integer> stack = new Stack<>();
		int count = 100_0000;
		
	    long start = System.currentTimeMillis();
		for(int i = 0; i < count; i++) {
			stack.add(i);
		}
		
		while(!stack.isEmpty()) {
			int i = stack.pop();
		}
		long end = System.currentTimeMillis();
		System.out.println("stack循环" + count + "次共耗时：" + (end - start));// 1110
		
		// 使用数组
		int[] arr = new int[count];
		start = System.currentTimeMillis();
		for(int i = 0; i < count; i++) {
			arr[i] = i;
		}
		
		for(int i = count - 1; i <= 0; i--) {
			int j = arr[i];	
		}
		end = System.currentTimeMillis();
		System.out.println("数组循环" + count + "次共耗时：" + (end - start));// 7
	}
}
