package com.mashibing.others;

/**
 * 打印int整数的各个二进制位
 * 课程：新手班课时1
 * 思路：见com.mashibing.preInEclipse.junior.PrintIntBinary和com.mashibing.preInEclipse.junior.ByteOper注释
 */
public class PrintIntBinary {
    public static void main(String[] args) {
        int i = Integer.MIN_VALUE;
        printIntBinary(i);
    }

    public static void printIntBinary(int num) {
        for(int i = 31; i >= 0; i--) {
            System.out.print(((1 << i) & num) == 0 ? "0" : "1");
        }
    }
}
