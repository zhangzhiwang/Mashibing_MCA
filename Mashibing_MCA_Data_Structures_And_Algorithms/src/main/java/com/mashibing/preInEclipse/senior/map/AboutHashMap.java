package com.mashibing.preInEclipse.senior.map;

import java.util.HashMap;

/**
 * 哈希表
 * 在java语言中有序表就是HashMap
 *
 * HashMap的那点事：
 * 1、增删改查的时间复杂度度都是O(1)的，虽然都是常数时间但是这个常数时间有点大
 * 2、如果key是String类型的，那么实际上时间复杂度是是O(N)，只不过这个N不是HashMap的数据量大小而是作为key的字符串的字符数
 * 3、HashMap是不能放入重复的key的，怎么判定key是否重复呢？是调用key的equals()方法来判断的，如果key的equals()方法返回true则代表key重复，否则为key不重复
 *
 * @author zhangzhiwang
 * @date 2022年3月3日 下午7:35:42
 */
public class AboutHashMap {
	public static void main(String[] args) {
		HashMap<Integer, String> map1 = new HashMap<>();
		
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(1);
		System.out.println(i1 == i2);
		System.out.println(i1.equals(i2));
		
		map1.put(i1, "i1");
		System.out.println("contains i2 ? " + map1.containsKey(i2));// 只要equals()方法返回tru就认为是同一个key
		
		System.out.println("-------------");
		MapTest m1 = new MapTest(1);
		MapTest m2 = new MapTest(1);
//		System.out.println(m1 == m2);
//		System.out.println(m1.equals(m2));
		
		HashMap<MapTest, String> map2 = new HashMap<>();
		map2.put(m1, "m1");
		System.out.println(map2.containsKey(m2));// map2是否包含m2取决于MapTest的equals()方法怎么定义的
	}
	
	static class MapTest {
		private int value;

		public MapTest(int value) {
			super();
			this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + value;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MapTest other = (MapTest) obj;
			if (value != other.value)
				return false;
			return true;
		}
		
		
	}
}
