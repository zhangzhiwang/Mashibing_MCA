package com.mashibing.preInEclipse.senior.bit;

/**
 * 异或题1——不用额外变量交换两个数
 * 思路：
 * 利用^运算的交换律和结合律
 *
 * @author zhangzhiwang
 * @date 2022年2月14日 上午10:07:37
 */
public class EOR1_ExchangeTwoNum {
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		exchange(a, b);
//		t(a);
//		System.out.println(a);
	}

	private static void exchange(int a, int b) {
		System.out.println(String.format("原始：a=%d，b=%d", a, b));
		/**
		 * 传统方法交换两个变量要设置一个临时变量来保存其中一个值，利用^运算就不需要额外的变量了
		 *
		 * ^运算的一些规律：
		 * 1、交换律：a ^ b = b ^ a
		 * 2、结合律：(a ^ b) ^ c = a ^ (b ^ c)
		 * 3、一个数和自己作^运算结果等于0：a ^ a = 0
		 * 4、一个数和0作^运算会还原这个数本身：a ^ 0 = a
		 * 5、^运算相当于无进位相加
		 *
		 * ^的思路：利用^运算的交换律、结合律和上面3、4两个特性
		 * 设初始int a = A; int b = B;
		 * 首先a = a ^ b，将a ^ b的结果赋值给a，b不变。所以执行完a = A ^ B，b = B。
		 * 其次b = a ^ b，将a ^ b的结果赋值给b，a不变。所以执行完a = A ^ B，b = A ^ B ^ B = A ^ (B ^ B) = A ^ 0 = A。
		 * 最后a = a ^ b，将a ^ b的结果赋值给a，b不变。所以执行完a = A ^ B ^ A = A ^ A ^ B = (A ^ A) ^ B = 0 ^ B = B，b = A。
		 * 所以整体执行完a = B，b = A。
		 * 注意：这种算法的前提条件是入参a和b的值可以相同，但是必须要指向两个不同的内存区域，如果引用a和b指向相同的内存区域，那么最后a和b都会被刷成0.
		 */
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(String.format("交换后：a=%d，b=%d", a, b));
	}
	
	private static void t(int a) {
		a = a + 1;
	}
}
