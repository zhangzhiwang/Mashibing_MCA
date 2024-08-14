package com.mashibing.dailyPractice.round4;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 用数组实现队列
 */
public class QueueByArray_0729 {
    private int[] arr;
    private int head;
    private int tail;
    private int size;

    public QueueByArray_0729(int n) {
        if (n <= 0) {
            return;
        }

        arr = new int[n];
    }

    public void add(int i) {
        if (size == arr.length) {
            throw new RuntimeException("Queue is full!");
        }

        if (size == 0) {
            arr[tail] = i;
        } else {
            if (++tail == arr.length) {
                tail = 0;
            }
            arr[tail] = i;
        }

        size++;
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("Queue is empty!");
        }

        int result = arr[head++];
        if (head == arr.length) {
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

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        QueueByArray_0729 queue = new QueueByArray_0729(5);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("-------------");
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        DuiShuQiUtil.printArr(queue.arr);
        System.out.println("head = " + queue.head + ",tail = " + queue.tail);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("head = " + queue.head + ",tail = " + queue.tail);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("head = " + queue.head + ",tail = " + queue.tail);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        queue.add(6);
        System.out.println("head = " + queue.head + ",tail = " + queue.tail);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        queue.add(7);
        System.out.println("head = " + queue.head + ",tail = " + queue.tail);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("head = " + queue.head + ",tail = " + queue.tail);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        queue.add(8);
        System.out.println("head = " + queue.head + ",tail = " + queue.tail);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("head = " + queue.head + ",tail = " + queue.tail);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("head = " + queue.head + ",tail = " + queue.tail);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("head = " + queue.head + ",tail = " + queue.tail);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size);
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");
    }
}
