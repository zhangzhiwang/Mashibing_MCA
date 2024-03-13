package com.mashibing.dailyPractice.round1._1_25;

/**
 * 计算：1!+2!+3!+……+n!
 */
public class JieCheng_0125 {
    public static int jieCheng(int n) {
        if(n <= 0) {
            throw new RuntimeException("参数有误！");
        }
        int preJieCheng = 1;
        int sum = 1;
        for(int i = 2; i <= n; i++) {
            preJieCheng *= i;
            sum += preJieCheng;
        }

        return sum;
    }

    public static void main(String[] args) {
        int sum = jieCheng(3);
        System.out.println(sum);
    }
}
