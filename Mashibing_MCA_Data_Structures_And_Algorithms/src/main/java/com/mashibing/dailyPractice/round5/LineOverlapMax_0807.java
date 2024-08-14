package com.mashibing.dailyPractice.round5;

import com.mashibing.others.DuiShuQiUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定若干条线段，每个线段都有起止位置[start,end]，且起止位置都是整数，线段之间会有重叠且重叠部分的长度最少为1，
 * 求重叠线段的最大条数。如果只有一条线段，那么重叠次数就是1，可以理解为自己和自己重叠。
 */
public class LineOverlapMax_0807 {
    static class Line {
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int lineOverlapMax(int[][] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }

        List<Line> list = new ArrayList<>();
        for (int[] a : arr) {
            list.add(new Line(a[0], a[1]));
        }
        list.sort(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int maxCount = 0;
        for (Line line : list) {
            while (!heap.isEmpty() && line.start >= heap.peek()) {
                heap.poll();
            }

            heap.add(line.end);
            maxCount = Math.max(maxCount, heap.size());
        }

        return maxCount;
    }

    // 对数器
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

    public static void main(String[] args) {
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = lineOverlapMax(lines);
            if (ans1 != ans2) {
                for(int j = 0; j < lines.length; j++) {
                    DuiShuQiUtil.printArr(lines[j]);
                }
                System.out.println("Oops!");
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test end");
    }
}
