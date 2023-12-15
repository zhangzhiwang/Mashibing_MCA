package com.mashibing.preInEclipse.senior;

/**
 * Master公式
 * 
 * 分析：
 * 1、大问题拆分成若干个小问题，每个小问题的数据量规模都是一样的，都是大问题数据量的1/b。
 * 2、满足第一点，就可以根据这个公式来找出a、b、d的值，从而带入Master公式计算整个时间复杂度
 * 			T(N) = a * (n / b) + O(N^d)，其中a、b、d为常数，N为整体数据量，O(N^d)是整个递归代码除了递归部分之外剩余代码的时间复杂度
 * 3、Master公式：
 * （1）若logba > d，则O(N^logba)
 * （2）若logba < d，则O(N^d)
 * （3）若logba = d，则O(N^logN)
 * 其中logba是以b为底a的对数。
 *
 * @author zhangzhiwang
 * @date 2022年3月3日 下午6:42:09
 */
public class Master {
	public static void main(String[] args) {
		// 简单的递归：找到一个数组的最大值，目的是计算Master公式
		int[] arr = {2,4,1,3,9};
		int result = findMax(arr, 0, 4);
		System.out.println(result);
		
		// 按照Master公式，整个递归的时间复杂度为：a = 2，b = 2，d = 0，logba = 1 > d，所以是O(N^1)，即O(N)，相当于循环遍历一遍找到最大值。
	}

	private static int findMax(int[] arr, int l, int r) {
		if (arr == null || arr.length == 0) {
			throw new RuntimeException("数组为空");
		}

		return f(arr, l, r);
	}

	private static int f(int[] arr, int l, int r) {
		if (l == r) {
			return arr[l];
		}

		int m = l + ((r - l) >> 1);
		int leftMax = f(arr, l, m);
		int rightMax = f(arr, m + 1, r);
		return Math.max(leftMax, rightMax);
	}
}
