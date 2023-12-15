package com.mashibing.bit;

/**
 * 给定一个数a的二进制形式，比如：0011 0010 1110 1000，找出并返回该二进制数的最后一个1，返回形式为：0000 0000 0000 1000
 * 课程：体系班课时15
 * 思路：见com.mashibing.preInEclipse.senior.bit.AND1注释
 */
public class AND1 {
    public static void main(String[] args) {
//        int i = 13032;
//        PrintIntBinary.printIntBinary(i);
//        int i1 = findLastOneBinary(i);
//        PrintIntBinary.printIntBinary(i1);

        // 测试优先级
        int a = 100;
        int b = ~a + 1;
        int c = (~a) + 1;
        System.out.println(b);
        System.out.println(c);
    }

    public static int findLastOneBinary(int i) {
        return i & (~i + 1);
//        return i & (-i);
    }
}
