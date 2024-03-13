package com.mashibing.dailyPractice.round2._0219;

/**
 * 打印一个int整数的二进制表示形式
 */
public class PrintIntBinary_0219 {
    public static void printIntBinary(int i) {
        for(int j = 31; j >= 0; j--) {
            System.out.print((i & (1 << j)) == 0 ? 0 : 1);
        }
    }

    public static void main(String[] args) {
        printIntBinary(5);
    }
}
