package com.mashibing.dailyPractice.round1._2_2;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现队列
 */
public class QueueByArray_0202 {
    private int[] arr;
    private int head;
    private int tail;
    private int size;

    public QueueByArray_0202(int len) {
        this.arr = new int[len];
    }

    public void add(int num) {
        if(size == arr.length) {
            throw new RuntimeException("队列已满");
        }

        arr[tail++] = num;
        if(tail == arr.length) {
            tail = 0;
        }

        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("队列已空");
        }

        int result = arr[head++];
        if(head == arr.length) {
            head = 0;
        }

        size--;
        return result;
    }

    public int peek() {
        if(size == 0) {
            throw new RuntimeException("队列已空");
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
        QueueByArray_0202 queue = new QueueByArray_0202(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
//        queue.add(3);
        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());
        System.out.println("queue:");
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("-------------");

        System.out.println(queue.pop());
        System.out.println("size = " + queue.size());
        System.out.println(queue.isEmpty());
        System.out.println("peek = " + queue.peek());
        System.out.println("queue:");
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head:" + queue.head + ",tail:" + queue.tail);
        System.out.println("-------------");

        System.out.println(queue.pop());
        System.out.println("size = " + queue.size());
        System.out.println(queue.isEmpty());
        System.out.println("peek = " + queue.peek());
        System.out.println("queue:");
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head:" + queue.head + ",tail:" + queue.tail);
        System.out.println("-------------");

        System.out.println(queue.pop());
        System.out.println("size = " + queue.size());
        System.out.println(queue.isEmpty());
//        System.out.println("peek = " + queue.peek());
        System.out.println("queue:");
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head:" + queue.head + ",tail:" + queue.tail);
        System.out.println("-------------");
    }
}
