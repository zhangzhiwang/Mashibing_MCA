package com.mashibing.dailyPractice.round4;

/**
 * 计算：1!+2!+3!+……+n!
 */
public class JieCheng_0719 {
    public static void jieCheng(int n) {
        if(n <= 0) {
            return;
        }

        int pre = 1;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            pre *= i;
            sum += pre;
        }

        System.out.println(sum);
    }

    // 老师的代码
    public static long f1(int N) {
        long ans = 0;
        for (int i = 1; i <= N; i++) {
            ans += factorial(i);
        }
        return ans;
    }

    public static long factorial(int N) {
        long ans = 1;
        for (int i = 1; i <= N; i++) {
            ans *= i;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("correct ans:" + f1(5));
        System.out.println("my ans:");
        jieCheng(5);
    }
}
