package com.mashibing.dailyPractice.round3._No11_20;

/**
 * 已知函数f以固定但不等概率返回0和1，求g函数以相等概率返回0和1。
 */
public class NotEqualP2EqualP_0305 {
    public static int f() {
        return Math.random() <= 0.8 ? 0 : 1;
    }

    public static int g() {
        int result = 0;
        do {
            result = f();
        } while(result == f());
        return result;
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
