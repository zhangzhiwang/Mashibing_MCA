package com.mashibing.dailyPractice.round4;

import com.mashibing.others.DuiShuQiUtil;
import com.mashibing.preInEclipse.junior.PrintIntBinary;

/**
 * 给定一个数a的二进制形式，比如：0011 0010 1110 1000，找出并返回该二进制数的最后一个1，返回形式为：0000 0000 0000 1000。
 */
public class AND1_0729 {
    public static void and1(int i) {
        PrintIntBinary_0719.printBinary(i & (-i));
    }

    public static void main(String[] args) {
        int i = 1025;
        PrintIntBinary.printIntBinary(i);
        and1(i);
    }
}
