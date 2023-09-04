package com.mashibing.preInEclipse.senior.map;

import java.util.Comparator;
import java.util.TreeMap;

import com.mashibing.preInEclipse.senior.map.AboutHashMap.MapTest;

/**
 * 有序表
 * 在java语言中，有序表就是TreeMap。
 * TreeMap在哈希表功能的基础上多了一个对key排序的功能，即无论添加时元素的key是否有序，内部存储是按照有序的结构来存储的，
 * 所以增删改查的时间复杂度做不到O(1)了，而是O(logN)。
 * 有序表可以做到key有序，但是前提是得告诉有序表key怎么排序。对于基本类型的包装类和String类都实现了Comparable接口，
 * 如果自定义类型也实现类了Comparable接口，那么就可以作为key放入有序表中，如果自定义类型没有实现Comparable接口，
 * 必须额外传入比较器来告诉key怎么排序，比较器必须实现Comparator接口。
 * 
 * 
 * @author zhangzhiwang
 * @date 2022年3月3日 下午7:55:30
 */
public class AboutTreeMap {
	public static void main(String[] args) {
		TreeMap<User, String> treeMap = new TreeMap<>(new UserComparator());
		
		User user1 = new User();
		treeMap.put(user1, "user1");
	}
	
	static class UserComparator implements Comparator<User> {
		@Override
		public int compare(User o1, User o2) {
			return 0;
		}
		
	}
	
	static class User {}
}
