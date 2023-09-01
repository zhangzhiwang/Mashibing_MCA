package com.mashibing.preInEclipse.senior.sort;

import java.util.HashMap;
import java.util.Map;


/** 
 * 插入排序
 * 假设前面的数组已经是有序的了，来了一个新数插入到数组的末尾，然后往前移动到它该有的位置。
 * 时间复杂度O(N^2)
 * 
 * @author zhangzhiwang
 * @date 2022年2月11日 上午10:58:29
 */
public class InsertionSort {
	public static void main(String[] args) {
		//		int[] arr = { 5, 4, 3, 2, 1, 1, 0 };
		//		insertSort(arr);
		//		SortCommon.printArr(arr);

		// 重新认识for循环
		// 当循环条件中的两个分号时间的条件为true时进行循环，为false时跳出循环
		//				for (int i = 0; true;i++) {// 死循环
		//					
		//				}
//		for (; true;) {// 死循环
//
//		}

		//		int num = 0;
		//		for (int i = 0; num != 5; i++) {
		//			System.out.println(num++);
		//		}
		
		Map map = new HashMap<>();

	}

	private static void insertSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}

		// 0 1 3 5
		for (int i = 0; i < arr.length; i++) {// [0,0]范围已经有序了，从1位置开始就可以
			for (int j = i + 1; j > 0 && j < arr.length && arr[j] < arr[j - 1]; j--) {// 注意在索引出现i+1时，要保证不会超过数组的长度，另一个方面如果出现索引i--时，要保证不能小于0
				swap(arr, j - 1, j);
			}
		}
	}

	private static void swap(int[] arr, int i, int j) {
		if (i == j) {
			return;
		}

		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}
}
