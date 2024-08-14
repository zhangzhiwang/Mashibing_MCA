package com.mashibing.dailyPractice.round4;

/**
 * 已知函数f以固定但不等概率返回0和1，求g函数以相等概率返回0和1。
 */
public class NotEqualP2EqualP_0721 {
    private static int f() {
        return Math.random() < 0.3 ? 1 : 0;
    }

    public static int g() {
        int i = 0;
        do {
            i = f();
        } while (i == f());

        return i;
    }

    public static void main(String[] args) {
        int totalTimes = 100_0000;
        int count = 0;
        for(int i = 0; i < totalTimes; i++) {
            if(g() == 0) {
                count++;
            }
        }
        System.out.println((double)count / (double)totalTimes);
    }
}
