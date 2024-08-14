package com.mashibing.dailyPractice.round4;

import com.mashibing.list.SingleNode;

/**
 * 用单链表实现队列，队列的功能有：新增元素、弹出元素、查看队首元素值、是否为空、队列大小。
 */
public class QueueBySingleList_0722 {
    private SingleNode<Integer> head;
    private SingleNode<Integer> tail;
    private int size;

    public void add(int i) {
        SingleNode<Integer> node = new SingleNode<>(i);
        if(head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("Queue is empty!");
        }

        int result = head.value;
        head = head.next;
        if(head == null) {
            tail = null;
        }

        size--;
        return result;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("Queue is empty!");
        }

        return head.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        QueueBySingleList_0722 queue = new QueueBySingleList_0722();
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peek());
        System.out.println("-------------");

        System.out.println("pop = " + queue.pop());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.peek());
//        System.out.println("pop = " + queue.pop());
    }
}
