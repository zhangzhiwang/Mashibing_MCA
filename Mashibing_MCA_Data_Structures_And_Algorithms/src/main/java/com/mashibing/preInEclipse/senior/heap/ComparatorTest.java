package com.mashibing.preInEclipse.senior.heap;

import java.util.Arrays;

/**
 * 比较器测试
 *
 * @author zhangzhiwang
 * @date 2022年2月15日 上午11:32:41
 */
public class ComparatorTest {
	public static void main(String[] args) {
		User u1 = new User(1, "zs", 18);
		User u2 = new User(2, "ls", 19);
		User u3 = new User(3, "ww", 20);

		User[] uerArr = new User[] { u3, u1, u2 };
		System.out.println("数组排序前：");
		for (User u : uerArr) {
			System.out.println(u);
		}

		System.out.println("id正序排序后：");
		Arrays.sort(uerArr, new UserIdAscComparator());
		for (User u : uerArr) {
			System.out.println(u);
		}
		System.out.println("------------");

		System.out.println("age倒序排序后：");
		Arrays.sort(uerArr, new UserAgeDescComparator());
		for (User u : uerArr) {
			System.out.println(u);
		}
		System.out.println("------------");

		System.out.println("id正序排序，id相同按age倒序排序后：");
		u1 = new User(3, "zs", 18);
		u2 = new User(1, "ls", 19);
		u3 = new User(1, "ww", 20);
		uerArr = new User[] { u3, u1, u2 };
		Arrays.sort(uerArr, new UserIdAscAgeDescComparator());
		for (User u : uerArr) {
			System.out.println(u);
		}
		System.out.println("------------");
	}
}
