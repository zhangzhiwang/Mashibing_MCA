package com.mashibing.dailyPractice.round4;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现栈
 */
public class StackByArray_0729 {
    private int [] arr;
    private int head = -1;
    private int size;

    public StackByArray_0729(int n) {
        if (n <= 0) {
            return;
        }

        arr = new int[n];
    }

    public void add(int i) {
        if (size == arr.length) {
            throw new RuntimeException("Stack is full!");
        }

        arr[++head] = i;
        size++;
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("Stack is empty!");
        }

        int result = arr[head--];
        size--;
        return result;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("Stack is empty!");
        }

        return arr[head];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        StackByArray_0729 stack = new StackByArray_0729(5);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("-------------");
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);

        DuiShuQiUtil.printArr(stack.arr);
        System.out.println("head = " + stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("head = " + stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("head = " + stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        stack.add(6);
        System.out.println("head = " + stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        stack.add(7);
        System.out.println("head = " + stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("head = " + stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        stack.add(8);
        System.out.println("head = " + stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("head = " + stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("head = " + stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("head = " + stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size);
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");
    }
}
