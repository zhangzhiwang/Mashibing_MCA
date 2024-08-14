package com.mashibing.dailyPractice.round4.secondPractice;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，要求空间复杂度是O(1)
 */
public class ReverseStackUsingRecursive_0802 {
    public static void reverseStackUsingRecursive(Stack<Integer> stack) {
        if(stack == null || stack.isEmpty()) {
            return;
        }

        int i = recurse(stack);
        reverseStackUsingRecursive(stack);
        stack.add(i);
    }

    private static Integer recurse(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if(stack.isEmpty()) {
            return pop;
        }

        int bottom = recurse(stack);
        stack.add(pop);
        return bottom;
    }

    // 老师的代码
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    // 栈底元素移除掉
    // 上面的元素盖下来
    // 返回移除掉的栈底元素
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);

//        while (!stack.isEmpty()) {
//            System.out.print(stack.pop() + "\t");
//        }
//        System.out.println("correct ans:");
//        reverse(stack);
//        while (!stack.isEmpty()) {
//            System.out.print(stack.pop() + "\t");
//        }
        System.out.println("my ans:");
        reverseStackUsingRecursive(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "\t");
        }
    }
}
