package com.mashibing.preInEclipse.junior.array;

/**
 * 从a-b随机到c-d随机
 * 题目2：有一个给定的函数f()，它能等概率的返回5-20的随机整数，要求不能利用其它任何函数只能使用f()函数来等概率地生成31-66范围的整数。
 * 思路：
 * 1、题目要看清，只能利用f()函数，不能使用其它任何随机函数，比如jdk自带买的Math.random()。
 * 2、利用f函数搞出一个0、1发生器出来，0、1发生器就是只能返回0和1两种值。
 * f可以返回5-20这16种值，当f返回5-12时发生器返回0，当f返回13-20时发生器返回1，f函数的返回值范围正好是偶数个值，所以不需要重做，这样发生器就可以利用f生成0或1两种值。
 * 3、生成的目标范围是31-66，这个要分几步来完成。首先让左边的范围是0，右边的范围是66-31=35，即先生成0-35范围的随机整数，然后在此基础上+31即可。
 * （1）看一下0-35范围的最大值35需要几个二进制位存放，一个二进制位能存放的范围是0-1（2^0），两个二进制位能存放的范围是0-3（2^0 +
 * 2^1），三个二进制位能存放的范围是0-7（2^0 + 2^1 + 2^2），
 * 所以35最少需要6个二进制位存放，每个二进制位都有0和1两种值，所以每个二进制位调用一下发生器即可，然后独立调用三次发生器，分别将结果左移5-0位，将左移后的结果加起来就得到了0-63范围的数。
 * 但我们的前期目标是0-35，那么如果生成的数在[36,63]的话就重做，直到生成的范围在[0-35]为止，这样把生成[36,63]的概率平摊到0-35当中，所以重做后生成的0-35的概率依然是相等的。
 * （2）将上一部生成的0-6的函数的返回值+31就是31-66范围内的随机整数了且等概率。
 * 
 * @author zhangzhiwang
 * @date 2022年1月18日 下午9:02:09
 */
public class RandomA2B_2 {
	public static void main(String[] args) {
		int[] arr = new int[67];
		for(int i = 0; i < 10000; i++) {
			arr[c()]++;
		}
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(String.format("生成整数%d的次数是%d", i, arr[i]));
		}
	}

	/**
	 * 题目已知函数，只能使用不能修改
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月18日 下午9:24:02
	 */
	public static int f() {
		return (int) (Math.random() * 16) + 5;
	}

	/**
	 * 0、1发生器
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月18日 下午9:28:39
	 */
	public static int g() {
		return f() < 13 ? 0 : 1;
	}

	/**
	 * 将输出范围调整到0-63
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月18日 下午9:31:40
	 */
	public static int a() {
		return (g() << 5) + (g() << 4) + (g() << 3) + (g() << 2) + (g() << 1) + (g() << 0);// 注意加号的优先级要高于位运算，所以位运算必须加括号
	}

	/**
	 * 利用a()函数将0-63范围调整到0-35
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月18日 下午9:38:33
	 */
	public static int b() {
		int result = 0;
		do {
			result = a();
		} while (result > 35);
		
		return result;
	}
	
	/**
	 * 输出题目要求的结果1-7
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月18日 下午9:40:05
	 */
	public static int c() {
		return b() + 31;
	}
}
