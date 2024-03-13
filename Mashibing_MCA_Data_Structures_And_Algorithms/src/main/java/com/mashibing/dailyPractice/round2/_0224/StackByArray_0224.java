package com.mashibing.dailyPractice.round2._0224;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现栈
 */
public class StackByArray_0224 {
    private int[] arr;
    private int head;
    private int size;

    public StackByArray_0224(int len) {
        this.arr = new int[len];
    }

    public void add(int num) {
        if(size == arr.length) {
            throw new RuntimeException("栈已满");
        }

        arr[head] = num;
        if(head != arr.length - 1) {
            head++;
        }
        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("栈已空");
        }

        int result = arr[head == size - 1 ? head : --head];
        size--;
        return result;
    }

    public int peek() {
        if(size == 0) {
            throw new RuntimeException("栈已空");
        }

        return arr[head == arr.length - 1 ? head : head - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        StackByArray_0224 stack = new StackByArray_0224(5);
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
//        stack.add(6);

        DuiShuQiUtil.printArr(stack.arr);

        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        System.out.println("head = " + stack.head);
        System.out.println("size = " + stack.size());
//        System.out.println("peek = " + stack.peek());
        System.out.println(stack.isEmpty());
        System.out.println("-------------------");


//        stack.add(6);
//        DuiShuQiUtil.printArr(stack.arr);
//        System.out.println("head = " + stack.head);
//        System.out.println("size = " + stack.size());
//        System.out.println("peek = " + stack.peek());
//        System.out.println(stack.isEmpty());
    }
}
