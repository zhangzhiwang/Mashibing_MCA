package com.mashibing.dailyPractice.round5;

import com.mashibing.dailyPractice.round4.RandomA2B_0721;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 有一个给定的函数f()，它能等概率的返回5-20（n1-m1）的随机整数，要求不能利用其它任何函数只能使用f()函数来等概率地生成31-66（n2-m2）范围的整数
 */
public class RandomA2B_0826 {
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

    /**
     * 等概率返回[0,8]，先跑一遍RandomA2BUtil.randomRange()
     * @return
     */
    private static int f() {
        return (int)(Math.random() * 9);
    }

    private static int f1() {
        int i = 0;
        do {
            i = f() < 4 ? 0 : 1;
        } while (i == 4);

        return i;
    }

    /**
     * 等概率返回[80,81]，先跑一遍RandomA2BUtil.randomRange()
     * 由于本题先跑的RandomA2BUtil.randomRange()的结果比较特殊：[n1,m1]为[0,8]，[n2,m2]为[80,81]，所以"神奇四侠"两个函数就可以搞定
     * @return
     */
    private static int f2() {
        return f1() + 80;
    }

    public static void main(String[] args) {
//        int[] range = RandomA2BUtil.randomRange(0, 20);
//        System.out.println("n1-m1：");
//        DuiShuQiUtil.printArr(range);
//        range = RandomA2BUtil.randomRange(30, 100);
//        System.out.println("n2-m2：");
//        DuiShuQiUtil.printArr(range);

        int[] arr = new int[100];
        for(int i = 0; i < 100_0000; i++) {
            arr[f2()]++;
        }

        for(int i = 77; i < arr.length; i++) {
            System.out.println(i + "出现了" + arr[i] + "次");
        }
    }
}
