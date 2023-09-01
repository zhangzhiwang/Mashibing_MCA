package com.mashibing.preInEclipse.junior.array;

import com.mashibing.preInEclipse.junior.array.sort.SortCommon;

/**
 * 局部最小值
 * 题目：给定一个全局无序但相邻元素不相等的数组，找到任意局部最小值。
 * 解释：局部最小不是全局最小，局部最小是指相邻元素的最小值，数组中局部最小值可能不是1个。
 * 对于数组首位的元素：如果一个元素小于第二个元素，而无论第三个元素是否比第一个元素小，第一个元素就是[0,1]这个局部的最小值；
 * 同理，如果最后一个元素小于倒数第二个元素，而无论倒数第三个元素的值是否比最后一个元素还小，最后一个元素就是在[N-2,N-1]局部的最小值；
 * 除了首位元素之外的任意一个位置i，局部最小值必须比左边的元素(i-1)和右边的元素(i+1)都要小才可以，即[i] < [i-1] %% [i] <
 * [i+1]，那么位置i就是[i-1,i+1]这个局部的最小值。
 * 
 * 思路：
 * 1、先判断首尾，如果首尾满足局部最小则直接返回
 * 2、要明确一点：局部最小值给定的数组一定是任意相邻的两个元素都不相等的，既然首尾元素都不是局部最小那么可以说明：0位置的元素一定比1位置的元素大，最后一个元素一定比倒数第二个元素大。
 * 那么就呈现出了[0,1]是下降趋势，[N-2,N-1]是上升趋势，那么在这中间可定存在局部最小。
 * 3、既然(0,N-1)范围内存在局部最小，那么就用二分法来查找：先找到中间的位置m，如果m比它相邻的m-1和m+1的位置都小，那么m就是局部最小值；否则找到任意比它小的相邻位置（构造上扬趋势），将另一边砍掉，这样就砍掉了一半。
 * 4、以此类推，重复第三步
 * 
 * @author zhangzhiwang
 * @date 2022年1月20日 上午10:56:48
 */
public class LocalLeastValue {
	public static void main(String[] args) {
		// 人工验证
//		int[] arr = { 3, 2, 7, 6, 5, 4, 3, 9};
//		int index = findLocalLeastValue(arr);
//		System.out.println(index);
		
		// 对数器验证
		int[] arr = TestAlgorithmTool.randomArray(100, 1000);
		for(int i = 0; i < 100_0000; i++) {
			int index = findLocalLeastValue(arr);
			boolean result = check(arr, index);
			if(!result) {
				System.out.println("出错了！");
				SortCommon.printArr(arr);
				System.out.println("index = " + index);
				break;
			}
		}
		
		System.out.println("ok");
	}

	/**
	 * 查找数组的任意局部最小值
	 * 
	 * @param arr
	 * @return 局部最小值索引
	 * @author zhangzhiwang
	 * @date 2022年1月20日 上午11:24:49
	 */
	public static int findLocalLeastValue(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int N = arr.length;
		if (N == 1) {
			return 0;
		}

		// 判断首尾是否为局部最小
		if (arr[0] < arr[1]) {
			return 0;
		}
		if (arr[N - 1] < arr[N - 2]) {
			return N - 1;
		}

		// 判断中间位置
		int l = 0;
		int r = N - 1;
		while (l < r - 1) {// 注意这里的循环条件是l < r - 1，不是l < r，以免出现l=m=0的时候m-1越界
			// 3 2 7 6 5 4 -1 4
			int m = (l + r) / 2;
			if (arr[m] < arr[m - 1] && arr[m] < arr[m + 1]) {
				return m;
			} else if (arr[m - 1] < arr[m]) {
				r = m - 1;
			} else {// arr[m + 1] < arr[m]
				l = m + 1;
			}
		}

		return arr[l] < arr[r] ? l : r;
	}

	public static boolean check(int[] arr, int index) {
		if (arr == null || arr.length == 0) {
			return index == -1;
		}

		if (arr.length == 1) {
			return index == 0;
		}

		if (arr[0] < arr[1]) {
			return index == 0;
		}

		int N = arr.length;
		if (arr[N - 1] < arr[N - 2]) {
			return index == N - 1;
		}

		// 前面已经吧数组为空、一个元素和两个元素的情况验证过了，如果走到这里说明数组有三个及以上元素。
		if (arr[index] < arr[index - 1] && arr[index] < arr[index + 1]) {
			return true;
		}

		return false;
	}
}
