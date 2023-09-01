package com.mashibing.preInEclipse.junior.array.sort;

/**
 * 归并排序
 * 思路：现将整个数组一分为二，将左半部分的数组排好序，右半部分的数组也排好序，然后进行归并merge。
 * merge的过程要准备两个指针p1和p2和一个空数组arr，p1和p2分别指向左边子数组和右边子数组的头部，p1和p2指向的元素谁小谁拷贝到arr里，然后相应指针向后移动，
 * 当p1越界后，p2后面所有的元素都拷贝到arr里面，整体结束，p2越界同理。
 * 如何让数组左半部分的子数组和右半部分的子数组做到有序呢？地柜上面的过程，直到左子数组和右子数组各自剩一个元素，然后merge两个元素，逐层返回整体结束。
 * 
 * 举例：
 * 给定数组：7 5 6 2 3 1 8 4
 * 1、先一分为二，左子数组（左边）：7 5 6 2，右子数组（右边）：3 1 8 4，然后让左边和右边都有序，最后进行merge，merge完后进行替换。
 * 2、如何做到左边和右边都有序呢？递归思想。以左边为例：把左边的7 5 6 2一分为二，左边：7 5，右边：6 2。
 * 3、再以左边为例，如何让7 5有序呢？再一分为二：左边：7，右边：5，当左右两边都剩一个的时候停止递归，进行merge。
 * 4、merge就是合并的过程：设置两个指针p1和p2，p1指向左数组的起始位置，p2指向右数组的起始位置。
 * 找到数组中间位置的索引m，那么左数组的范围就是[0,m]，右数组的范围就是[m+1,N-1]，所以p1的起始位置是0，p2的起始位置是m+1。
 * 然后猪呢比一个空数组tmpArr，p1指向的元素和p2指向的元素谁小谁放在tmpArr里，然后谁小谁的指针往后移，另一个指针不动。当其中一个指针（比如p1）移出了左数组或有数组的范围时，
 * p2指针及其后面的所有元素原封不动地挪到tmpArr里，本次merge结束，进行下一步。
 * 5、merge完之后tmpArr里面就有了左数组和右数组的所有元素了，然后将tmpArr里的所有元素替换到原数组中相应的位置，本轮递归结束。
 * 
 * 总结：归并排序的整体思路就三步：
 * 1、数组一分为二，保证左右数组都有序
 * 2、merge
 * 3、替换
 * 在保证左右数组都有序上用到了递归思想，直到左右数组都有1个元素的时候才开始merge。
 * 
 * @author zhangzhiwang
 * @date 2022年2月9日 下午9:15:14
 */
public class MergeSort {
	public static void main(String[] args) {
		int[] arr = { 7, 5, 6, 2, 3, 1, 8, 2 };
		mergeSort(arr, 0, arr.length - 1);
		for(int i : arr) {
			System.out.print(i);
		}
	}

	/**
	 * merge方法的功能，给给定数组arr的[l,r]范围内排序
	 * 
	 * @param arr 给定数组
	 * @param l 数组的起始位置
	 * @param r 数组的结束位置
	 * @author zhangzhiwang
	 * @date 2022年2月10日 上午8:17:41
	 */
	private static void mergeSort(int[] arr, int l, int r) {
		// 7 5 6 2 3 1 8 4
		// 校验，不需要排序的场景
		if (arr == null || arr.length <= 1) {
			return;
		}

		process(arr, l, r);
	}

	private static void process(int[] arr, int l, int r) {
		if (l == r) {// 当左右数组只有一个元素就返回
			return;
		}

		// step1：一分为二，将数组在[l,r]范围内拆分为所有两个数组
		/**
		 * 找到数组中间的位置
		 * 由于除法转int是向下取整，所以用int接收是没有问题的。
		 * 但是这里有个潜在的问题：就是当数组长度足够大时，大到了接近int类型的上限时，l + r有可能超过int的最大值，一旦超过就变成了负数。可以试下：System.out.println(Integer.MAX_VALUE + 1);
		 * 所以相加除以2在数组足够长的情况下会越界。
		 *  
		 * l + ((r - l) >> 1)的理解：
		 * r和l之间一共有多少个数？一共有r - l + 1个数。r和l之间的差距是多少呢？差距是r - l，也就是r比l大了r - l。
		 * r和l之间差距的一半是多少呢？差距的一半是(r - l) / 2。比l大差距一半的数是多少呢？l加上一本的差距就是比l大差距一半的数，即l + (r - l) / 2。
		 * 把触发缓冲写法：就得到了l + ((r - l) >> 1)
		 */
		// int m = (l + r) / 2;
		int m = l + ((r - l) >> 1);
		process(arr, l, m);// 找到了中间位置之后，左数组的范围就是[l,m]
		process(arr, m + 1, r);// 右数组的范围就是[m+1,r]

		// step2：merge
		merge(arr, l, m, r);
	}

	/**
	 * 归并
	 * 
	 * @param arr 原始数组
	 * @param l 数组的起始位置
	 * @param m 数组的中间位置
	 * @param r 数组的结束位置
	 * 
	 * @author zhangzhiwang
	 * @date 2022年2月10日 上午10:07:14
	 */
	private static void merge(int[] arr, int l, int m, int r) {
		/**
		 *  临时数组用于merge，每次新生成的临时数组的长度都要和左右数组的长度和一致，而左右数组的总长度就是r - l + 1，因为每次调用merge方法l和r都会变。
		 *  不可以是arr的length，因为arr的length是固定不变的，merge只针对本次左右数组来进行的。
		 */
		int[] temArr = new int[r - l + 1];
		// 准备三个指针：
		int i = 0;// temArr用
		int p1 = l;// 左数组用，起始值为左数组的起始位置l，可不能写0
		int p2 = m + 1;// 右数组用，起始值为中间位置的后一个

		// p1和p2比较，谁小谁放到temArr里，谁小谁往后移
		while (p1 <= m && p2 <= r) {// 当p1或者p2越界时跳出循环，由于一次只移动一个指针，所以p1和p2有且只有一个会越界
			temArr[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 当退出上面的while循环之后说明已经有一个子数组拷贝完了，下面要做的是将另一个子数组没有被拷贝的元素放入进temArr里面
		// 情况1：
		while (p1 <= m) {// 如果退出上面的循环之后p1仍然小于等于m，说明上面p2所在的数组已经被拷贝完了，接下来要拷贝p1剩下的元素了
			temArr[i++] = arr[p1++];
		}
		// 情况2：
		while (p2 <= r) {// 同理，如果退出上面的循环之后p2仍然小于等于r，说明上面p1所在的数组已经被拷贝完了，接下来要拷贝p2剩下的元素了.情况1和情况2两个while循环只会进入一个
			temArr[i++] = arr[p2++];
		}

		// step3：覆盖原数组中相应位置的元素
		// 到此为止，左右数组的元素都已经拷贝到temArr里面了，下面原数组arr在[l,r]范围的元素要被temArr的元素替换掉
		for (int j = 0; j < temArr.length; j++) {
			arr[l + j] = temArr[j];
		}
	}
}
