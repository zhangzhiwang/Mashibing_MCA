package com.mashibing.dailyPractice.round2._71_to_100;

/**
 * 有三个塔依次排列，分别为左塔、中塔和右塔，还有n个大小不一的圆盘，大的必须在小的下面。
 * 最初所有圆盘已经按照从上到下（依次按照从小到大）的顺序排列好并放在了左塔上，请使用最少的步数将所有圆盘移动到右塔上，并打印每一步。
 */
public class Hanoi_0509 {
    public static void hanoi(int N) {
        if(N < 1) {
            return;
        }

        recurse(N, "左", "中", "右");
    }

    private static void recurse(int N, String from, String other, String to) {
        if(N == 1) {
            System.out.println(N + "从" + from + "移动到" + to);
            return;
        }

        recurse(N - 1, from, to, other);
        System.out.println(N + "从" + from + "移到" + to);
        recurse(N - 1, other, from, to);
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
