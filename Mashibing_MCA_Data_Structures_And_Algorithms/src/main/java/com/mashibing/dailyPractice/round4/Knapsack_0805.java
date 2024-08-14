package com.mashibing.dailyPractice.round4;

/**
 * 动态规划题目3：
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Knapsack_0805 {
    public static int knapsack1(int[] weights, int[] values, int bag) {
        if(weights.length == 0 || bag <= 0) {
            return 0;
        }

        return recurse(weights, values, 0, bag);
    }

    private static int recurse(int[] weights, int[] values, int i, int rest) {
        if(rest < 0) {
            return -1;
        }
        if(i == weights.length) {
            return 0;
        }

        int p1 = recurse(weights, values, i + 1, rest);
        int p2 = 0;
        int v = recurse(weights, values, i + 1, rest - weights[i]);
        if(v >= 0) {
            p2 = values[i] + v;
        }

        return Math.max(p1, p2);
    }

    public static int knapsack2(int[] weights, int[] values, int bag) {
        if (weights.length == 0 || bag <= 0) {
            return 0;
        }

        int n = weights.length;
        int[][] buf = new int[n + 1][bag + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < bag + 1; j++) {
                int p1 = buf[i + 1][j];
                int p2 = 0;
                if(j - weights[i] >= 0) {
                    p2 = values[i] + buf[i + 1][j - weights[i]];
                }

                buf[i][j] = Math.max(p1, p2);
            }
        }

        return buf[0][bag];
    }

    // 老师的代码
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数！
        return process(w, v, 0, bag);
    }

    // index 0~N
    // rest 负~bag
    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println("correct ans:");
        System.out.println(maxValue(weights, values, bag));
        System.out.println("my ans1:");
        System.out.println(knapsack1(weights, values, bag));
        System.out.println("my ans2:");
        System.out.println(knapsack2(weights, values, bag));
    }
}
