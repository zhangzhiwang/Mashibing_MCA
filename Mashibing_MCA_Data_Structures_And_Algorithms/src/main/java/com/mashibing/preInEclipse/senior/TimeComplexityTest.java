package com.mashibing.preInEclipse.senior;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间复杂度
 * 
 * @author zhangzhiwang
 * @date 2022年2月11日 上午10:39:41
 */
public class TimeComplexityTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();// 假设list是个单链表
		int N = list.size();
		
		/**
		 * 时间复杂度是O(N^2)
		 * 注意：list.get(int index)不是常数操作 
		 */
		for(int i = 0; i < N; i++) {// for循环的时间复杂度是O(N)
			Integer result = list.get(i);// list.get(int index)的时间杂度是O(N)，因为每get一个索引的值都是从起始位置head节点开始出发的，一个一个跳转直到找到位置index为止。
			System.out.println(result);
		}
		// 所以上面的循环整体时间复杂度是O(N * N)
		
 	}
}
