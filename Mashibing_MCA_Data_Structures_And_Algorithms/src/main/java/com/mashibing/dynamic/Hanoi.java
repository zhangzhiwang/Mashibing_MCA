package com.mashibing.dynamic;

/**
 * 暴力递归——汉诺塔问题
 * 题目：汉诺塔游戏：有三个塔依次排列，分别为左塔、中塔和右塔，还有n个大小不一的圆盘，大的必须在小的下面。
 * 最初所有圆盘已经按照从上到下（依次按照从小到大）的顺序排列好并放在了左塔上，请使用最少的步数将所有圆盘移动到右塔上，并打印每一步。
 * 思路：
 * 1、如果将所有的圆盘按照大小顺序全部摞在一起，那么可以将这n个圆盘分为两大类：第一大类是上面的n-1个圆盘，第二大类是最后一个圆盘。
 * 2、汉诺塔游戏确实有三个塔，分别是左、中、右，但是在做题时要进行抽象，去掉左、中、右的概念，抽象成：圆盘从A塔移动到B塔，那么A塔就是from塔，B塔就是to塔，另一个就是other塔，
 * from、to、other这三个塔可以是左、中、右塔中的任何一个。
 * 3、从游戏开始到游戏结束，如果从整体来看可以将整个过程分成三步：
 * 最开始所有的n的圆盘都在from上：
 * step 1：将n-1个圆盘从from塔移动到other塔；
 * step 2：将最后一个圆盘从from塔移动到to塔；
 * step 3：将n-1个圆盘从other塔移动到to塔
 * 递归下去，每一个递归都是上面的三个步骤，直到达到base case：将全局最小的圆盘从from塔移动到to塔。
 *
 * 课程：体系班课时158-160
 */
public class Hanoi {
    /**
     *
     * @param n 一共有n个圆盘，也可以理解为圆盘都摞在一起一共有n层
     */
    public static void hanoi(int n) {
        if(n <= 0) {
            return;
        }

        recurse(n, "左塔", "右塔", "中塔");
    }

    /**
     *
     * @param n 一共有多少个圆盘
     * @param from from塔
     * @param to to塔
     * @param other other塔
     */
    private static void recurse(int n, String from, String to, String other) {
        if(n == 1) {// base case：将全局最小的圆盘从from塔移动到to塔
            System.out.println("将1（全局最小）从" + from + "移动到" + to);
        } else {
            // step 1：将n-1个圆盘从from塔移动到other塔
            recurse(n - 1, from, other, to);
            // step 2：将最后一个圆盘从from塔移动到to塔
            System.out.println("将" + n + "从" + from + "移动到" + to);
            // step 3：将n-1个圆盘从other塔移动到to塔
            recurse(n - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
