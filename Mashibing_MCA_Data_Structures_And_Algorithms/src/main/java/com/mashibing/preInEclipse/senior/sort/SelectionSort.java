package com.mashibing.preInEclipse.senior.sort;

import com.mashibing.preInEclipse.junior.array.sort.SortCommon;

/**
 * 选择排序
 * 思路：
 * 在数组范围内选择一个最小的值放在数组最左边，然后从第二个位置开始重复上面的过程，直到数组最后一个元素为止。
 * 时间复杂度O(N^2)
 * 
 * @author zhangzhiwang
 * @date 2022年2月11日 下午2:51:04
 */
public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = {1,3,5,7,9,2,4,6,8,0};
		selectSort(arr);
		
//		swap(arr, 0, 1);
		SortCommon.printArr(arr);
	}
	
	private static void selectSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;// 先默认数组最左的元素最小
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			
			swap(arr, i, minIndex);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		if (i == j) {// 注意：位置i和j一定不能指向同一块内存区域，否则都会刷成0
			return;
		}
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}
}
