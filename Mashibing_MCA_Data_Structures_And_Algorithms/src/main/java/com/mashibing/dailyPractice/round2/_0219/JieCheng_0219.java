package com.mashibing.dailyPractice.round2._0219;

/**
 * 计算：1!+2!+3!+……+n!
 */
public class JieCheng_0219 {
    public static int jieCheng(int n) {
        int jieCheng = 1;
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            jieCheng *= i;
            sum += jieCheng;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(jieCheng(4));
    }
}
