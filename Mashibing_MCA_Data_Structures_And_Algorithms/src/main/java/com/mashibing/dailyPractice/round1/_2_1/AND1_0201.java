package com.mashibing.dailyPractice.round1._2_1;

import com.mashibing.bit.PrintIntBinary;

/**
 * 给定一个数a的二进制形式，比如：0011 0010 1110 1000，找出并返回该二进制数的最后一个1，返回形式为：0000 0000 0000 1000。
 */
public class AND1_0201 {
    public static void and1(int a) {
        PrintIntBinary.printIntBinary(a & (-a));
    }

    public static void main(String[] args) {
        and1(13032);
    }
}
