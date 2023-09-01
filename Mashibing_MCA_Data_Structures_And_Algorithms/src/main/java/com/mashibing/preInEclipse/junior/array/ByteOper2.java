package com.mashibing.preInEclipse.junior.array;

/**
 * java位运算
 * 
 * @author zhangzhiwang
 * @date 2022年1月10日 下午3:43:13
 */
public class ByteOper2 {
	public static void main(String[] args) {
		int i = 100;
		System.out.println(i * 32);
		System.out.println(i << 5);// 向左移动n位就相当于乘以2的n次方，当于十进制数3左移一位右边补0相当于乘以10
		
		System.out.println("---------");
		System.out.println(i / 4);
		System.out.println(i >> 2);// 向右移动n位就相当于除以2的n次方，当于十进制数30右移一位左边补0相当于除以10
		
		
	}
}
