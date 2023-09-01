package com.mashibing.preInEclipse.senior.sort;

/**
 * 堆排序
 * 这里用的堆是大根堆
 * 时间复杂度：O(N*logN)
 * 
 * 思路：
 * 1、遍历一遍数组将所有元素都heapInsert到堆里，结束后数组的头元素是整体的最大值
 * 2、将堆的第一个元素和最后一个元素互换，这样整体的最大值被移动到了右边，heapSize--，将最大值排除到堆之外
 * 3、调用heapify，将最大值移动到对的头部，然后将头部和尾部元素互换，heapSize--，循环第三步
 *
 * @author zhangzhiwang
 * @date 2022年2月15日 下午5:29:18
 */
public class HeapSort {
	private static int heapSize;
	
	private static void heapSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}
		
		// step1：构建大根堆
		for(int i = 0; i < arr.length; i++) {// 遍历数组将所有元素添加到大根堆里面，O(N)
			heapInsert(arr, i);// O(logN)
		}
		// 将所有元素放入到堆中之后，heapSize就是arr.length了
		heapSize = arr.length;
		
		// step2：将数组的头元素和尾元素互换，heapSize--。将最大值移到数组末尾，将该最大值排除到heap之外
		while(heapSize > 0) {// heapSize从N减到0，O(N)
			swap(arr, 0, --heapSize);
			heapify(arr, 0);// O(logN)
		}
	}
	
	/**
	 * heapInsert的作用不是向堆中添加节点，而是调整堆中指定节点的位置，向堆中添加节点的工作是由调用heapInsert方法的上游方法来完成的
	 * heapInsert是将指定节点往上调
	 * 
	 * @param arr
	 * @param index
	 * @author zhangzhiwang
	 * @date 2022年2月15日 下午6:28:08
	 */
	private static void heapInsert(int[] arr, int index) {
		if (arr == null) {
			return;
		}

		while (arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	/**
	 * heapify是将指定节点往下调
	 * 
	 * @param arr
	 * @param index
	 * @author zhangzhiwang
	 * @date 2022年2月15日 下午6:29:44
	 */
	private static void heapify(int[] arr, int index) {
		if (arr == null) {
			return;
		}

		int leftIndex = 2 * index + 1;
		while (leftIndex < heapSize) {
			int rightIndex = leftIndex + 1;
			int largestIndex = rightIndex < heapSize && arr[leftIndex] < arr[rightIndex] ? rightIndex : leftIndex;
			largestIndex = arr[largestIndex] > arr[index] ? largestIndex : index;

			if (largestIndex == index) {
				return;
			}

			swap(arr, largestIndex, index);
			index = largestIndex;
			leftIndex = 2 * index + 1;
		}
	}

	/**
	 * 元素互换位置
	 * 
	 * @param arr
	 * @param i 索引i
	 * @param j 索引j
	 * @author zhangzhiwang
	 * @date 2022年2月15日 下午6:16:56
	 */
	private static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}
	
	public static void main(String[] args) {
		int[] arr = {1,3,5,7,9,2,4,6,8,0};
		heapSort(arr);
		for(int i : arr) {
			System.out.print(i + "\t");
		}
	}
}
