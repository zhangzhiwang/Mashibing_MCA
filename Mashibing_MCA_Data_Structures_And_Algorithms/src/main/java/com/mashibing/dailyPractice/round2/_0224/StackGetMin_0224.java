package com.mashibing.dailyPractice.round2._0224;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现查看栈中最小元素的功能。要求：
 * 1、pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2、设计的栈类型可以使用现成的栈结构。
 */
public class StackGetMin_0224 {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void add(int num) {
        if(dataStack.isEmpty()) {
            minStack.add(num);
        } else {
            if(num <= minStack.peek()) {
                minStack.add(num);
            } else {
                minStack.add(minStack.peek());
            }
        }
        dataStack.add(num);
    }

    public int pop() {
        if(dataStack.isEmpty()) {
            throw new RuntimeException("栈已空");
        }

        int result = dataStack.pop();
        minStack.pop();
        return result;
    }

    public int peek() {
        if(dataStack.isEmpty()) {
            throw new RuntimeException("栈已空");
        }

        return dataStack.peek();
    }

    public int getMin() {
        if(dataStack.isEmpty()) {
            throw new RuntimeException("栈已空");
        }

        return minStack.peek();
    }

    public boolean isEmpty() {
        return dataStack.isEmpty();
    }

    public int size() {
        return dataStack.size();
    }

    public static void main(String[] args) {
//        Stack<Integer> stack = new Stack<>();
//        stack.add(1);
//        stack.add(2);
//        stack.add(3);

//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//
//        while(!stack.isEmpty()) {
//            System.out.print(stack.pop() + "\t");
//        }

        StackGetMin_0224 stack = new StackGetMin_0224();
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(1);
        stack.add(0);
        stack.add(6);
        stack.add(7);

//        System.out.println(stack.dataStack.size() == stack.minStack.size());
//        while(!stack.isEmpty()) {
//            System.out.print(stack.pop() + "\t");
//            System.out.print(stack.dataStack.pop() + "\t");
//            System.out.print(stack.minStack.pop() + "\t");
//        }
//        System.out.println();

        System.out.println("getMin:" + stack.getMin());
        System.out.println("peek:" + stack.peek());
        System.out.println("pop = " + stack.pop());
        System.out.println("getMin:" + stack.getMin());
        System.out.println("pop = " + stack.pop());
        System.out.println("getMin:" + stack.getMin());
        System.out.println("pop = " + stack.pop());
        System.out.println("getMin:" + stack.getMin());
        System.out.println("pop = " + stack.pop());
        System.out.println("getMin:" + stack.getMin());
        System.out.println("pop = " + stack.pop());
        System.out.println("getMin:" + stack.getMin());
        System.out.println("pop = " + stack.pop());
        System.out.println("getMin:" + stack.getMin());
        System.out.println("pop = " + stack.pop());
        System.out.println("getMin:" + stack.getMin());
    }
}
