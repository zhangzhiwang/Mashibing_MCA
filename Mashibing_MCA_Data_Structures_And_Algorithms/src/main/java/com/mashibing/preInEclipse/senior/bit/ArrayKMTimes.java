package com.mashibing.preInEclipse.senior.bit;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 题目：给定一个int类型的数组，有且只有一个元素出现了k次，其它所有元素都出现了m次，其中m>1且k<m，找出出现k次的这个元素是什么，要求时间复杂度是O(N)，空间复杂度是O(1)。
 * 思路：
 * 1、创建一个有32个元素的int数组tmpArr
 * 2、将原数组arr的所有元素的所有位加入到tmpArr中
 * 3、找到出现k次的元素a在哪些位上有1
 * 4、申请一个临时变量result初始值为0，用result做出一个a来返回
 *
 * @author zhangzhiwang
 * @date 2022年2月14日 下午5:06:55
 */
public class ArrayKMTimes {
	public static void main(String[] args) {
		// 人工验证
		int[] arr = { -3,-3,-3,-1,-1};
		int k = 2;
		int m = 3;
		int result = findOnlyKTimesValue(arr, m);
		System.out.println(result);

	}

	/**
	 * 找出出现k次的元素
	 * 思路：
	 * 1、题目给定的是int类型，所以准备一个int[]数组tmpArr，由于int类型的长度是32位，所以这个数组的长度是32位，即int[] tmpArr = new int[32];
	 * 这个数组的每一个元素对应一个int型整数的二进制位。
	 * 2、假设题目给定的数组是arr，接下来将arr里面的所有元素的二进制位全部放到这个tmpArr数组里面。怎么全放进去呢？一个一个放。
	 * 初始tmpArr的32个元素都是0，将arr第一个数的二进制形式放到数组里面，每一位对应数组的一个元素，即arr[0]代表arr第一个元素的第0位，arr[1]代表arr第一个元素的第1位……arr[31]代表arr第一个元素的第31位，
	 * 这样arr第一个元素的所有二进制位都放到了tmpArr数组里面，然后放arr的第二个元素。放第二个元素的时候就是将第二个元素的各位加入到tmpArr的相应元素里面。
	 * 比如：tmpArr[0]的值是1，说明arr的第一个元素的第一位是1，现在要放入arr的第二个元素的第一位，如果也是1，就将tmpArr[0] + 1即可，所以tmpArr[0]的值变成了2，以此类推，将arr的所有元素的所有位都放到tmpArr数组里面。
	 * 3、进行完第二步之后，tmpArr里面已经放入了arr的所有元素的所有位，元素值都是累加的。然后依次判断tmpArr里面的每个元素和m的模值。
	 * 假如tmpArr[0] % m = 0，且假设出现k次的数是a，说明a的第0位不包含1；如果tmpArr[0] % m != 0，说明a的第0位一定包含1，以此类推判断所有的32位，这样就知道了a的32位哪些位上有1哪些没有。
	 * 4、经过第三步已经知道了a的所有位上哪些有1哪些没有1，怎么将a返回呢？申请一个int型的变量result，初始值为0，即result的32为上全是0，如何将result做成a呢？
	 * 这就涉及到另一个位运算的知识点：如何将某一位上的值改成1而其它位保持不动呢？我们设目标位是第i位，那么就将这个数num |= (1 << i)。这样就过一次循环之后就可以将result做成a返回了。
	 * 
	 * @param arr
	 * @param m 题目已知的m次
	 * @author zhangzhiwang
	 * @date 2022年2月14日 下午5:10:11
	 */
	public static int findOnlyKTimesValue(int[] arr, int m) {
		if (arr == null) {
			throw new RuntimeException("数组不能为空！");
		}

		int[] tmpArr = new int[32];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < 32; j++) {// 虽然是两次for循环，但是整体的时间复杂度是O(N)，因为内层循环的次数是有限且已知的和数据量N无关，是常数项，所以内层for循环的时间复杂度是O(1)。
				if ((arr[i] & (1 << j)) != 0) {// 说明arr[i]的第j位是1
					tmpArr[j]++;
				}
			}
		}
		// 上面的两个for循环结束之后，tmpArr里面已经包括了arr里面所有元素的所有位的值的累加和。
		// 假设出现k次的元素是a，下面就要找出a的哪些位上有1
		int result = 0;// 用result来做出a，result的初始值的32位上都是0
		for (int i = 0; i < 32; i++) {// 注意：这里的for循环循环的次数是有限且已知的，和数据量N无关，时间复杂度是O(1)
			if (tmpArr[i] % m != 0) {// 说明a的第i位上肯定是1且被加进在tmpArr[i]里面，否则tmpArr[i]应该是m的整数倍
				result |= (1 << i);// 用result做出a，做法就是a的哪位上有1就让result的相应位上有1，或运算就可以做到，与运算不行
			}
		}

		return result;
	}
}
