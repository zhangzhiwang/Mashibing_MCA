package com.mashibing.preInEclipse.senior;

/**
 * 数组对数器
 *
 * @author zhangzhiwang
 * @date 2022年2月12日 上午10:59:20
 */
public class ArrayComparator {
	/**
	 * 产生一个随机int数组
	 * 
	 * @param maxLength 数组最大长度
	 * @param maxValue 数组元素最大值
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月12日 上午11:01:37
	 */
	public static int[] generateRandomArray(int maxLength, int maxValue) {
		if (maxLength <= 0) {
			return new int[0];
		}

		int length = (int) (Math.random() * (maxLength + 1));
		int[] result = new int[length];
		for (int i = 0; i < length; i++) {
			result[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));// 两次随机数相减结果可正可负可零
		}

		return result;
	}

	public static int[] copyArray(int[] src) {
		if (src == null) {
			return src;
		}

		int[] des = new int[src.length];
		for (int i = 0; i < src.length; i++) {
			des[i] = src[i];
		}

		return des;
	}

	public static boolean compareArray(int[] src, int[] des) {
		if (src == null && des == null) {
			return true;
		}

		if ((src == null && des != null) || (src != null && des == null)) {
			return false;
		}

		if (src.length != des.length) {
			return false;
		}

		for (int i = 0; i < src.length; i++) {
			if (src[i] != des[i]) {
				return false;
			}
		}

		return true;
	}
}
