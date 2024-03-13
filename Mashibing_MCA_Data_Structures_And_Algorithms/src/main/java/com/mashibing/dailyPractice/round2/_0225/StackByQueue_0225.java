package com.mashibing.dailyPractice.round2._0225;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列（非双端队列）实现栈
 */
public class StackByQueue_0225 {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    public void add(int num) {
        q1.add(num);
    }

    public int pop() {
        if(q1.isEmpty()) {
            throw new RuntimeException("栈已空");
        }

        while(q1.size() > 1) {
            q2.add(q1.poll());
        }

        int result = q1.poll();

        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;
        return result;
    }

    public int peek() {
        if(q1.isEmpty()) {
            throw new RuntimeException("栈已空");
        }

        while(q1.size() > 1) {
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
        return q1.size() == 0;
    }

    public int size() {
        return q1.size();
    }

    public static void main(String[] args) {
        StackByQueue_0225 stack = new StackByQueue_0225();
        stack.add(1);
        stack.add(2);
        stack.add(3);

        System.out.println("size = " + stack.size());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println("size = " + stack.size());
        System.out.println("------------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("size = " + stack.size());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println("size = " + stack.size());
        System.out.println("------------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("size = " + stack.size());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println("size = " + stack.size());
        System.out.println("------------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("size = " + stack.size());
        System.out.println(stack.isEmpty());
//        System.out.println(stack.peek());
        System.out.println("size = " + stack.size());
        System.out.println("------------------");
    }
}