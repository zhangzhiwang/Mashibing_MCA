package com.mashibing.dailyPractice.round6;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现队列
 */
public class QueueByArray_0909 {
    private int[] arr;
    private int head;
    private int tail;
    private int size;

    public QueueByArray_0909(int len) {
        if(len <= 0) {
            return;
        }

        arr = new int[len];
    }

    public void add(int n) {
        if(size == arr.length) {
            throw new RuntimeException("Queue is full!");
        }

        arr[tail++] = n;
        if(tail == arr.length) {
            tail = 0;
        }
        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("Queue is empty!");
        }

        int r = arr[head++];
        if(head == arr.length) {
            head = 0;
        }
        size--;
        return r;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("Queue is empty!");
        }

        return arr[head];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size () {
        return size;
    }

    public static void main(String[] args) {
        QueueByArray_0909 queue = new QueueByArray_0909(5);
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("-------------");

        queue.add(1);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("-------------");

        queue.add(2);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("-------------");

//        queue.add(3);
//        System.out.println("isEmpty = " + queue.isEmpty());
//        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
//        DuiShuQiUtil.printArr(queue.arr);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
//
//        System.out.println("pop = " + queue.pop());
//        System.out.println("isEmpty = " + queue.isEmpty());
//        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
//        DuiShuQiUtil.printArr(queue.arr);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
//
//        queue.add(4);
//        System.out.println("isEmpty = " + queue.isEmpty());
//        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
//        DuiShuQiUtil.printArr(queue.arr);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
//
//        queue.add(5);
//        System.out.println("isEmpty = " + queue.isEmpty());
//        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
//        DuiShuQiUtil.printArr(queue.arr);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
//
//        queue.add(6);
//        System.out.println("isEmpty = " + queue.isEmpty());
//        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
//        DuiShuQiUtil.printArr(queue.arr);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
//
////        queue.add(7);
//        System.out.println("pop = " + queue.pop());
//        System.out.println("isEmpty = " + queue.isEmpty());
//        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
//        DuiShuQiUtil.printArr(queue.arr);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
//
//        queue.add(7);
//        System.out.println("isEmpty = " + queue.isEmpty());
//        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
//        DuiShuQiUtil.printArr(queue.arr);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
//
//        while (!queue.isEmpty()) {
//            System.out.println("pop = " + queue.pop());
//        }
//        DuiShuQiUtil.printArr(queue.arr);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
//
//        queue.add(10);
//        System.out.println("isEmpty = " + queue.isEmpty());
//        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
//        DuiShuQiUtil.printArr(queue.arr);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
//
//        System.out.println("pop = " + queue.pop());
//        System.out.println("isEmpty = " + queue.isEmpty());
//        System.out.println("size = " + queue.size());
////        System.out.println("peek = " + queue.peek());
//        DuiShuQiUtil.printArr(queue.arr);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
    }
}
