package com.mashibing.preInEclipse.senior.binarySearch;

/**
 * 二分查找——查找>=k的最左位置
 * 给定一个有序数组，查找>=某个数的最左位置
 *
 * 思路：一次砍一半
 * 
 * 时间复杂度：O(logN)
 *
 * @author zhangzhiwang
 * @date 2022年2月21日 下午9:36:27
 */
public class BinarySearch_FindMoreThanKLeftmost {
	public static void main(String[] args) {
		int[] arr = { 1, 3, 3, 3, 5, 7, 9, 11, 13, 13 };
		System.out.println(findMoreThanKLeftmost(arr, 0, 9, 13));
	}

	private static int findMoreThanKLeftmost(int[] arr, int l, int r, int k) {
		if (arr == null && arr.length == 0) {
			return -1;
		}

		return f(arr, l, r, k, -1);
	}

	private static int f(int[] arr, int l, int r, int k, int tmpIndex) {// tmpIndex用于保存>=k的最左位置的索引
		while (l <= r) {
			int m = l + ((r - l) >> 1);

			if (arr[m] >= k) {
				r = m - 1;
				tmpIndex = m;
				return f(arr, l, r, k, tmpIndex);
			} else {// arr[m] < k
				l = m + 1;
				return f(arr, l, r, k, tmpIndex);
			}
		}

		return tmpIndex;
	}
}
