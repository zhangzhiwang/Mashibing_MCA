package com.mashibing.others;

/**
 * 计算：1!+2!+3!+……+n!
 * 课程：新手班课时4
 * 思路：见com.mashibing.preInEclipse.junior.JieCheng注释
 */
public class JieCheng {
    public static void main(String[] args) {
        int result = jieCheng(5);
        System.out.println("result = " + result);
    }

    /**
     * 
     * @param n 一直累加到n的阶乘
     * @return
     */
    public static int jieCheng(int n) {
        int tmp = 1;
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            tmp *= i;
            sum += tmp;
        }
        
        return sum;
    }
}
