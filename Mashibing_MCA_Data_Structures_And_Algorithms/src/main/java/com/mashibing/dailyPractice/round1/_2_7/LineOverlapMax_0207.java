package com.mashibing.dailyPractice.round1._2_7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定若干条线段，每个线段都有起止位置[start,end]，且起止位置都是整数，线段之间会有重叠且重叠部分的长度最少为1，求重叠线段的最大条数。
 * 如果只有一条线段，那么重叠次数就是1，可以理解为自己和自己重叠。
 */
public class LineOverlapMax_0207 {
    static class Line_0207 {
        public int start;
        public int end;

        public Line_0207(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int lineOverlapMax(int[][] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }

//        int N = arr.length;
//        Line_0207[] lines = new Line_0207[N];
//        for(int i = 0; i < N; i++) {
//            lines[i] = new Line_0207(arr[i][0], arr[i][1]);
//        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for(int i = 0; i < arr.length; i++) {
            while(!heap.isEmpty() && heap.peek() <= arr[i][0]) {
                heap.poll();
            }
            heap.add(arr[i][1]);
            max = Math.max(max, heap.size());
        }

        return max;
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
            int ans2 = lineOverlapMax(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
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
