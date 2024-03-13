package com.mashibing.dailyPractice.round2._0304;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，要求空间复杂度是O(1)
 */
public class ReverseStackUsingRecursive2_0304 {
    public static void reverseStackUsingRecursive2(Stack<Integer> stack) {
        if(stack == null || stack.isEmpty()) {
            return;
        }

        int i = recurse(stack);
        reverseStackUsingRecursive2(stack);
        stack.add(i);
    }

    private static int recurse(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if(stack.isEmpty()) {
            return pop;
        }

        int result = recurse(stack);
        stack.add(pop);
        return result;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);

//        while (!stack.isEmpty()) {
//            System.out.print(stack.pop() + "\t");
//        }
//        System.out.println();

        reverseStackUsingRecursive2(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "\t");
        }
        System.out.println();
    }
}
