package com.mashibing.preInEclipse.junior.sort;

/**
 * 排序算法1——选择排序
 * 
 * 排序就是将一个无序数组按照从小到大或者从大到小（一般是从小到大）排好序，排序的方法有很多就衍生出了很多排序算法，但最终目的就是将数组排好序
 * 以后如果不加特殊说明，那么：变量N代表数组或者集合的长度，即元素个数；数组或者集合的排序按照从小到大的顺序排序。
 * 
 * 
 * 选择排序思路：
 * 1、先从0位置开始到N-1位置，找到一个最小值，将这个最小值和0位置的元素交换顺序，这样0位置的元素就是[0,N-1]范围内最小的
 * 2、从1位置开始到N-1位置，找到一个最小值，将这个最小值和1位置的元素交换顺序，这样1位置的元素就是[1,N-1]范围内最小的
 * 3、从2位置开始到N-1位置，找到一个最小值，将这个最小值和1位置的元素交换顺序，这样2位置的元素就是[2,N-1]范围内最小的，以此类推
 * 4、如何找到制定索引范围内的最小值？用一个变量保存最小值的索引，假设某范围的起始位置的元素最小并将起始位置保存到临时变量中，用for循环依次将后面的每一个值和最小索引位置的值作比较，
 * 谁小就将谁的索引保存在临时变量中，这样循环一遍之后就找到了最小值的索引。
 * 5、将最小值所在位置和范围起始位置的元素互换
 * 
 * @author zhangzhiwang
 * @date 2022年1月17日 上午10:57:17
 */
public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = { 1, 3, 5, 2, 7, 4, 8, 100, 9, 10, -888 };// 准备一个无序数组
		SortCommon.printArr(arr);
		
		// 边界检查
		if(!SortCommon.isArrNeedSort(arr)) {
			return;
		}
		
		for(int i = 0; i < arr.length; i++) {// 外层循环控制范围
			int minValueIndex = i;// 临时变量，保存最小值的索引，刚开始假设某范围起始位置的值是最小的
			for(int j = i+1; j < arr.length; j++) {// 内层循环找到该范围内的最小值及其索引
				minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
			}
			
			// 将最小值放到该范围的第一个位置
			SortCommon.changeValue(arr, i, minValueIndex);
		}
		
		SortCommon.printArr(arr);
	}
}
