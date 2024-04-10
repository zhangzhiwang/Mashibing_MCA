package com.mashibing.dailyPractice.round1._91_to_100;

/**
 * 动态规划题目3：
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Knapsack_0327 {
    public static int knapsack1(int[] weights, int[] values, int bag) {
        if(bag <= 0 || weights == null || values == null || weights.length == 0 || weights.length != values.length) {
            throw new RuntimeException("参数有误");
        }

        return recurse1(weights, values, bag, 0);
    }

    private static int recurse1(int[] weights, int[] values, int bagRest, int index) {
        if(bagRest < 0) {
            return -1;
        }
        if(index == weights.length) {
            return 0;
        }

        int p1 = recurse1(weights, values, bagRest, index + 1);
        int p2 = 0;
        int value = recurse1(weights, values, bagRest - weights[index], index + 1);
        if(value >= 0) {
            p2 = values[index] + value;
        }
        return Math.max(p1, p2);
    }

    public static int knapsack2(int[] weights, int[] values, int bag) {
        if(bag <= 0 || weights == null || values == null || weights.length == 0 || weights.length != values.length) {
            throw new RuntimeException("参数有误");
        }

        int N = weights.length;
        int[][] arr = new int[bag + 1][N + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[0].length - 2; j >= 0; j--) {
                int p1 = arr[i][j + 1];
                int p2 = 0;
                int value = i - weights[j] < 0 ? -1 : arr[i - weights[j]][j + 1];
                if(value >= 0) {
                    p2 = values[j] + value;
                }
                arr[i][j] = Math.max(p1, p2);
            }
        }

        return arr[bag][0];
    }

    // 老师的代码，用于测试
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
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        int ans = maxValue(weights, values, bag);
        System.out.println("ans = " + ans);
        int ans1 = knapsack1(weights, values, bag);
        System.out.println(ans == ans1);
        int ans2 = knapsack2(weights, values, bag);
        System.out.println(ans == ans2);
    }
}
