package com.mashibing.others;

/**
 * 从a-b随机到c-d随机之题目1：
 * 有一个给定的函数f()，它能等概率的返回1-5的随机整数，要求不能利用其它任何函数只能使用f()来等概率地生成1-7范围的整数。
 *
 * 课程：课程：新手班课时13
 * 思路：见com.mashibing.preInEclipse.junior.RandomA2B注释
 */
public class RandomA2B {
    public static void main(String[] args) {
        testP1(8);
//        testP2();
    }

    /**
     * 题目给定的已知函数
     * @return
     */
    public static int f() {
        return (int)(Math.random() * 5) + 1;
    }

    /**
     * 0、1等概率发生器
     * @return
     */
    public static int f1() {
        int res = 0;
        do {
            res = f();
        }while(res == 3);

        return res < 3 ? 0 : 1;
    }

    /**
     * 1、要求的1-7范围改成0开的范围是0-6，都减去了1，即差值k=1
     * 2、分析出范围0-6的最大值6需要3个二进制位来存放，所以调用3次f2()方法
     * @return 返回值的范围是0-7
     */
    public static int f2() {
        return (f1() << 2) + (f1() << 1) + (f1() << 0);
    }

    /**
     * 将f2()返回值的范围0-7修正为0-6
     * @return
     */
    public static int f3() {
        int res = 0;
        do {
            res = f2();
        } while(res == 7);

        return res;
    }

    /**
     * 将上面的0-6范围加上差值k得到题目要求的最终范围
     * @return
     */
    public static int f4() {
        return f3() + 1;
    }

    /**
     * 等概率测试函数
     */
    public static void testP1(int arrLength) {
        int[] arr = new int[arrLength];
        for(int i = 0; i < 100_0000; i++) {
            arr[f4()]++;
        }

       for(int i = 0; i < arrLength; i++) {
           System.out.println(i + "出现了" + arr[i] + "次");
       }
    }

    public static void testP2() {
        int count = 0;
        for(int i = 0; i < 100_0000; i++) {
            if(f1() == 0) {
                count++;
            }
        }
        System.out.println("count出现的概率：" + ((double)count / (double)100_0000));
    }
}
