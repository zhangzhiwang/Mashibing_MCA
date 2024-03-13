package com.mashibing.dailyPractice.round3._No11_20;

/**
 * 有一个给定的函数f()，它能等概率的返回5-21的随机整数，要求不能利用其它任何函数只能使用f()函数来等概率地生成32-66范围的整数
 */
public class RandomA2B_0305 {
    public static int f() {
        return (int)(Math.random() * 17) + 5;
    }

    private static int m1() {
        int result = 0;
        do {
            result = f();
        } while (result == 13);

        return result < 13 ? 0 : 1;
    }

    private static int m2() {
        return (m1() << 5) + (m1() << 4) + (m1() << 3) + (m1() << 2) + (m1() << 1) + (m1() << 0);
    }

    private static int m3() {
        int result = 0;
        do {
            result = m2();
        } while (result > 34);

        return result;
    }

    public static int g() {
        return m3() + 32;
    }

    public static void main(String[] args) {
        int[] arr = new int[68];
        for(int i = 0; i < 100_0000; i++) {
            arr[g()]++;
        }

        for(int i = 30; i < arr.length; i++) {
            System.out.println(i + "出现了" + arr[i] + "次");
        }
    }
}
