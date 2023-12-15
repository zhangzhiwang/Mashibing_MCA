package com.mashibing.stack;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能。要求：
 * 1、pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2、设计的栈类型可以使用现成的栈结构。
 * 课程：体系班课时23
 * 思路：见com.mashibing.preInEclipse.senior.stack.StackGetMin注释
 */
public class StackGetMin {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();// 保存最小值的栈

    public void add(int num) {
        dataStack.push(num);
        if(minStack.isEmpty()) {
            minStack.push(num);
        } else if(num <= minStack.peek()) {
            minStack.push(num);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public int pop() {
        minStack.pop();
        return dataStack.pop();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        /*
        3   4   5   2   6   1
        3   3   3   2   2   1
         */
        StackGetMin sgm = new StackGetMin();
        sgm.add(3);
        sgm.add(4);
        sgm.add(5);
        sgm.add(2);
        sgm.add(6);
        sgm.add(1);
        System.out.println(sgm.getMin());
        System.out.println("------------");

        int result = sgm.pop();
        System.out.println("result = " + result);
        System.out.println(sgm.getMin());
        System.out.println("------------");

        result = sgm.pop();
        System.out.println("result = " + result);
        System.out.println(sgm.getMin());
        System.out.println("------------");

        result = sgm.pop();
        System.out.println("result = " + result);
        System.out.println(sgm.getMin());
        System.out.println("------------");

        result = sgm.pop();
        System.out.println("result = " + result);
        System.out.println(sgm.getMin());
        System.out.println("------------");

        result = sgm.pop();
        System.out.println("result = " + result);
        System.out.println(sgm.getMin());
        System.out.println("------------");

        result = sgm.pop();
        System.out.println("result = " + result);
        System.out.println(sgm.getMin());
        System.out.println("------------");
    }
}
