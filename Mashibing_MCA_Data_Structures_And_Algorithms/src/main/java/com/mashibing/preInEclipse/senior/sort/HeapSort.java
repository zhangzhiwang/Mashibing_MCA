package com.mashibing.preInEclipse.senior.sort;

/**
 * 堆排序
 * 这里用的堆是大根堆
 * 时间复杂度：O(N*logN)
 * 
 * 思路：
 * 1、遍历一遍数组将所有元素都heapInsert到堆里，结束后数组的头元素是整体的最大值
 * 2、将堆的第一个元素和最后一个元素互换，这样整体的最大值被移动到了右边，heapSize--，将最大值排除到堆之外
 * 3、调用heapify，将最大值移动到堆的头部，然后将头部和尾部元素互换，heapSize--，循环第三步
 *
 * @author zhangzhiwang
 * @date 2022年2月15日 下午5:29:18
 */
public class HeapSort {
	private static int heapSize;// 注意：heapSize可以看做是下一个被插入元素的位置，是堆最后一个元素的下一个位置
	
	private static void heapSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}

		heapSize = arr.length;

		// step1：建堆，构建大根堆
		/*
		 建堆方式1：使用heapInsert从上往下建堆，整体时间复杂度是O(N * logN)
		 */
		for(int i = 0; i < heapSize; i++) {// 遍历数组将所有元素添加到大根堆里面，O(N)
			heapInsert(arr, i);// O(logN)
		}

		/*
		建堆方式2：使用heapify从下往上建堆，整体时间复杂度是O(N)
		注意：虽然外层循环的时间复杂度是O(N)，循环内时间复杂度是O(logN)，但这种从下往上建堆的方式整体时间复杂度不是O(N * logN)，而是最终收敛于O(N)。
		为什么会出现这种差异？
		1、从数学上解释
		根节点是第一层，叶子节点是最后一层，那么从最后一层往上看：从数量级上来说最后一层有N/2个节点（N为节点总数），倒数第二层大概有N/4个节点，
		倒数第三层大概有N/8个节点，倒数第四层大概有N/16个节点，以此类推。heapify的话，最后一层的节点都不需要往下调，所以每个节点只访问1次，所以最后一层的节点一共
		需要访问(N/2 * 1)次；倒数第二层的每个节点除了自己访问自己一次，如果向下调整的话最多调整一次，所以每个节点最多访问2次，所以倒数第二层的所有节点最多总共访问(N/4 * 2)次；
		同理，倒数第三层的节点最多总共访问(N/8 * 3)次，即自己访问自己一次，最多调整两次，倒数第四层的节点最多总共访问(N/16 * 4)次，倒数第五层的节点最多总共访问(N/32 * 5)次。
		所以整体来看时间复杂度是：T(N) = N/2 * 1 + N/4 * 2 + N/8 * 3 + N/16 * 4 + N/32 * 5 + ...，等号右边是等比数列的求和公式，所以是O(N)，即T(N) = O(N)。
		2、从原理上解释
		本质上是多数节点访问的次数多还是少量节点访问的次数多的问题。先说heapInsert，heapInsert的过程是每加入一个新节点都是先把它放到最后一层，然后逐层往上窜，
		如果新加入的这个节点是最大的话（假设是大根堆），那么它要从最底层最层向上蹿，要经历过所有层之后才能到达根节点，也就是说最底层的每一个节点最多都要经过所有层
		才能到达根节点，层数越往上移动的次数越少，同时层数越往上节点数量越少，也就是说大多数节点移动的次数多，少数节点移动的次数少。而heapify正好相反，由于是从下往上建堆，
		最底层的节点只访问一次，越往上的节点访问的次数越多，同时越往上层的节点越少，也就是说大多数节点移动的次数少而少量节点移动的次数多，所以就造成了从上往下建堆的时间复杂度
		比从下往上建堆的时间复杂度大。
		具体解释可以看课程：体系班课时54-55
		使用heapify建堆的代码在com.mashibing.array.sort.HeapSort。
		但是这里要说明的是：不管是以哪种方式建堆，整个堆排序算法的时间复杂度都是O(N * logN)，因为堆排序的过程一共分为两部分，上面说的只是第一部分的时间复杂度优化，
		但是第二部分调整堆的时间复杂度是O(N * logN)，这个是优化不了的。
		 */
//		for(int i = arr.length - 1; i >= 0; i--) {// O(N)
//			heapify(arr, i);// O(logN)
//		}

		// step2：调整堆，将数组的头元素和尾元素互换，heapSize--。将最大值移到数组末尾，将该最大值排除到heap之外
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
