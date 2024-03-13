package com.mashibing.dailyPractice.round2._0224;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现队列
 */
public class QueueByArray_0224 {
    private int[] arr;
    private int head;
    private int tail;
    private int size;

    public QueueByArray_0224(int len) {
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
        QueueByArray_0224 queue = new QueueByArray_0224(5);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println("pop = " + queue.pop());
        System.out.println("pop = " + queue.pop());
        queue.add(5);
        queue.add(6);
        queue.add(7);
//        queue.add(8);
//        System.out.println("pop = " + queue.pop());
//        System.out.println("pop = " + queue.pop());
//        System.out.println("pop = " + queue.pop());
//        System.out.println("pop = " + queue.pop());
//        System.out.println("pop = " + queue.pop());


        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
//        System.out.println(queue.peek());
        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
    }
}
