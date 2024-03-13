package com.mashibing.dailyPractice.round1._1_25;

/**
 * 打印一个int整数的二进制表示形式
 */
public class PrintIntBinary_0125 {
    public static void printIntBinary(int num) {
        for(int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? 0 : 1);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printIntBinary(3);
    }
}
