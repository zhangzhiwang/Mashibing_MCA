package com.mashibing.dailyPractice.round6;

import com.mashibing.dailyPractice.round5.QueueByDoubleLinkedList_0817;
import com.mashibing.list.DoubleNode;

/**
 * 用双向链表实现队列
 */
public class QueueByDoubleLinkedList_1030 {
    private DoubleNode<Integer> h;
    private DoubleNode<Integer> t;
    private int size;

    public void add(int num) {
        DoubleNode<Integer> node = new DoubleNode<>(num);
        if(h == null) {
            h = t = node;
        } else {
            t.next = node;
            node.last = t;
            t = node;
        }
        size++;
    }

    public int pop() {
        if(h == null) {
            throw new RuntimeException("Queue is empty!");
        }

        int r = h.value;
        h = h.next;
        if(h == null) {
            t = null;
        } else {
            h.last = null;
        }

        size--;
        return r;
    }

    public int peek() {
        if(h == null) {
            throw new RuntimeException("Queue is empty!");
        }

        return h.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        QueueByDoubleLinkedList_1030 queue = new QueueByDoubleLinkedList_1030();
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

