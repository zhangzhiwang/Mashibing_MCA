package com.mashibing.dailyPractice.round3._No41_50;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现队列
 */
public class QueueByArray_0308 {
    private int[] arr;
    private int head;
    private int tail;
    private int size;

    public QueueByArray_0308(int len) {
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

        int r = arr[head++];
        if (head == arr.length) {
            head = 0;
        }
        size--;
        return r;
    }

    public int peek() {
        if (size == 0) {
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
        QueueByArray_0308 queue = new QueueByArray_0308(5);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
//        queue.add(6);

        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("------------");

        System.out.println("pop = " + queue.pop());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("------------");

        System.out.println("pop = " + queue.pop());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("------------");

        queue.add(6);
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("------------");

        queue.add(7);
//        queue.add(8);
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("------------");

        System.out.println("pop = " + queue.pop());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("------------");

        System.out.println("pop = " + queue.pop());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("peek = " + queue.peek());
        System.out.println("------------");

        System.out.println("pop = " + queue.pop());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("peek = " + queue.peek());
        System.out.println("------------");

        queue.add(8);
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("peek = " + queue.peek());
        System.out.println("------------");
    }
}
