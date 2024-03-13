package com.mashibing.dynamic;

import java.util.Stack;

/**
 * 栈的逆序
 * 题目：给你一个栈，请你逆序这个栈，要求空间复杂度是O(1)。
 * 思路：
 * 如果要求空间复杂度是O(1)，那么在算法中就不能借助其它数据结构了，使用递归可以做到。
 * 分两个部分：
 * 第一部分：主函数，即调用递归函数recurse的函数，和之前做过的递归不一样的是本算法主函数本身也是递归
 * 第二部分：递归函数recurse，功能：移除并返回栈底元素（可以理解为pop出的是栈底元素），并将剩余元素按照原顺序下沉。
 *
 * 课程：体系班课时166
 */
public class ReverseStackUsingRecursive {
    public static void reverseStackUsingRecursive(Stack<Integer> stack) {
        if(stack == null || stack.isEmpty()) {// base case
            return;
        }

        Integer i = recurse(stack);
        reverseStackUsingRecursive(stack);
        stack.push(i);
    }

    private static Integer recurse(Stack<Integer> stack) {
        Integer result = stack.pop();
        if(stack.isEmpty()) {// base case：如果弹出栈顶元素后栈为空，那么返回栈顶元素
            return result;
        } else {
            Integer last = recurse(stack);
            stack.push(result);// 剩下元素下沉的过程
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);

//        while(!stack.isEmpty()) {
//            System.out.print(stack.pop() + "\t");
//        }
//        System.out.println();

        reverseStackUsingRecursive(stack);
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + "\t");
        }
        System.out.println();
    }
}
