package com.mashibing.dailyPractice.round4;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 有一个给定的函数f()，它能等概率的返回n1-m1的随机整数，要求不能利用其它任何函数只能使用f()函数来等概率地生成n2-m2范围的整数
 * 本次：n1-m1为1-18，n2-m2为43-99
 */
public class RandomA2B_0721 {
    static class RandomA2BUtil {
        public static int[] randomRange(int min, int max) {
            int[] range = new int[2];
            int i1 = 0;
            int i2 = 0;
            do {
                i1 = (int) (Math.random() * (max - min + 1)) + min;
                i2 = (int) (Math.random() * (max - min + 1)) + min;
            } while (i1 >= i2);

            range[0] = i1;
            range[1] = i2;
            return range;
        }
    }


    private static int f() {
        return (int)(Math.random() * 18) + 1;
    }

    private static int f1() {
        int i = f();
        return i >= 1 & i <= 9 ? 0 : 1;
    }

    private static int f2() {
        return (f1() << 5) + (f1() << 4) + (f1() << 3) + (f1() << 2) + (f1() << 1) + f1();
    }

    private static int f3() {
        int i = 0;
        do {
            i = f2();
        } while (i > 56);

        return i;
    }

    public static int f4() {
        return f3() + 43;
    }

    public static void main(String[] args) {
        int[] range = RandomA2BUtil.randomRange(0, 20);
        System.out.println("n1-m1：");
        DuiShuQiUtil.printArr(range);
        range = RandomA2BUtil.randomRange(30, 100);
        System.out.println("n2-m2：");
        DuiShuQiUtil.printArr(range);

        int[] arr = new int[100];
        for(int i = 0; i < 100_0000; i++) {
            arr[f4()]++;
        }

        for(int i = 40; i < arr.length; i++) {
            System.out.println(i + "出现了" + arr[i] + "次");
        }
    }
}
