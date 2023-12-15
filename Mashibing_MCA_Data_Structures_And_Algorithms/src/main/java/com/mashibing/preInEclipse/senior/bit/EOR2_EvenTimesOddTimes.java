package com.mashibing.preInEclipse.senior.bit;

/**
 * 异或题2——给定一个数组，这个数组里面有且只有一个数出现的次数是奇数次，其它所有元素出现的次数是偶数次，找到这个出现奇数次的元素。
 * 思路：
 * 利用^的交换律
 *
 * @author zhangzhiwang
 * @date 2022年2月14日 上午10:33:37
 */
public class EOR2_EvenTimesOddTimes {
	public static void main(String[] args) {
		int[] arr = { 1, 3, 5, 7, 9, 3, 7, 5, 5, 5, 1, 9, 1, 7, 7 };
		int result = findOddTimes(arr);
		System.out.println(result);
	}

	private static int findOddTimes(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("数组不能为空！");
		}

		/**
		 * 传统思路：用一个Map来接收数组的所有元素，key为元素的值，value为元素出现的频次，遍历一遍arr数组后将所有元素及其频次都放入到Map中，最后再遍历Map找出频次为基数的元素。
		 * 传统思路的弊端：时间复杂度是O(2*N)，空间复杂度是O(N).
		 * 使用^运算遍历一次数组就可以，而且只需要一个临时变量int。
		 * 
		 * 让变量eor和数组的每一个元素都异或一遍，每一次异或都将结果保存到eor里面，最后eor的值就是出现奇数次的元素本身。
		 * 利用^的三个特性：
		 * 1、自己和自己异或永远等于0
		 * 2、0和任何数异或结果都是该数本身
		 * 3、交换律：a ^ b ^ c = a ^ c ^ b
		 * 
		 * 原始数组：{ 1, 3, 5, 7, 9, 3, 7, 5, 5, 5, 1, 9, 1, 7, 7 }
		 * 利用交换律：1 ^ 3 ^ 5 ^ 7 ^ …… ^ 7 = 1 ^ 1 ^ 1 ^ 3 ^ 3 ^ 5 ^ 5 ^ 5 ^ 5 ^ 7 ^ ……
		 * 利用特性1和特性2：偶数个自己进行异或结果是0，奇数个自己进行异或结果是自己本身
		 * 根据题目有且只有一个数出现奇数次，那么最终eor的值就只出现奇数次的元素本身
		 */
		int eor = 0;// eor的初始值必须为0，因为任何值和0进行^运算都是该值本身
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}

		return eor;
	}
}
