package com.mashibing.dailyPractice.round1._1_27;

/**
 * 已知函数f以固定但不等概率返回0和1，求g函数以相等概率返回0和1
 */
public class NotEqualP2EqualP_0127 {
    private static int f() {
        return Math.random() < 0.3 ? 0 : 1;
    }

    public static int g() {
        int result = 0;
        do {
            result = f();
        } while(result == f());// 注意：这里是相等才重做，不相等才返回

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
