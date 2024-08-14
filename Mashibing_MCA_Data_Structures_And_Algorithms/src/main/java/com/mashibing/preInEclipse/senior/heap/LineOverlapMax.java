package com.mashibing.preInEclipse.senior.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 线段最大重合条数
 * 题目：给定若干条线段，每个线段都有起止位置[start,end]，且起止位置都是整数，线段之间会有重叠且重叠部分的长度最少为1，求重叠部分最多的那个线段的重叠次数。
 * 解释：
 * 1、”叠部分的长度最少为1“的意思是只有某一个点重合不叫重合，比如两条线段：一条是[1,3]，另一条是[3,5]，这两条线段只有在3这个点重合且重合的线段长度是0，所以这两条线段认为没有重合。
 * 2、要清楚这道题求什么，求的是：将所有已知线段全部放在一起，重叠次数最多的部分重叠了多少次，不是求重叠次数最多的线段的起止位置。
 * 3、通过题目的已知条件可以推断出：重叠部分的起始位置肯定是某条原始线段的起始位置，同样，重叠部分的终止位置肯定是某条原始线段的终止位置，
 * 只不过重叠线段的”起“和”止“可能不是同一条原始线段的”起“和”止“，有可能是不同原始线段的。
 * 比如三条线段：[1,10][2,12][3,15]，那么重叠最多的部分是[3,10]，重叠部分被重复了3次，所以最终答案是3，但是这个重叠最多的部分的起止位置分别来自两条线段。
 * 4、如果只有一条线段，那么重叠次数就是1，可以理解为自己和自己重叠。
 * 
 * 思路：
 * 1、将所有线段的起始位置按照从小到大排序
 * 2、准备一个小根堆，用于放线段的终点
 * 3、遍历排好序的所有线段，将每一个线段的起始位置和小根堆里面的元素作比较，所有小于等于这个起始位置的元素都被弹出，然后将该线段的结束位置放到堆里，
 * 堆里面剩下的元素个数就是能够穿过以该线段的起点为起点的重复线段的线段数，即堆里留下来的线段都能够穿过该起点。
 * 4、找出所有线段数重叠数量的最大值
 * 总结一句话：将所有线段按照起始位置的大小依次排在x轴上，从左到右开始数重复的线段，看哪一部分重叠的次数最多，返回最多的重叠线段数。
 * 
 * 时间复杂度O(N*logN)
 * 
 * @author zhangzhiwang
 * @date 2022年2月16日 上午11:57:57
 */
public class LineOverlapMax {
	public static void main(String[] args) {
		// 对数器
		System.out.println("test begin");
		int N = 100;
		int L = 0;
		int R = 200;
		int testTimes = 200000;
		for (int i = 0; i < testTimes; i++) {
			int[][] lines = generateLines(N, L, R);
			int ans1 = maxCover1(lines);
			int ans2 = getLineOverlapMax(lines);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
	}
	
	static class Line {
		private int start;
		private int end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}
	}

	/**
	 * 
	 * 
	 * @param lineArr 二维数组里面放的都是一个一个的线段line，每个line都有一个起止位置[start,end]
	 * @return
	 * @author zhangzhiwang
	 * @date 2022年2月16日 下午12:20:52
	 */
	private static int getLineOverlapMax(int[][] lineArr) {
		if (lineArr == null) {
			return 0;
		}

		// 0、将二维数组转换为一维数组，将原第二维的数组封装进Line对象里（这一步可有可无，看题目给的什么，如果给的直接就是Line[]就省略本步）
		Line[] lines = new Line[lineArr.length];
		for (int i = 0; i < lineArr.length; i++) {
			lines[i] = new Line(lineArr[i][0], lineArr[i][1]);
		}

		// 1、按照每个线段的起始位置排序（排序的目的是后面小根堆可以放心地弹出，因为被弹出的元素已经考察过了，后面也不会再考察了）
		Arrays.sort(lines, new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				return o1.start - o2.start;
			}
		});

		// 2、准备小根堆，这里用java的优先级队列实现
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();// 由于优先级队列的泛型是基本数据类型，所以默认是小根堆

		// 3、遍历所有的线段，并将每个线段的起始位置和小根堆的元素作比较，小根堆中所有小于等于起始位置大小的元素全部弹出，堆中剩下元素的个数就是以该线段起点为起点的重复线段的个数，然后将线段的终点放入堆中
		int max = 0;
		for (int i = 0; i < lineArr.length; i++) {// O(N)
			while (!heap.isEmpty() && lines[i].start >= heap.peek()) {
				heap.poll();
			}
			heap.add(lines[i].end);// O(logN)
			max = Math.max(max, heap.size());
		}

		// 4、返回最大值
		return max;
	}
	
	public static int[][] generateLines(int N, int L, int R) {
		int size = (int) (Math.random() * N) + 1;
		int[][] ans = new int[size][2];
		for (int i = 0; i < size; i++) {
			int a = L + (int) (Math.random() * (R - L + 1));
			int b = L + (int) (Math.random() * (R - L + 1));
			if (a == b) {
				b = a + 1;
			}
			ans[i][0] = Math.min(a, b);
			ans[i][1] = Math.max(a, b);
		}
		return ans;
	}
	
	public static int maxCover1(int[][] lines) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < lines.length; i++) {
			min = Math.min(min, lines[i][0]);
			max = Math.max(max, lines[i][1]);
		}
		int cover = 0;
		for (double p = min + 0.5; p < max; p += 1) {
			int cur = 0;
			for (int i = 0; i < lines.length; i++) {
				if (lines[i][0] < p && lines[i][1] > p) {
					cur++;
				}
			}
			cover = Math.max(cover, cur);
		}
		return cover;
	}
}
