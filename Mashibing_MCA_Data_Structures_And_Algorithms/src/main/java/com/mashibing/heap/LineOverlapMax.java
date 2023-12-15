package com.mashibing.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 线段最大重合数问题
 * 题目：给定若干条线段，每个线段都有起止位置[start,end]，且起止位置都是整数，线段之间会有重叠且重叠部分的长度最少为1，求重叠部分最多的那个线段的重叠次数。
 * 课程：体系班课时57-59
 * 思路：见com.mashibing.preInEclipse.senior.heap.LineOverlapMax注释
 */
public class LineOverlapMax {
    static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int getLineOverlapMax(int[][] lineArr) {
        int totalLineCount = lineArr.length;
        if(lineArr == null || totalLineCount < 1) {
            return 0;
        }

        Line[] lines = new Line[totalLineCount];
        for(int i = 0; i < totalLineCount; i++) {
            lines[i] = new Line(lineArr[i][0],lineArr[i][1]);
        }

        Arrays.sort(lines, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for(int i = 0; i < lines.length; i++) {
            Line cur = lines[i];
            while(!heap.isEmpty() && cur.start >= heap.peek()) {
                heap.poll();
            }
            heap.add(cur.end);
            max = Math.max(max, heap.size());
        }

        return max;
    }

    // 以下为对数器
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
        // 对数器
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = new LineOverlapMax().getLineOverlapMax(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
}
