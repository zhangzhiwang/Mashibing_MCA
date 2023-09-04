package com.mashibing.others;

/**
 * 0、1不等概率随机到0、1等概率随机
 * 题目：有一个已知函数f以固定概率P返回0，以固定概率1-P返回1，且P!=1-P，即函数f以固定但不相等的概率返回0和1。在只使用f函数的情况下，如何设计一个函数g使得g以等概率返回0和1（返回0或1的概率各50%）。
 * 课程：新手班课时14
 * 思路：见com.mashibing.preInEclipse.junior.array.NotEqualP2EqualP注释
 */
public class NotEqualP2EqualP {
    public static void main(String[] args) {
        testP2();
    }

    /**
     * 题目已知函数，该函数以30%的概率返回0，70%的概率返回1
     * @return
     */
    public static int f() {
        if(Math.random() <= 0.3) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int g() {
        int res = 0;
        do {
            res = f();
        } while(res == f());

        return res;
    }

    /**
     * 测试方法
     */
    public static void testP2() {
        int count = 0;
        for(int i = 0; i < 100_0000; i++) {
            if(g() == 0) {
                count++;
            }
        }
        System.out.println("count出现的概率：" + ((double)count / (double)100_0000));
    }
}
