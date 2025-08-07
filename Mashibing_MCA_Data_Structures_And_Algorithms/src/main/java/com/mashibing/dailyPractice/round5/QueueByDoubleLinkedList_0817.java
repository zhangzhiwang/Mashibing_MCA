package com.mashibing.dailyPractice.round5;

import com.mashibing.list.DoubleNode;

/**
 * 用双向链表实现队列
 */
public class QueueByDoubleLinkedList_0817 {
    private DoubleNode<Integer> head;
    private DoubleNode<Integer> tail;
    private int size;

    public void add(int num) {
        DoubleNode<Integer> node = new DoubleNode<>(num);
        if(head == null) {
            head = node;
        } else {
            tail.next = node;
            node.last = tail;
        }
        tail = node;
        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("Queue is Empty!");
        }

        int result = head.value;
        head = head.next;
        if(head != null) {
            head.last = null;
        } else {
            tail = null;
        }
        size--;
        return result;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("Queue is Empty!");
        }

        return head.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        QueueByDoubleLinkedList_0817 queue = new QueueByDoubleLinkedList_0817();
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