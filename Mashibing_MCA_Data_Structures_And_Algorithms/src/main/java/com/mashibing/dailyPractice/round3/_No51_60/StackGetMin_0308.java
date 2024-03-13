package com.mashibing.dailyPractice.round3._No51_60;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现查看栈中最小元素的功能。要求：
 * 1、pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2、设计的栈类型可以使用现成的栈结构。
 */
public class StackGetMin_0308 {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void add(int num) {
        if(dataStack.size() == 0 && minStack.size() == 0) {
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

        int r = dataStack.pop();
        minStack.pop();
        return r;
    }

    public int peek() {
        if(dataStack.isEmpty()) {
            throw new RuntimeException("栈已空");
        }

        return dataStack.peek();
    }

    public int peekMin() {
        if(dataStack.isEmpty()) {
            throw new RuntimeException("栈已空");
        }

        return minStack.peek();
    }

    public boolean isEmpty() {
        return dataStack.size() == 0;
    }

    public int size() {
        return dataStack.size();
    }

    public static void main(String[] args) {
        StackGetMin_0308 s = new StackGetMin_0308();
        s.add(1);
        s.add(2);
        s.add(0);
        s.add(4);
        s.add(5);

        System.out.println("size = " + s.size());
        System.out.println("peek = " + s.peek());
        System.out.println("peekMin = " + s.peekMin());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("------------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
        System.out.println("peek = " + s.peek());
        System.out.println("peekMin = " + s.peekMin());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("------------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
        System.out.println("peek = " + s.peek());
        System.out.println("peekMin = " + s.peekMin());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("------------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
        System.out.println("peek = " + s.peek());
        System.out.println("peekMin = " + s.peekMin());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("------------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
        System.out.println("peek = " + s.peek());
        System.out.println("peekMin = " + s.peekMin());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("------------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
//        System.out.println("peek = " + s.peek());
//        System.out.println("peekMin = " + s.peekMin());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("------------");
    }
}
