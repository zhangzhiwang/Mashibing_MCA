package com.mashibing.dailyPractice.round5;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现栈
 */
public class StackByArray_0817 {
    private int[] arr;
    private int head;
    private int size;

    public StackByArray_0817(int n) {
        if(n <= 0) {
            return;
        }

        arr = new int[n];
    }

    public void add(int num) {
        if(size == arr.length) {
            throw new RuntimeException("Stack is full!");
        }

        arr[head++] = num;
        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("Stack is empty!");
        }

        int result = arr[--head];
        size--;
        return result;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("Stack is empty!");
        }

        return arr[head - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        StackByArray_0817 stack = new StackByArray_0817(5);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("-------------");

        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
//        stack.add(5);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        DuiShuQiUtil.printArr(stack.arr);
        System.out.println("head = " + stack.head);
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        DuiShuQiUtil.printArr(stack.arr);
        System.out.println("head = " + stack.head);
        System.out.println("-------------");

        stack.add(7);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        DuiShuQiUtil.printArr(stack.arr);
        System.out.println("head = " + stack.head);
        System.out.println("-------------");

        while (!stack.isEmpty()) {
            System.out.println("pop = " + stack.pop());
        }
        DuiShuQiUtil.printArr(stack.arr);
        System.out.println("head = " + stack.head);
        System.out.println("-------------");
    }
}
