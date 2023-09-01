package com.mashibing.preInEclipse.junior.array;

import com.mashibing.preInEclipse.junior.array.sort.BubbleSort;
import com.mashibing.preInEclipse.junior.array.sort.InsertSort;
import com.mashibing.preInEclipse.junior.array.sort.SelectionSort;
import com.mashibing.preInEclipse.junior.array.sort.SortCommon;

/**
 * 对数器——验证算法的正确性
 * 
 * @author zhangzhiwang
 * @date 2022年1月19日 上午11:06:59
 */
public class TestAlgorithmTool {
	public static void main(String[] args) {
		/*
		 * 验证冒泡排序、选择排序、插入排序算法的正确性。
		 * 思路：首先验证其中一个排序算法是否正确，是否将一个无序数组按照从小到大的顺序排好序了，如果不正确则直接返回，如果正确则比较三个排序算法的结果输出是否一致
		 */
		int[] arr = randomArray(10, 100);// 产生一个随机无序数组
		SortCommon.printArr(arr);// 打印原始数组

		// 将原始数组拷贝一份
		int[] arrCopy1 = copyArray(arr);
		int[] arrCopy2 = copyArray(arr);

		// 冒泡排序
		BubbleSort.bubbleSort(arr);

		// 验证冒泡排序算法的正确性
		if (!isSorted(arr)) {
			System.out.println("冒泡排序有误！排完序的数组为：");
			SortCommon.printArr(arr);
			return;
		}

		SelectionSort.selectnSort(arrCopy1);
		if(!isArrEquals(arr, arrCopy1)) {
			System.out.println("选择排序有误！排完序的数组为：");
			SortCommon.printArr(arrCopy1);
			return;
		}
		
		InsertSort.insertSort(arrCopy2);
		if(!isArrEquals(arr, arrCopy2)) {
			System.out.println("插入排序有误！排完序的数组为：");
			SortCommon.printArr(arrCopy2);
			return;
		}
	}

	/**
	 * 产生一个长度随机、元素随机（最小值为0）的数组
	 * 
	 * @param maxLength 数组长度的最大值
	 * @param maxValue  元素的最大值（最小值为0）
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月19日 上午11:21:41
	 */
	public static int[] randomArray(int maxLength, int maxValue) {
		int length = (int) (Math.random() * (maxLength + 1));
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}

		return arr;
	}

	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}

		int[] result = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i];
		}

		return result;
	}

	/**
	 * 判断一个数组是否排好序
	 * 
	 * @param arr
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月19日 上午11:20:23
	 */
	public static boolean isSorted(int[] arr) {
		if (!SortCommon.isArrNeedSort(arr)) {
			return true;
		}

		int larger = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				return false;
			}

			larger = arr[i + 1];
		}

		return true;
	}

	/**
	 * 判断两个数组的元素是否相等
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月19日 下午12:22:13
	 */
	public static boolean isArrEquals(int[] arr1, int[] arr2) {
		if (arr1 == null || arr2 == null) {
			return false;
		}

		if (arr1.length != arr2.length) {
			return false;
		}

		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}

		return true;
	}
}
