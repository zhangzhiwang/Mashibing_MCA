package com.mashibing.dailyPractice.round3._No51_60;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现栈
 */
public class StackByArray_0308 {
    private int[] arr;
    private int head;
    private int size;

    public StackByArray_0308(int len) {
        this.arr = new int[len];
    }

    public void add(int num) {
        if(size == arr.length) {
            throw new RuntimeException("栈已满");
        }

        arr[head++] = num;
        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("栈已空");
        }

        int r = arr[--head];
        size--;
        return r;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("栈已空");
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
        StackByArray_0308 s = new StackByArray_0308(5);
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);
        s.add(5);
//        s.add(6);

        DuiShuQiUtil.printArr(s.arr);
        System.out.println("head = " + s.head);
        System.out.println("size = " + s.size);
        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("--------------");

        System.out.println("pop = " + s.pop());
        DuiShuQiUtil.printArr(s.arr);
        System.out.println("head = " + s.head);
        System.out.println("size = " + s.size);
        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("--------------");

        System.out.println("pop = " + s.pop());
        DuiShuQiUtil.printArr(s.arr);
        System.out.println("head = " + s.head);
        System.out.println("size = " + s.size);
        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("--------------");

        s.add(6);
        DuiShuQiUtil.printArr(s.arr);
        System.out.println("head = " + s.head);
        System.out.println("size = " + s.size);
        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("--------------");

        System.out.println("pop = " + s.pop());
        DuiShuQiUtil.printArr(s.arr);
        System.out.println("head = " + s.head);
        System.out.println("size = " + s.size);
        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("--------------");

        System.out.println("pop = " + s.pop());
        DuiShuQiUtil.printArr(s.arr);
        System.out.println("head = " + s.head);
        System.out.println("size = " + s.size);
        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("--------------");

        System.out.println("pop = " + s.pop());
        DuiShuQiUtil.printArr(s.arr);
        System.out.println("head = " + s.head);
        System.out.println("size = " + s.size);
        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("--------------");

        System.out.println("pop = " + s.pop());
        DuiShuQiUtil.printArr(s.arr);
        System.out.println("head = " + s.head);
        System.out.println("size = " + s.size);
//        System.out.println("peek = " + s.peek());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("--------------");
    }
}
