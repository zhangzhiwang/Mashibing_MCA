package com.mashibing.preInEclipse.junior.sort;

/**
 * 排序的公共方法
 * 
 * @author zhangzhiwang
 * @date 2022年1月17日 上午11:30:26
 */
public class SortCommon {
	/**
	 * 判断数组是否需要排序
	 * 
	 * @param arr
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月17日 上午11:33:56
	 */
	public static boolean isArrNeedSort(int[] arr) {
		return arr != null && arr.length >= 2;// null	[]	[1]都无需排序
	} 
	
	/**
	 * 打印数组
	 * 
	 * @param arr
	 * @author zhangzhiwang
	 * @date 2022年1月17日 上午11:34:09
	 */
	public static void printArr(int[] arr) {
		if(arr == null) {
			return;
		}
		
		StringBuilder stringBuilder = new StringBuilder("[  ");
		for(int i = 0; i < arr.length; i++) {
			stringBuilder.append(arr[i]);
			if(i != arr.length - 1) {
				stringBuilder.append(",  ");
			}
		}
		stringBuilder.append("  ]");
		System.out.println(stringBuilder.toString());
	}
	
	/**
	 * 交换索引i和索引j的值
	 * 
	 * @param arr
	 * @param i 索引i
	 * @param j 索引j
	 * @author zhangzhiwang
	 * @date 2022年1月17日 下午12:15:54
	 */
	public static void changeValue(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
