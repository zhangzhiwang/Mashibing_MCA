package com.mashibing.preInEclipse.senior.heap;

import java.util.Comparator;

/**
 * 比较器——按UserId正序排序
 *
 * @author zhangzhiwang
 * @date 2022年2月15日 上午11:43:32
 */
public class UserIdAscComparator implements Comparator<User> {
	/**
	 * Comparator的compare方法符合以下规则：
	 * 1、返回负数则第一个参数排在第二个参数的前面
	 * 2、返回正数则第二个参数排在第一个参数的前面
	 * 3、返回0则代表两个参数相等，返回谁无所谓
	 */
	@Override
	public int compare(User o1, User o2) {// 实现按id正序排序
		return o1.getId() - o2.getId();
	}
}
