package com.mashibing.dailyPractice.round2._0223;

import com.mashibing.bit.PrintIntBinary;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 给定一个数a的二进制形式，比如：0011 0010 1110 1000，找出并返回该二进制数的最后一个1，返回形式为：0000 0000 0000 1000。
 */
public class AND1_0223 {
    public static int and1(int i) {
        return i & (-i);
    }

    public static void main(String[] args) {
        int i = 5;
//        PrintIntBinary.printIntBinary(i);
//        PrintIntBinary.printIntBinary(and1(i));
        System.out.println(~i);
        System.out.println(-i);
    }
}
