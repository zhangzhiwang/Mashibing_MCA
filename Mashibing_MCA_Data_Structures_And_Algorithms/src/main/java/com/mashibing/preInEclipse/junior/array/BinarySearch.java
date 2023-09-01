package com.mashibing.preInEclipse.junior.array;

/**
 * 二分法查找
 * 思路：有限数组必须是有序的，无序数组中找到一个元素只能遍历了。
 * 用一个L和R来界定每次查找的index范围，根据L或者R位置上数据与待查找数据的大小比较来移动L或者R的位置，诶次移动缩减一半范围，直到找到目标数据或者L > R，当L > R时仍然没有找到数据时说明
 * 数组没有待查找元素。
 * 
 * @author zhangzhiwang
 * @date 2022年1月19日 下午5:06:16
 */
public class BinarySearch {
	public static void main(String[] args) {
		// 利用对数器验证算法
		int maxLength = 10;
		int maxValue = 100;
		int[] array = TestAlgorithmTool.randomArray(maxLength, maxValue);
		int radomTarget = (int)(Math.random() * (maxValue + 1));
		
		for(int i = 0; i < 100_0000; i++) {
			if(binarySearch(array, radomTarget) != searchValue(array, radomTarget)) {
				System.out.println("出错了！");
				break;
			}
		}
		
		System.out.println("ok");
	}
	
	/**
	 * 二分查找
	 * 
	 * 说明：这里是找到其中一个匹配的元素就返回
	 * 
	 * @param arr	有序数组
	 * @param num	待查找的元素
	 * @return 返回num的下标
	 * @author zhangzhiwang
	 * @date 2022年1月19日 下午5:22:03
	 */
	public static int binarySearch(int[] arr, int num) {
		// [1 2 4 8 16 32]
		if(arr == null || arr.length == 0) {
			return -1;
		}
		
		int lIndex = 0;// lIndex初始位置在数组最左端，即0的位置
		int rIndex = arr.length - 1;// rIndex初始位置在数组最右端，即末尾的位置，始终在[lIndex,rIndex]范围内进行二分查找，即lIndex和rIndex界定了每次二分查找的界限
		while(lIndex <= rIndex) {// 只要lIndex <= rIndex就可以在此范围内进行二分查找，直到找到为止，当lIndex > rIndex仍然没有找到则则说明数组中不存在被查找元素。
			int mIndex = (lIndex + rIndex) / 2;// 中间位置的索引
			if(arr[mIndex] == num) {
				return mIndex;
			} else if(arr[mIndex] < num) {// 中间位置的元素比目标元素小，说明[lIndex,mIndex]范围内的数据都比目标元素小，所以左边的都不看了，下一次从mIndex右边开始查找
				lIndex = mIndex + 1;// 下次查找的范围是[mIndex + 1,rIndex]，范围缩小了一半
			} else {// arr[mIndex] > num，说明待查找元素在mIndex的左边，后边的舍弃
				rIndex = mIndex - 1;
			}
		}
		
		return -1;// 当退出循环时说明lIndex已经大于了rIndex，仍然没有找到目标数据则返回-1
	}
	
	/**
	 * 用原始方法实现从一个数组中查找某一元素的索引
	 * 
	 * @param arr
	 * @param num
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月19日 下午5:47:33
	 */
	public static int searchValue(int[] arr, int num) {
		if(arr == null || arr.length == 0) {
			return -1;
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == num) {
				return i;
			}
		}
		
		return -1;
	}
}
