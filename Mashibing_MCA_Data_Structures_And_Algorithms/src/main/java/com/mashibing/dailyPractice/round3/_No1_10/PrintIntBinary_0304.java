package com.mashibing.dailyPractice.round3._No1_10;

/**
 * 打印一个int整数的二进制表示形式
 */
public class PrintIntBinary_0304 {
    public static void printIntBinary(int num) {
        for(int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? 0 : 1);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printIntBinary(5);
    }
}
