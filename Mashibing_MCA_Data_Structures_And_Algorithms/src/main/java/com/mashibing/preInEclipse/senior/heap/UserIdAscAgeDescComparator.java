package com.mashibing.preInEclipse.senior.heap;

import java.util.Comparator;

/**
 * 比较器——id正序排序，id相同按age倒序排序
 *
 * @author zhangzhiwang
 * @date 2022年2月15日 上午11:54:38
 */
public class UserIdAscAgeDescComparator implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		return o1.getId() != o2.getId() ? o1.getId() - o2.getId() : o2.getAge() - o1.getAge();// id正序排序，id相同按age倒序排序
	}
}
