package com.mashibing.preInEclipse.senior.binarySearch;

/**
 * 二分查找
 * 题目：给定一个有序数组且元素不重复，返回该数组是否包含某个数字k
 * 
 * 思路：一次砍一半
 * 
 * 时间复杂度：O(logN)
 *
 * @author zhangzhiwang
 * @date 2022年2月21日 下午8:22:36
 */
public class BinarySearch {
	public static void main(String[] args) {
		int[] arr = {1,3,5,7};
		System.out.println(binarySearch(arr, 0, 4, 0));
	}

	private static boolean binarySearch(int[] arr, int l, int r, int k) {
		if (arr == null || arr.length == 0) {
			return false;
		}

		return f(arr, l, r, k);
	}

	private static boolean f(int[] arr, int l, int r, int k) {
		while (l <= r) {
			int m = l + ((r - l) >> 1);
			if (arr[m] == k) {
				return true;
			} else if (arr[m] > k) {
				r = m - 1;
				return f(arr, l, r, k);
			} else {// arr[m] < k
				l = m + 1;
				return f(arr, l, r, k);
			}
		}

		return false;
	}
}
