package com.mashibing.dailyPractice.round3._No1_10;

/**
 * 计算：1!+2!+3!+……+n!
 */
public class JieCheng_0304 {
    public static int jieCheng(int n) {
        if(n <= 0) {
            throw new RuntimeException("参数有误");
        }

        int sum = 0;
        int jieCheng = 1;
        for(int i = 1; i <= n; i++) {
            jieCheng *= i;
            sum += jieCheng;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(jieCheng(5));
    }
}
