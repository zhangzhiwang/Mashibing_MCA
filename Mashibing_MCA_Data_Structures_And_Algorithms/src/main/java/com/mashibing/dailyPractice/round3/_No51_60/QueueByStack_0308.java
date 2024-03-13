package com.mashibing.dailyPractice.round3._No51_60;

import java.util.Stack;

/**
 * 使用栈实现队列（非双端队列）
 */
public class QueueByStack_0308 {
    private Stack<Integer> addStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public void add(int num) {
        addStack.add(num);
    }

    public int pop() {
        if(addStack.isEmpty() && popStack.isEmpty()) {
            throw new RuntimeException("队列已空");
        }

        if(popStack.isEmpty()) {
            while(!addStack.isEmpty()) {
                popStack.add(addStack.pop());
            }
        }

        return popStack.pop();
    }

    public int peek() {
        if(addStack.isEmpty() && popStack.isEmpty()) {
            throw new RuntimeException("队列已空");
        }

        if(popStack.isEmpty()) {
            while(!addStack.isEmpty()) {
                popStack.add(addStack.pop());
            }
        }

        return popStack.peek();
    }

    public boolean isEmpty() {
        return addStack.isEmpty() && popStack.isEmpty();
    }

    public int size() {
        return addStack.size() + popStack.size();
    }

    public static void main(String[] args) {
        QueueByStack_0308 q = new QueueByStack_0308();
        q.add(1);
        q.add(2);
        q.add(3);

        System.out.println("size = " + q.size());
        System.out.println("peek = " + q.peek());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("-----------");

        System.out.println("pop = " + q.pop());
        System.out.println("size = " + q.size());
        System.out.println("peek = " + q.peek());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("-----------");

        System.out.println("pop = " + q.pop());
        System.out.println("size = " + q.size());
        System.out.println("peek = " + q.peek());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("-----------");

        q.add(6);
        System.out.println("size = " + q.size());
        System.out.println("peek = " + q.peek());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("-----------");

        System.out.println("pop = " + q.pop());
        System.out.println("size = " + q.size());
        System.out.println("peek = " + q.peek());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("-----------");

        System.out.println("pop = " + q.pop());
        System.out.println("size = " + q.size());
//        System.out.println("peek = " + q.peek());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("-----------");
    }
}