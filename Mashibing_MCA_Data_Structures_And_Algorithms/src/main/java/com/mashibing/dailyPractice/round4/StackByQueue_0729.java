package com.mashibing.dailyPractice.round4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列（非双端队列）实现栈
 */
public class StackByQueue_0729 {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    public void add(int i) {
        q1.add(i);
    }

    public int pop() {
        if (q1.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }

        while (q1.size() > 1) {
            q2.add(q1.poll());
        }

        int result = q1.poll();
        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;
        return result;
    }

    public int peek() {
        if (q1.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }

        while (q1.size() > 1) {
            q2.add(q1.poll());
        }

        int result = q1.poll();
        q2.add(result);
        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;
        return result;
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }

    public int size() {
        return q1.size();
    }

    public static void main(String[] args) {
        StackByQueue_0729 stack = new StackByQueue_0729();
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("-------------");
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);

        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        stack.add(6);
        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        stack.add(7);
        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        stack.add(8);
        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

//        System.out.println("pop = " + stack.pop());
//        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
//        System.out.println("isEmpty = " + stack.isEmpty());
//        System.out.println("size = " + stack.size());
//        System.out.println("peek = " + stack.peek());
//        System.out.println("-------------");
//
//        System.out.println("pop = " + stack.pop());
//        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
//        System.out.println("isEmpty = " + stack.isEmpty());
//        System.out.println("size = " + stack.size());
//        System.out.println("peek = " + stack.peek());
//        System.out.println("-------------");
//
//        System.out.println("pop = " + stack.pop());
//        System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
//        System.out.println("isEmpty = " + stack.isEmpty());
//        System.out.println("size = " + stack.size());
//        System.out.println("peek = " + stack.peek());
//        System.out.println("-------------");

        while (!stack.isEmpty()) {
            System.out.println("pop = " + stack.pop());
            System.out.println("q1.size = " + stack.q1.size() + ",q2.size = " + stack.q2.size());
            System.out.println("isEmpty = " + stack.isEmpty());
            System.out.println("size = " + stack.size());
//            System.out.println("peek = " + stack.peek());
            System.out.println("-------------");
        }
    }
}
