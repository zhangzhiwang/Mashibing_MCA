package com.mashibing.others;

/**
 * 从a-b随机到c-d随机之题目2：
 * 有一个给定的函数f()，它能等概率的返回6-25的随机整数，要求不能利用其它任何函数只能使用f()来等概率地生成75-99范围的整数。
 */
public class RandomA2B_2 {
    public static void main(String[] args) {
        testP1(100);
//        testP2();
    }

    /**
     * 题目给定的已知函数
     * @return
     */
    public static int f() {
        return (int)(Math.random() * 20) + 6;
    }

    public static int f1() {
        return f() <=15 ? 0 : 1;
    }

    public static int f2() {
        return (f1() << 4) + (f1() << 3) +(f1() << 2) +(f1() << 1) +(f1() << 0);
    }

    public static int f3() {
        int res = 0;
        do {
            res = f2();
        } while (res >= 25 && res <= 31);
        return res;
    }

    public static int f4() {
        return f3() + 75;
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
