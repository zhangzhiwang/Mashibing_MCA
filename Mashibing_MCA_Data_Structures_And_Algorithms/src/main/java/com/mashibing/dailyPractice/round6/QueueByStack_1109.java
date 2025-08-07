package com.mashibing.dailyPractice.round6;

import com.mashibing.dailyPractice.round5.QueueByStack_0819;

import java.util.Stack;

/**
 * 使用栈实现队列（非双端队列）
 */
public class QueueByStack_1109 {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public void add(int num) {
        dataStack.add(num);
    }

    public int pop() {
        if(dataStack.isEmpty() && popStack.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }

        if(popStack.isEmpty()) {
            while (!dataStack.isEmpty()) {
                popStack.add(dataStack.pop());
            }
        }

        return popStack.pop();
    }

    public int peek() {
        if(dataStack.isEmpty() && popStack.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }

        if(popStack.isEmpty()) {
            while (!dataStack.isEmpty()) {
                popStack.add(dataStack.pop());
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
        QueueByStack_1109 queue = new QueueByStack_1109();
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("-------------");
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

//        System.out.println("peek = " + queue.peek());
//        System.out.println("size = " + queue.size());
//        while (!queue.isEmpty()) {
//            System.out.println(queue.pop());
//        }

//        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
//        System.out.println("isEmpty = " + queue.isEmpty());
//        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
//        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
//        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        queue.add(6);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        queue.add(7);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        queue.add(8);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
//        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.dataStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");
    }
}
