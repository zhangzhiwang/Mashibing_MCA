package com.mashibing.dailyPractice.round4;

/**
 * 打印一个int整数的二进制表示形式
 * 位运算特性：
 * 1、与1与还原该位本身
 * 2、
 */
public class PrintIntBinary_0719 {
    public static void printBinary(int i) {
        for (int j = 31; j >= 0; j--) {
            System.out.print((i & (1 << j)) == 0 ? 0 : 1);
        }
    }

    // 老师的代码
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        System.out.println("correct ans:");
        print(13579);
        System.out.println("my ans:");
        printBinary(13579);
    }
}
