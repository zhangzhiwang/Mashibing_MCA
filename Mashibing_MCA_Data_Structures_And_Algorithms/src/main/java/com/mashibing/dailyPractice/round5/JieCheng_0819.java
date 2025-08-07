package com.mashibing.dailyPractice.round5;

/**
 * 计算：1!+2!+3!+……+n!
 */
public class JieCheng_0819 {
    public static int jieCheng(int n) {
        if(n <= 0) {
            return -1;
        }

        int t = 1;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            t *= i;
            sum += t;
        }

        return sum;
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
        System.out.println("my ans:" + jieCheng(5));
    }
}
