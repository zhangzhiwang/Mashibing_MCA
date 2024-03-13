package com.mashibing.dailyPractice.round3._No61_70;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，要求空间复杂度是O(1)
 */
public class ReverseStackUsingRecursive_0313 {
    public static void reverseStackUsingRecursive(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }

        int i = recurse(stack);
        reverseStackUsingRecursive(stack);
        stack.add(i);
    }

    private static int recurse(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            int r = recurse(stack);
            stack.add(pop);
            return r;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);

        reverseStackUsingRecursive(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "\t");
        }
        System.out.println();
    }
}
