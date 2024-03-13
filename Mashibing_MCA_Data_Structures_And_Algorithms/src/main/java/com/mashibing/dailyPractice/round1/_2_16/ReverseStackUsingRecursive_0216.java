package com.mashibing.dailyPractice.round1._2_16;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，要求空间复杂度是O(1)
 */
public class ReverseStackUsingRecursive_0216 {
    public static void reverseStackUsingRecursive(Stack<String> stack) {
        if(stack == null || stack.isEmpty()) {
            return;
        }

        String s = recurse(stack);
        reverseStackUsingRecursive(stack);
        stack.add(s);
    }

    private static String recurse(Stack<String> stack) {
        String pop = stack.pop();
        if(stack.isEmpty()) {
            return pop;
        }

        String result = recurse(stack);
        stack.add(pop);
        return result;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("a");
        stack.add("b");
        stack.add("c");

        reverseStackUsingRecursive(stack);
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
