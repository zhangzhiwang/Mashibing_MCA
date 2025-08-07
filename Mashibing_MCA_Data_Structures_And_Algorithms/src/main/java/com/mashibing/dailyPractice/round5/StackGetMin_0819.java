package com.mashibing.dailyPractice.round5;

import com.mashibing.dailyPractice.round4.StackGetMin_0729;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现查看栈中最小元素的功能。要求：
 * 1、pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2、设计的栈类型可以使用现成的栈结构。
 */
public class StackGetMin_0819 {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void add(int num) {
        if (dataStack.isEmpty()) {
            minStack.add(num);
        } else {
            if (minStack.peek() <= num) {
                minStack.add(minStack.peek());
            } else {
                minStack.add(num);
            }
        }
        dataStack.add(num);
    }

    public int pop() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }

        minStack.pop();
        return dataStack.pop();
    }

    public int peek() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
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
            throw new RuntimeException("Stack is empty!");
        }

        return minStack.peek();
    }

    public static void main(String[] args) {
        StackGetMin_0729 stack = new StackGetMin_0729();
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("-------------");

        stack.add(5);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("min = " + stack.getMin());
        System.out.println("-------------");

        stack.add(6);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("min = " + stack.getMin());
        System.out.println("-------------");

        stack.add(7);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("min = " + stack.getMin());
        System.out.println("-------------");

        stack.add(3);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("min = " + stack.getMin());
        System.out.println("-------------");

        stack.add(8);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("min = " + stack.getMin());
        System.out.println("-------------");

        stack.add(1);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("min = " + stack.getMin());
        System.out.println("-------------");

        System.out.println("pop:");
        while (!stack.isEmpty()) {
            System.out.println("pop = " + stack.pop());
            System.out.println("isEmpty = " + stack.isEmpty());
            System.out.println("size = " + stack.size());
//            System.out.println("peek = " + stack.peek());
//            System.out.println("min = " + stack.getMin());
            System.out.println("-------------");
        }
//        System.out.println("pop = " + stack.pop());
    }
}
