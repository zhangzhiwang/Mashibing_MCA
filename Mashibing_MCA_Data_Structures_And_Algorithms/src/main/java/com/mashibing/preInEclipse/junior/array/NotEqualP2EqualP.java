package com.mashibing.preInEclipse.junior.array;

/**
 * 不等概率转换为等概率
 * 题目：有一个已知函数f以固定概率P返回0，以固定概率1-P返回1，且P!=1-P，即函数f以固定但不相等的概率返回0和1。
 * 在只使用f函数的情况下，如何设计一个函数g使得g以等概率返回0和1（返回0或1的概率各50%）。
 *
 * 思路：
 * 调两次f函数，如果两次调用都返回0或者都返回1则重做，因为这样返回的0或者1的概率是不相等的：因为P != 1 - P，所以P * P != (1 - P) * (1 - P)。
 * 直到两次调用f返回的数不相等时返回第一次调用的结果。比如第一次返回0概率是P，第二次返回1概率是1-P，那么返回第一次的调用结果0，所以出现0的概率是P * (1-P)；
 * 同理，第一次返回1概率是1-P，第二次返回0概率是P，那么返回第一次的调用结果1，所以出现1的概率是(1-P) * P，这样无论返回0还是1概率都是P * (1-P)。
 * 
 * @author zhangzhiwang
 * @date 2022年1月19日 上午10:25:25
 */
public class NotEqualP2EqualP {
	public static void main(String[] args) {

	}

	/**
	 * 题目已知函数f，这里以30%的概率返回0，以70%的概率返回1
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月19日 上午10:36:21
	 */
	public static int f() {
		return Math.random() < 0.3 ? 0 : 1;
	}

	/**
	 * 以相等概率返回0或1
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年1月19日 上午10:38:57
	 */
	public static int g() {
		int result = 0;
		do {
			result = f();
		} while (result == f());// 如果第一次和第二次的结果一致则重做，在while的判断条件里面调用第二次f

		return result;
	}
}
