package com.mashibing.stack;

import java.util.Stack;

/**
 * 使用栈实现队列（非双端队列）
 * 课程：体系班课时24
 * 思路：见com.mashibing.preInEclipse.senior.stack.QueueByStack注释
 */
public class QueueByStack {
    private Stack<Integer> pushStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    private void pushToPop() {
        if (!popStack.isEmpty()) {
            return;
        }

        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
    }

    public void add(int num) {
        pushStack.push(num);
        pushToPop();
    }

    public int pop() {
        pushToPop();
        if (popStack.isEmpty()) {
            throw new RuntimeException("队列为空！");
        }

        return popStack.pop();
    }

    public int peek() {
        pushToPop();
        if (popStack.isEmpty()) {
            throw new RuntimeException("队列为空！");
        }

        return popStack.peek();
    }

    public static void main(String[] args) {
        QueueByStack qbs = new QueueByStack();
        qbs.add(1);
        qbs.add(2);
        qbs.add(3);
        qbs.add(4);
        qbs.add(5);
        System.out.println(qbs.pop());
        System.out.println(qbs.pop());
        System.out.println(qbs.pop());
        System.out.println(qbs.pop());
        System.out.println(qbs.pop());
        System.out.println(qbs.peek());
    }
}
