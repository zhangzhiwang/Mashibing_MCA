package com.mashibing.preInEclipse.junior;

/**
 * 从a-b随机到c-d随机
 * 题目1：有一个给定的函数f()，它能等概率的返回1-5的随机整数，要求不能利用其它任何函数只能使用f()函数来等概率地生成1-7范围的整数。
 * 思路：
 * 1、题目要看清，只能利用f()函数，不能使用其它任何随机函数，比如jdk自带买的Math.random()。
 * 2、利用f函数搞出一个0、1发生器出来，0、1发生器就是只能返回0和1两种值。
 * f可以返回1、2、3、4、5这5种值，当f返回1或2时发生器返回0，当f返回4或5时发生器返回1，当f返回3时发生器重新调用f函数直至f返回1、2、4、5中的一个值，这样发生器就可以利用f生成0或1两种值。
 * 3、生成的目标范围是1-7，这个要分几步来完成。首先让左边的范围是0，右边的范围是7-1=6，即先生成0-6范围的随机整数，然后在此基础上+1即可。
 * （1）看一下0-6范围的最大值6需要几个二进制位存放，一个二进制位能存放的范围是0-1（2^0），两个二进制位能存放的范围是0-3（2^0 +
 * 2^1），三个二进制位能存放的范围是0-7（2^0 + 2^1 + 2^2），
 * 所以6可以用三个二进制位存放，每个二进制位都有0和1两种值，所以每个二进制位调用一下发生器即可，然后独立调用三次发生器，分别将结果左移2位、1位和0位，将左移后的结果加起来就得到了0-7范围的数。
 * 但我们的前期目标是0-6，那么如果生成7的话就重做，直到生成不是7为止，这样把生成7的概率平摊到0-6当中，所以重做后生成的0-6的概率依然是相等的。
 * （2）将上一部生成的0-6的函数的返回值+1就是1-7范围内的随机整数了且等概率。
 * 
 * @author zhangzhiwang
 * @date 2022年1月18日 下午9:02:09
 */
public class RandomA2B {
	public static void main(String[] args) {
		int[] arr = new int[8];
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
		return (int) (Math.random() * 5) + 1;// 这里取得是整数范围在1-5，由于double强转int是向下取整，所以Math.random()要乘以5而不是4。
	}

	/**
	 * 0、1发生器
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月18日 下午9:28:39
	 */
	public static int g() {
		int result = 0;
		do {
			result = f();
		} while (result == 3);// f函数返回3重做

		return result == 1 || result == 2 ? 0 : 1;
	}

	/**
	 * 将输出范围调整到0-7
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月18日 下午9:31:40
	 */
	public static int a() {
		return (g() << 2) + (g() << 1) + (g() << 0);// 注意加号的优先级要高于位运算，所以位运算必须加括号
	}

	/**
	 * 利用a()函数将0-7范围调整到0-6
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月18日 下午9:38:33
	 */
	public static int b() {
		int result = 0;
		do {
			result = a();
		} while (result > 6);
		
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
		return b() + 1;
	}
}
