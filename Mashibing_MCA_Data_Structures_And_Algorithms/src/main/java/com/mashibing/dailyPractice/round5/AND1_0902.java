package com.mashibing.dailyPractice.round5;

import com.mashibing.preInEclipse.junior.PrintIntBinary;

/**
 * 给定一个数a的二进制形式，比如：0011 0010 1110 1000，找出并返回该二进制数的最后一个1，返回形式为：0000 0000 0000 1000。
 */
public class AND1_0902 {
    public static int and1(int a) {
        return a & (-a);
    }

    public static void main(String[] args) {
        int i = -80;
        PrintIntBinary.printIntBinary(i);
        PrintIntBinary.printIntBinary(and1(i));
    }
}
