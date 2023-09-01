package com.mashibing.preInEclipse.senior.sort;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.mashibing.preInEclipse.junior.array.sort.SortCommon;
import com.mashibing.preInEclipse.senior.ArrayComparator;

/**
 * 冒泡排序
 * 思路：
 * 两两比较，大的放右边
 * 时间复杂度：O(N^2)
 *
 * @author zhangzhiwang
 * @date 2022年2月12日 上午10:19:26
 */
public class BubbleSort {
	public static void main(String[] args) {
//		int[] arr1 = { 1, 3, 5, 7, 9, 2, 4, 6, 8, 0 };
//		bubbleSort(arr1);
//
//		for (int i : arr1) {
//			System.out.print(i + "\t");
//		}
		
		// 对数器验证
//		int maxLength = 100;
//		int maxValue = 1000;
//		for(int i = 0; i < 100_0000; i++) {
//			int[] arr = ArrayComparator.generateRandomArray(maxLength, maxValue);
//			int[] arr2 = ArrayComparator.copyArray(arr);
//			int[] arr3 = ArrayComparator.copyArray(arr);
//			bubbleSort(arr2);
//		}
		
		HashMap<String,String> m = new HashMap<String, String>();
		for(String key : m.keySet()) {
			String count = m.get(m);
			
			
		}
		
	}

	private static void bubbleSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i; j++) {
				if (j < arr.length - 1 &&  arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
	}
}
