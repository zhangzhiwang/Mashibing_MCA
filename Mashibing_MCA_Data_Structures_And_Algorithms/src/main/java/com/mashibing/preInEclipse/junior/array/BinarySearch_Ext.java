package com.mashibing.preInEclipse.junior.array;

/**
 * 二分法查找扩展
 * 题目一：找到一个有序数组中>=num的最左位置
 * 题目二：找到一个有序数组中<=num的最右位置
 * 
 * @author zhangzhiwang
 * @date 2022年1月19日 下午5:06:16
 */
public class BinarySearch_Ext {
	public static void main(String[] args) {
		// 人工肉眼验证，简单测试
//		int[] arr = {1,2,3,3,3,7,9};
//		int index = find1(arr, 8);
//		int index = find2(arr, 8);
//		System.out.println(index);

		// 利用对数器验证算法
		int maxLength = 10;
		int maxValue = 100;
		int[] array = TestAlgorithmTool.randomArray(maxLength, maxValue);
		int radomTarget = (int) (Math.random() * (maxValue + 1));

		for (int i = 0; i < 100_0000; i++) {
//			if (find1(array, radomTarget) != searchValue1(array, radomTarget)) {
			if (find2(array, radomTarget) != searchValue2(array, radomTarget)) {
				System.out.println("出错了！");
				break;
			}
		}

		System.out.println("ok");
	}

	/**
	 * 题目一：找到一个有序数组中>=num的最左位置
	 * 
	 * @param arr
	 * @param num
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月19日 下午6:43:43
	 */
	public static int find1(int[] arr, int num) {
		// 1 2 3 3 3 7 9
		if (arr == null || arr.length == 0) {
			return -1;
		}

		int lIndex = 0;
		int rIndex = arr.length - 1;
		int tmpIndex = -1;
		while (lIndex <= rIndex) {
			int mIndex = (lIndex + rIndex) / 2;
			if (arr[mIndex] < num) {
				lIndex = mIndex + 1;
			} else {// arr[mIndex] >= num
				tmpIndex = mIndex;
				rIndex = mIndex - 1;
			}
		}

		return tmpIndex;
	}

	/**
	 * 题目二：找到一个有序数组中<=num的最右位置
	 * 
	 * @param arr
	 * @param num
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月19日 下午6:43:43
	 */
	public static int find2(int[] arr, int num) {
		// 1 2 3 3 3 7 9
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
		int lIndex = 0;
		int rIndex = arr.length - 1;
		int tmpIndex = -1;
		while (lIndex <= rIndex) {
			int mIndex = (lIndex + rIndex) / 2;
			if (arr[mIndex] <= num) {
				tmpIndex = mIndex;
				lIndex = mIndex + 1;
			} else {// arr[mIndex] > num
				rIndex = mIndex - 1;
			}
		}

		return tmpIndex;
	}

	public static int searchValue1(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return -1;
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= num) {
				return i;
			}
		}

		return -1;
	}
	
	public static int searchValue2(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] <= num) {
				return i;
			}
		}
		
		return -1;
	}
}
