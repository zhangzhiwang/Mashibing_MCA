package com.mashibing.preInEclipse.junior.array;

/**
 * 打印int数的二进制形式
 * 
 * @author zhangzhiwang
 * @date 2022年1月13日 下午4:09:05
 */
public class PrintIntBinary {
	public static void printIntBinary(int num) {
		/**
		 * 核心思想：把1作为探针，将num的二进制表示的每一位都和1进行&运算，这里用到了&的两个特性:
		 * （1）任何一个二进制数（0或1）和1进行&运算的结果就是该数本身，换句话说就是一个二进制数和1进行&运算的结果可以还原该数本身的值。
		 * （2）任何一个二进制数（0或1）和0进行&运算的结果都是0。
		 * 这个算法是利用两个数&操作的整体结果来判断某一位是0还是1，这是关键点。要达到这一点就必须排除被考察位之外其它位的干扰，所以就用到了&的第二个特性：任何二进制数与0与的结果都是0。
		 * 首先无论1左移或者右移多少位其中只有一位是1，其它位都是0，那么让num的其它位和0进行与操作得出的结果都是0，这样将num其它位上的数字抵消掉了（因为全变成0了）。就剩下被考察位和1与的结果了，如果结果是0，那么整体的32位就都是0了，那么与的结果就是0，
		 * 再根据与运算的第一个特性（任何一个二进制数与1进行与运算结果都是该数本身）就可以判断出被考察位是0。反之，如果整体&运算的结果不是0，那么说明被考察位的值是1，这样就可以根据两个操作数整体与运算的结果来判断num某一位上是否为1。
		 */
//		System.out.println("算法1：");
		for (int i = 31; i >= 0; i--) {
			System.out.print((((1 << i) & num) == 0 ? "0" : "1"));
		}
		System.out.println();
		
//		System.out.println("算法2：");
//		for (int i = 0; i <= 31; i++) {
//			System.out.print((((2147483648L >> i) & num) == 0 ? "0" : "1"));
//		}
//		System.out.println();
	}
	
	/**
	 * 取相反数
	 * 
	 * @param num
	 * @author zhangzhiwang
	 * @date 2022年1月13日 下午7:18:18
	 */
	public static void reverse(int num) {
		System.out.println(~num + 1);
	}
	
	public static void main(String[] args) {
//		printIntBinary(Integer.MIN_VALUE);
//		System.out.println(Integer.MIN_VALUE);
//		reverse(Integer.MIN_VALUE);
		
		printIntBinary(-2);
	}
}
