package com.mashibing.preInEclipse.junior.sort;

/**
 * 排序算法3——插入排序
 * 
 * 思路：
 * 1、让索引[0,0]范围内有序，然而数组只有一个元素天然是有序的
 * 2、让索引[0,1]范围内有序，可以看做将1位置的元素插入到已排好序的[0,0]数组的最末尾，然后让1元素与前面数组最末尾的元素进行比较，如果小的话就和其交换，反之不动；
 * 交换完后让新数据和原有序数组倒数第二个元素进行比较，小则交换，大则不动；交换完后和倒数第三个元素进行比较，以此类推，直到新元素不小于左侧的元素为止或者新元素挪到了数组的最左边。
 * 
 * @author zhangzhiwang
 * @date 2022年1月17日 下午5:15:21
 */
public class InsertSort {
	public static void main(String[] args) {
		int[] arr = { 1, 3, 5, 2, 7, 4, 8, 100, 9, 10, -888, 8 };// 准备一个无序数组
		SortCommon.printArr(arr);
		
		for(int newDataIndex = 1; newDataIndex < arr.length; newDataIndex++) {// newDataIndex是新插入数据的索引，默认新插入的数据放在已排好序数组的末尾，由于最开始已排好序数组的索引范围是[0,0]，所以新元素的第一个位置应该是1。
			// 外层循环从1开始，因为0位置天生已经排好序了，所以新插入一个元素是从位置1开始的    想的屏放都发过几个唯一因为回家看看渝欧就1123
			/**
			 * 内层for循环的含义：
			 * 1、preDataIndex：假如现在已排好序的数组是[1,3,5]，然后在5后面插入一个新元素2，外层循环的newDataIndex就是2所在的位置，那么5所在的位置就是newDataIndex-1，
			 * 即preDataIndex始终是newDataIndex的前一个位置，无论newDataIndex移动到哪里。
			 * 2、退出循环的条件是preDataIndex<0，也就是newDataIndex挪到了数组的第一个位置，或者newDataIndex不比前一个位置小了
			 * 3、如果newDataIndex比妻哪一个位置小那么会进行位置交换，相当于newDataIndex向前移动了一个位置，那么preDataIndex也要往前移动一个位置以便下次循环比较，所以每次循环之后preDataIndex要减1。
			 */
			for(int preDataIndex = newDataIndex - 1; preDataIndex >= 0 && arr[preDataIndex] > arr[preDataIndex + 1]; preDataIndex--) {
				SortCommon.changeValue(arr, preDataIndex, preDataIndex + 1);
			}
		}
		SortCommon.printArr(arr);
	}
}