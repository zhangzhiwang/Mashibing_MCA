package com.mashibing.dailyPractice.round2._0221;

/**
 * 从a-b随机到c-d随机
 * 比如：有一个给定的函数f()，它能等概率的返回5-20的随机整数，要求不能利用其它任何函数只能使用f()函数来等概率地生成31-66范围的整数
 */
public class RandomA2B_0221 {
    public static int f() {
        return (int) (Math.random() * 16) + 5;
    }

    private static int m1() {
        int i = f();
        return i >= 5 && i <= 12 ? 0 : 1;
    }

    private static int m2() {
        return (m1() << 5) + (m1() << 4) + (m1() << 3) + (m1() << 2) + (m1() << 1) + (m1() << 0);
    }

    private static int m3() {
        int i = 0;
        do {
            i = m2();
        } while(i >= 36 && i <= 63);
        return i;
    }

    public static int f4() {
        return m3() + 31;
    }

    public static void main(String[] args) {
        int[] arr = new int[68];
        for(int i = 0; i < 100_0000; i++) {
            arr[f4()]++;
        }

        for(int i = 30; i < arr.length; i++) {
            System.out.println(i + "出现了" + arr[i] + "次");
        }
    }
}
