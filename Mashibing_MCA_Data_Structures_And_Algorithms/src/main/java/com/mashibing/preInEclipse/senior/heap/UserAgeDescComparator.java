package com.mashibing.preInEclipse.senior.heap;

import java.util.Comparator;

/**
 * 比较器——UserAge降序排序
 *
 * @author zhangzhiwang
 * @date 2022年2月15日 上午11:47:03
 */
public class UserAgeDescComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {// 降序排序
//		if (o1.getAge() - o2.getAge() < 0) {
//			return 1;// return正数第二个参数排在前面，正好第二个数大
//		} else if (o1.getAge() - o2.getAge() > 0) {
//			return -1;// return负数第一个参数排在前面，正好第一个数大
//		} else {
//			return 0;
//		}
		
		return o2.getAge() - o1.getAge();
	}
}
