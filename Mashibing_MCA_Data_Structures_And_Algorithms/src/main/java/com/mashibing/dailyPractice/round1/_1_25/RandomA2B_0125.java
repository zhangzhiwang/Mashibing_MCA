package com.mashibing.dailyPractice.round1._1_25;

/**
 * 从a-b随机到c-d随机：有一个给定的函数f()，它能等概率的返回5-20的随机整数，要求不能利用其它任何函数只能使用f()函数来等概率地生成31-66范围的整数。
 */
public class RandomA2B_0125 {
    private static int f() {
        return (int)(Math.random() * 16) + 5;
    }

    private static int f1() {
        int i = f();
        if(i >= 5 && i <= 12) {
            return 0;
        } else {
            return 1;
        }
    }

    private static int f2() {
        return (f1() << 5) + (f1() << 4) + (f1() << 3) + (f1() << 2) + (f1() << 1) + (f1() << 0);
    }

    private static int f3() {
        int result = -1;
        do {
            result = f2();
        } while (result > 35);

        return result;
    }

    private static int f4() {
        return f3() + 31;
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
