package com.mashibing.dailyPractice.round4;

import com.mashibing.others.DuiShuQiUtil;

import java.util.Stack;

/**
 * 使用栈实现队列（非双端队列）
 */
public class QueueByStack_0729 {
    private Stack<Integer> addStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public void add(int i) {
        addStack.add(i);
    }

    public int pop() {
        if(popStack.isEmpty()) {
            while (!addStack.isEmpty()) {
                popStack.add(addStack.pop());
            }
        }

        if(popStack.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }

        return popStack.pop();
    }

    public int peek() {
        if(popStack.isEmpty()) {
            while (!addStack.isEmpty()) {
                popStack.add(addStack.pop());
            }
        }

        if(popStack.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }

        return popStack.peek();
    }

    public boolean isEmpty() {
        return addStack.size() + popStack.size() == 0;
    }

    public int size() {
        return addStack.size() + popStack.size();
    }

    public static void main(String[] args) {
        QueueByStack_0729 queue = new QueueByStack_0729();
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("-------------");
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        queue.add(6);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        queue.add(7);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        queue.add(8);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
        System.out.println("s1.size = " + queue.addStack.size() + ",s2.size = " + queue.popStack.size());
        System.out.println("-------------");
    }
}
