package com.mashibing.preInEclipse.senior.sort;

import java.util.Arrays;

import com.mashibing.preInEclipse.junior.array.sort.SortCommon;

/**
 * 桶排序——计数排序
 * 题目：给定一个无序数组，每个元素的范围在[m,n]之间且m>=0且n不会太大，对该数组进行排序。
 * 思路：
 * 1、遍历一遍原数组arr，找出最大值maxValue
 * 2、准备一个数组tmpArr，长度是maxValue + 1，这里的重点是tmpArr的索引值是arr的元素值，而tmpArr的元素值是arr某元素出现的次数
 * 3、遍历tmpArr，根据每个元素出现的次数将所有元素平铺开来并逐个赋给arr
 *
 * @author zhangzhiwang
 * @date 2022年2月18日 下午7:41:15
 */
public class CountSort {
	public static void main(String[] args) {
//		int[] arr = {1,2,2,1,3,3,3,3,1,2};
//		countSort(arr);
//		SortCommon.printArr(arr);
		
		// 对数器
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 150;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			countSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				SortCommon.printArr(arr1);
				SortCommon.printArr(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		SortCommon.printArr(arr);
		countSort(arr);
		SortCommon.printArr(arr);
	}
	
	private static void countSort(int[] arr) {
		if(arr == null || arr.length <= 1) {
			return;
		}
		
		// 1、遍历原数组找出最大值
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		
		// 2、准备辅助数组help，它的索引值是arr的元素值，它的元素值是arr某元素出现的次数，所以原数组arr的最大值max将作为help数组的最大索引，所以help的长度应该是max+1
		int[] help = new int[max + 1];// help数组的每一个位置都代表一个桶，桶里面放某一个数在原数组中出现的次数
		for (int i = 0; i < arr.length; i++) {// 所有数入桶
			help[arr[i]]++;
		}
		
		// 3、遍历tmpArr，根据每个元素出现的次数将所有元素平铺开来并逐个赋给arr
		int j = 0;
		for (int i = 0; i < help.length; i++) {// 所有数出桶
			while (help[i]-- > 0) {
				arr[j++] = i;
//				j++;
//				help[i]--;
			}
		}
		// 注意：出桶的时候要给arr逐个复制，不能新生成一个和arr等长的数组最后赋值给arr，这样的话原数组相当于没有排序，因为参数arr传进来的时候会复制一个引用。
	}
	
	// 对数器所用方法
	// for test
		public static void comparator(int[] arr) {
			Arrays.sort(arr);
		}

		// for test
		public static int[] generateRandomArray(int maxSize, int maxValue) {
			int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = (int) ((maxValue + 1) * Math.random());
			}
			return arr;
		}

		// for test
		public static int[] copyArray(int[] arr) {
			if (arr == null) {
				return null;
			}
			int[] res = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				res[i] = arr[i];
			}
			return res;
		}

		// for test
		public static boolean isEqual(int[] arr1, int[] arr2) {
			if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
				return false;
			}
			if (arr1 == null && arr2 == null) {
				return true;
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
