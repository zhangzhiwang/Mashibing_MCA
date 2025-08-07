package com.mashibing.dailyPractice.round5;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现队列
 */
public class QueueByArray_0817 {
    private int[] arr;
    private int head;
    private int tail;
    private int size;

    public QueueByArray_0817(int n) {
        if(n <= 0) {
            return;
        }

        arr = new int[n];
    }

    public void add(int num) {
        if(size == arr.length) {
            throw new RuntimeException("Queue is full!");
        }

        arr[tail++] = num;
        if(tail == arr.length) {
            tail = 0;
        }
        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("Queue is empty!");
        }

        int result = arr[head++];
        if(head == arr.length) {
            head = 0;
        }
        size--;
        return result;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("Queue is empty!");
        }

        return arr[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        QueueByArray_0817 queue = new QueueByArray_0817(5);
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("-------------");

        queue.add(1);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("-------------");

        queue.add(2);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("-------------");

        queue.add(3);
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
        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("-------------");

        queue.add(4);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("-------------");

        queue.add(5);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("-------------");

        queue.add(6);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("-------------");

//        queue.add(7);
        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("-------------");

        queue.add(7);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("-------------");

        while (!queue.isEmpty()) {
            System.out.println("pop = " + queue.pop());
        }
        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head);
        System.out.println("tail = " + queue.tail);
        System.out.println("-------------");

        queue.add(10);
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
    }
}
