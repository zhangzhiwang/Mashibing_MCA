package com.mashibing.dailyPractice.round2._71_to_100._0708;

/**
 * 汉诺塔游戏：
 * 有三个塔依次排列，分别为左塔、中塔和右塔，还有n个大小不一的圆盘，大的必须在小的下面。
 * 最初所有圆盘已经按照从上到下（依次按照从小到大）的顺序排列好并放在了左塔上，请使用最少的步数将所有圆盘移动到右塔上，并打印每一步。
 */
public class Hanoi_0713 {
    public static void hanoi(int n) {
        if(n <= 0) {
            return;
        }

        recurse(n, "from", "to", "other");
    }

    private static void recurse(int n, String from, String to, String other) {
        if(n == 1) {
            System.out.println("将" + n + "从" + from + "移动到" + to);
            return;
        }

        recurse(n - 1, from, other, to);
        System.out.println("将" + n + "从" + from + "移动到" + to);
        recurse(n - 1, other, to, from);
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
