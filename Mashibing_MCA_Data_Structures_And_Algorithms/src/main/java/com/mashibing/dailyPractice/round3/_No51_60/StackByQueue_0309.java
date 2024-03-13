package com.mashibing.dailyPractice.round3._No51_60;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列（非双端队列）实现栈
 */
public class StackByQueue_0309 {
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

        int r = q1.poll();
        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;

        return r;
    }

    public int peek() {
        if(q1.isEmpty()) {
            throw new RuntimeException("栈已空");
        }

        while(q1.size() > 1) {
            q2.add(q1.poll());
        }

        int r = q1.peek();
        q2.add(q1.poll());
        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;

        return r;
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }

    public int size() {
        return q1.size();
    }

    public static void main(String[] args) {
        StackByQueue_0309 s = new StackByQueue_0309();
        s.add(1);
        s.add(2);
        s.add(3);

        System.out.println("size = " + s.size());
        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("------------------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("------------------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("------------------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
//        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("------------------");
    }
}