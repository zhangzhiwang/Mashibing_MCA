package com.mashibing.preInEclipse.senior.stack;

import com.mashibing.preInEclipse.junior.array.sort.SortCommon;

/**
 * 用数组实现栈
 * 思路：
 * 维护一个head变量即可
 * 
 * @author zhangzhiwang
 * @date 2022年2月25日 下午7:38:09
 */
public class StackByArray {
	private int[] arr;
	private int head = -1;
	private int size;
	
	public StackByArray(int length) {
		arr = new int[length];
	}
	
	
	public void add(int num) {
		if (size == arr.length) {
			throw new RuntimeException("栈已满！");
		}
		
		arr[++head] = num;
		size++;
	}
	
	/**
	 * pop元素不需要在数组中删除，只要维护好head边界，那么外面只能访问到head的数据
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月25日 下午7:48:15
	 */
	public int pop() {
		if (size == 0) {
			throw new RuntimeException("栈已空！");
		}
		
		int result = arr[head--];
		size--;
		
		return result;
	}
	
	public void printTest() {
		SortCommon.printArr(arr);
	}
	
	public static void main(String[] args) {
		StackByArray stackByArray = new StackByArray(5);
		stackByArray.add(1);
		stackByArray.add(2);
		stackByArray.add(3);
		stackByArray.add(4);
		stackByArray.add(5);
		stackByArray.printTest();
		
		System.out.println(stackByArray.pop());
		stackByArray.add(6);
		stackByArray.printTest();
		
		System.out.println(stackByArray.pop());
		System.out.println(stackByArray.pop());
		stackByArray.add(7);
		stackByArray.printTest();
		
		System.out.println(stackByArray.pop());
	}
}
