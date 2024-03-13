package com.mashibing.dailyPractice.round1._2_2;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现查看栈中最小元素的功能。要求：
 * 1、pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2、设计的栈类型可以使用现成的栈结构。
 */
public class StackGetMin_0202 {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void add(int num) {
        if(dataStack.isEmpty() || num < minStack.peek()) {
            minStack.push(num);
        } else {
            minStack.push(minStack.peek());
        }

        dataStack.add(num);
    }

    public int pop() {
        if(dataStack.isEmpty()) {
            throw new RuntimeException("栈为空");
        }

        Integer result = dataStack.pop();
        minStack.pop();
        return result;
    }

    public int peek() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("栈为空");
        }

        return dataStack.peek();
    }

    public boolean isEmpty() {
        return dataStack.isEmpty();
    }

    public int size() {
        return dataStack.size();
    }

    public int getMin() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("栈为空");
        }

        return minStack.peek();
    }

    public static void main(String[] args) {
        StackGetMin_0202 stack = new StackGetMin_0202();
        stack.add(3);
        stack.add(10);
        stack.add(11);
        stack.add(2);
        stack.add(20);
        stack.add(23);
        stack.add(1);
        stack.add(19);

        System.out.println("min = " + stack.getMin());
        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        System.out.println("min = " + stack.getMin());
        System.out.println("peek = " + stack.peek());
        System.out.println("empty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
    }
}
