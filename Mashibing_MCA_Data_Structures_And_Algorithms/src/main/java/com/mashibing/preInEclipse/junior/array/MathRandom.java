package com.mashibing.preInEclipse.junior.array;

/**
 * 探究Math.random()
 * 
 * @author zhangzhiwang
 * @date 2022年1月18日 下午5:45:56
 */
public class MathRandom {
	public static void main(String[] args) {
		/*
		 * 1、Math.random()产生一个范围在[0,1)之间的小数，如果想要[0,10)那么就Math.random() * 10，如果想要[1,10)那么就Math.random() * 9 + 1，如果要求生成的是整数，那么将结果强转成int即可，注意double强转int是向下取整，不是四舍五入。
		 * 2、在[0,1)范围内出现的随机数是均匀分布的
		 * 3、在[0,y)范围内出现的某个数x的概率是多少？由于右边是开区间，所以肯定取不到y本身，但是假如能取到y，那么出现在[0,x)范围的数的概率接近x/y。
		 * 4、将上面第3点具体化，[0,1)范围内出现一个随机数x，那么随机数落在[0,x)内的概率接近x的值（或者可以近似认为就是x本身）。
		 * 那么如果让一个随机数出现在[0,x)范围内的概率是x^2呢？答案是：Math.max(Math.random(),Math.random())，即取两次随机数然后取最大值。
		 * 比如：在[0,1)范围内有一个随机数0.3，我想让出现在[0,0.3)范围内数随机数的概率不是0.3，而是0.3^2，那么Math.max(Math.random(),Math.random())就可以做到。
		 * 分析：
		 * （1）最终的目的是出现在[0,0.3)范围内数随机数的概率是0.3^2，那么一次Math.random()出现的随机数落在[0,0.3)范围内的概率是0.3，再来一次Math.random()的概率又是0.3，
		 * 两次都出现在[0,0.3)范围内的概率是0.3 * 0.3。
		 * （2）为什么取最大值而不是最小值呢？因为只有两次Math.random()产生的随机数都必须落在[0,0.3)范围内，才能保证最终结果落在[0,0.3)范围内且概率是0.3^2；如果任意一个Math.random()产生
		 * 的随机数不在[0,0.3)范围内，那么取完最大值就不回落在[0,0.3)范围内。
		 * （3）如果是min会产生什么样的效果？如果用min函数是最终结果落在[0,0.3)范围内，那么就要求至少有一个Math.random()产生的随机数落在[0,0.3)范围内，这样最终结果的概率是1 - (1-0.3)^2.
		 * 可以抽象为有两个事件A和B，A发生的概率为P(A)，发生的概率为P(B)，max函数相当于求事件A和B同时发生的概率，即P(A) * P(B)，min函数相当于求事件A和事件B至少一个发生的概率，即1 - [(1 - P(A)) * (1 - P(B))]。
		 * 这是高中数学概率的知识，可以参考：https://zhidao.baidu.com/question/1705198043247483420.html
		 * 
		 */
//		double random = Math.random();
//		System.out.println("产生了一个随机数：" + random);
		
//		int totalCount = 500_0000;
//		int count = 0;
//		for(int i = 1; i <= totalCount; i++) {
//			if(Math.random() * 8 < 5) {
//				count++;
//			}
//		}
//		System.out.println("该随机数产生的概率为：" + ((double)count / (double)totalCount));
		
//		int totalCount = 500_0000;
//		int count = 0;
//		for(int i = 1; i <= totalCount; i++) {
//			if(Math.max(Math.random(), Math.random()) * 1 < 0.5) {
//				count++;
//			}
//		}
//		System.out.println("该随机数产生的概率为：" + ((double)count / (double)totalCount));// 最后的范围是x^2
		
//		int totalCount = 500_0000;
//		int count = 0;
//		for(int i = 1; i <= totalCount; i++) {
//			if(Math.max(Math.random(), Math.max(Math.random(), Math.random())) * 1 < 0.5) {
//				count++;
//			}
//		}
//		System.out.println("该随机数产生的概率为：" + ((double)count / (double)totalCount));// 最后的范围是x^3
		
		int totalCount = 500_0000;
		int count = 0;
		for(int i = 1; i <= totalCount; i++) {
			if(Math.min(Math.random(), Math.random()) * 1 < 0.5) {
				count++;
			}
		}
		System.out.println("该随机数产生的概率为：" + ((double)count / (double)totalCount));// 最后的范围是1 - (1 - x)^2
	}
}
