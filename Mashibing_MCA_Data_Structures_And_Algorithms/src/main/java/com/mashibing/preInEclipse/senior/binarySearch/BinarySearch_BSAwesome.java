package com.mashibing.preInEclipse.senior.binarySearch;

/**
 * 二分查找——局部最小值
 * 二分查找一般都要求给定的数组是有序的，像本题特殊给定的数组是无序的。
 * 题目，给定一个无序数组，该数组任意相邻两个元素不相等，找到任意局部最小值。
 *
 * @author zhangzhiwang
 * @date 2022年2月21日 下午10:02:20
 */
public class BinarySearch_BSAwesome {
	public static void main(String[] args) {
		int[] arr = {3,2,9,7,6,2,3};
		System.out.println(f(arr));
	}
	
	/**
	 * 
	 * 
	 * @param arr
	 * @return 返回某一个局部最小值的索引
	 * @author zhangzhiwang
	 * @date 2022年2月25日 下午6:34:26
	 */
	private static int f(int[] arr) {
		if(arr == null || arr.length == 0) {
			return -1;
		}
		
		if (arr.length == 1) {
			return 0;
		}
		
		if (arr[0] < arr[1]) {
			return 0;
		}
		int N = arr.length;
		if (arr[N - 1] < arr[N - 2]) {
			return N - 1;
		}
		
		int l = 1;
		int r = N - 2;
		int m = 0;
		while(true) {
			m = l + ((r - l) >> 1);
			
			if (arr[m] > arr[m - 1]) {
				r = m - 1;
			} else if (arr[m] > arr[m + 1]) {
				l = m + 1;
			} else {//  arr[m - 1] < arr[m] < arr[m + 1]
				return m;
			}
		}
	}
}
