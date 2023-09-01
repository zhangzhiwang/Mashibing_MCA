package com.mashibing.preInEclipse.junior.array;

/**
 * 阶乘
 * 计算：1!+2!+3!+……+n!
 * 
 * 思路：n! = (n-1)! * n
 * 用一个临时变量保存(n-1)!，循环n次，每一次都用n乘以(n-1)!，再用一个变量保存和。
 * 
 * 不好的思路：用两个for循环，外层循环n次，内层循环计算n!，然后加起来，这样时间复杂度就大了。
 * 
 * @author zhangzhiwang
 * @date 2022年1月17日 上午10:16:58
 */
public class JieCheng {
	public static void main(String[] args) {
		int n = 5;// 1!+2!+3!+……+5! = 1 + 2 + 6 + 24 + 120
		int tmp = 1;
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			tmp *= i;
			sum += tmp;
		}

		System.out.println(sum);
	}
}
