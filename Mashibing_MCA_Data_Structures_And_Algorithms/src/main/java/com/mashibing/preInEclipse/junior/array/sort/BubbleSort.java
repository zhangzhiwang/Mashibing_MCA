package com.mashibing.preInEclipse.junior.array.sort;

/**
 * 排序算法2——冒泡排序
 *
 * 形象描述：由于水里的冒的泡比较多所以比较次数比较多。
 * 
 * 思路：
 * 1、0到N-1的位置两两比较，如果前面的数大就和后面的数交换（0和1比较，1和2比较，……，N-2和N-1比较），这样走一遍N-1的位置就是全局最大的
 * 2、0到N-2的位置两两比较，如果前面的数大就和后面的数交换，这样走一遍N-2的位置就是全局最大的
 * 3、0到N-3的位置两两比较，如果前面的数大就和后面的数交换，这样走一遍N-3的位置就是全局最大的，以此类推
 *
 * 数组的算法题有一个规律：这个数组你想怎么推进？从左往右推进还是从右往左推进（假设左是开始右是结束）？要想从左往右推进就正序遍历，从右往左推进就倒序遍历，但要注意结束遍历的边界条件有没有"="。
 * 
 * @author zhangzhiwang
 * @date 2022年1月17日 下午12:01:17
 */
public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = { 1, 3, 5, 2, 7, 4, 8, 100, 9, 10, -888, 8 };// 准备一个无序数组
		SortCommon.printArr(arr);

		bubbleSort(arr);
	}

	public static void bubbleSort(int[] arr) {
		if(!SortCommon.isArrNeedSort(arr)) {
			return;
		}
		
		for (int i = arr.length - 1; i >= 0; i--) {// 第一次的范围是[0,N-1]，第二次的范围是[0,N-2]，第三次的范围是[0,N-3]，都是从0开始，所以后面是递减的，所以倒序遍历
			for (int second = 1; second <= i; second++) {// 因为是两两比较，那么second代表每组后面的元素索引
				if (arr[second - 1] > arr[second]) {
					SortCommon.changeValue(arr, second - 1, second);
				}
			}
		}

		SortCommon.printArr(arr);
	}
}
