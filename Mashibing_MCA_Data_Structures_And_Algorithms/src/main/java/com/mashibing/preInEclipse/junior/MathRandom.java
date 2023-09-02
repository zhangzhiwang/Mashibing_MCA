package com.mashibing.preInEclipse.junior;

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
		 * 2、在[0,1)范围内出现的随机数是均匀分布的，换句话说：Math.random()这个方法在[0,1)范围内不仅仅是产生一个随机数，而且是deng等概率地产生一个随机数。
		 * 3、在[0,y)范围内出现0到某个数x的概率是多少？由于右边是开区间，所以肯定取不到y本身，但是假如能取到y，那么出现在[0,x)范围的数的概率接近x/y。
		 * 4、将上面第3点具体化，[0,1)范围内出现一个随机数x，那么随机数落在[0,x)内的概率接近x的值（或者可以近似认为就是x本身）。
		 * 那么如果让一个随机数出现在[0,x)范围内的概率是x^2呢？答案是：Math.max(Math.random(),Math.random())，即取两次随机数然后取最大值。
		 * 比如：在[0,1)范围内有一个随机数0.3，我想让出现在[0,0.3)范围内数随机数的概率不是0.3，而是0.3^2，那么Math.max(Math.random(),Math.random())就可以做到。
		 * 分析：
		 * （1）最终的目的是出现在[0,0.3)范围内数随机数的概率是0.3^2（准确地说是[0,随机数)出现的概率），那么一次Math.random()出现的随机数落在[0,0.3)范围内的概率是0.3，再来一次Math.random()的概率又是0.3，
		 * 两次都出现在[0,0.3)范围内的概率是0.3 * 0.3。两个随机事件同时发生的概率是：P（两事同时发生）=P(A)*P(B)。
		 * （2）为什么取最大值而不是最小值呢？因为只有两次Math.random()产生的随机数都必须落在[0,0.3)范围内，才能保证最终结果落在[0,0.3)范围内且概率是0.3^2；如果任意一次Math.random()产生
		 * 的随机数不在[0,0.3)范围内，那么取完最大值就不回落在[0,0.3)范围内。
		 * （3）如果取最小值（Math.min()）该怎么做？如果用最小值来做那就不能用最大值的思路了，得反过来考虑：假设最小值不在[0,0.3)范围内的概率是P，那么在这个范围内的概率就是1-P，下面的关键点就是怎么求P。
		 * 那么如何让两次随机函数的调用的最小值不在[0,0.3)范围内呢？那么就得是两次随机函数调用的结果都不在[0,0.3)范围内，这样才能保证最小值肯定不在，如果有一次的结果在这个范围那最小值就一定在这个范围，而现在要做的是怎么让最小值不在。
		 * 所以两次函数调用都不在才可以，那么Math.random()不在[0,0.3)的概率是1-0.3，两次都不在的概率是(1-0.3)^2，所以P=(1-0.3)^2，那么最中的结果1-P=1-(1-0.3)^2。
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
