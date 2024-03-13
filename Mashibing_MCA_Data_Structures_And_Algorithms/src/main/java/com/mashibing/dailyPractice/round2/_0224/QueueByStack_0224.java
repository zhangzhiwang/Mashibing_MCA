package com.mashibing.dailyPractice.round2._0224;

import java.util.Stack;

/**
 * 使用栈实现队列（非双端队列）
 */
public class QueueByStack_0224 {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public void add(int num) {
        dataStack.add(num);
    }

    public int pop() {
        if (popStack.isEmpty()) {
            while (!dataStack.isEmpty()) {
                popStack.add(dataStack.pop());
            }
            if (popStack.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
        }

        return popStack.pop();
    }

    public int peek() {
        if (popStack.isEmpty()) {
            while (!dataStack.isEmpty()) {
                popStack.add(dataStack.pop());
            }
            if (popStack.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
        }

        return popStack.peek();
    }

    public boolean isEmpty() {
        return dataStack.isEmpty() && popStack.isEmpty();
    }

    public int size() {
        return dataStack.size() + popStack.size();
    }

    public static void main(String[] args) {
        QueueByStack_0224 queue = new QueueByStack_0224();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("size = " + queue.size());
        System.out.println("------------");

        queue.add(4);
        System.out.println("size = " + queue.size());
        System.out.println("------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("size = " + queue.size());
        System.out.println("------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("size = " + queue.size());
        System.out.println("------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("size = " + queue.size());
        System.out.println("------------");

        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.isEmpty());
        System.out.println(queue.dataStack.size());
        System.out.println(queue.popStack.size());
        System.out.println("peek = " + queue.peek());
        System.out.println(queue.dataStack.size());
        System.out.println(queue.popStack.size());
        System.out.println(queue.isEmpty());
        queue.add(4);
        System.out.println(queue.isEmpty());
        System.out.println(queue.dataStack.size());
        System.out.println(queue.popStack.size());
        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();
        System.out.println(queue.dataStack.size());
        System.out.println(queue.popStack.size());
        System.out.println(queue.isEmpty());
    }
}
