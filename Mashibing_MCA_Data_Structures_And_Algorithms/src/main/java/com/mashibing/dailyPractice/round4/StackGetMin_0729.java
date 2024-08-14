package com.mashibing.dailyPractice.round4;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现查看栈中最小元素的功能。要求：
 * 1、pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2、设计的栈类型可以使用现成的栈结构。
 */
public class StackGetMin_0729 {
    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> min = new Stack<>();

    public void add(int i) {
        if(data.isEmpty() || i <= min.peek()) {
            min.add(i);
        } else{
            min.add(min.peek());
        }
        data.add(i);
    }

    public int pop() {
        if(data.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }

        int result = data.pop();
        min.pop();
        return result;
    }

    public int peek() {
        if (data.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }

        return data.peek();
    }

    public int getMin() {
        if(data.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        return min.peek();
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }

    public int size() {
        return data.size();
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
