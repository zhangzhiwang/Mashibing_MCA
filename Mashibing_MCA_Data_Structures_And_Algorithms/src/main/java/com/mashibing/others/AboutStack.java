package com.mashibing.others;

import java.util.Stack;

/**
 * 关于java中的栈
 */
public class AboutStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
//        stack.add(1);
//        stack.add(2);
//        stack.add(3);
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());

        /*
        java中的栈Stack虽然可以实现先进后出的功能，但是实现比较复杂，运行相对较慢，
        如果能够事先知道栈的大小，那么可以使用数组来实现栈，运行相对较快。
         */

        int testTimes = 100_0000;
        long start = System.currentTimeMillis();
        for(int i = 0; i < testTimes; i++) {
            stack.add(i);
        }

        for(int i = 0; i < testTimes; i++) {
            stack.pop();
        }
        long end = System.currentTimeMillis();
        System.out.println("stack共消耗：" + (end - start));

        int[] stack2 = new int[testTimes];
        start = System.currentTimeMillis();
        for(int i = 0; i < testTimes; i++) {
            stack2[i] = i;
        }

        for(int i = testTimes - 1; i >= 0; i--) {
            int a = stack2[i];
        }
        end = System.currentTimeMillis();
        System.out.println("数组共消耗：" + (end - start));
    }
}
