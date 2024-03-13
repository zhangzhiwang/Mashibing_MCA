package com.mashibing.dailyPractice.round1._2_1;

import com.mashibing.list.DoubleNode;

/**
 * 用双向链表实现队列
 */
public class QueueByDoubleLinkedList_0201 {
    private DoubleNode<Integer> head;
    private DoubleNode<Integer> tail;
    private int size;

    public void add(int num) {
        DoubleNode<Integer> node = new DoubleNode<>(num);
        if(tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.last = tail;
            tail = node;
        }
        size++;
    }

    public int pop() {
        if(head == null) {
            throw new RuntimeException("队列已空");
        }

        Integer result = head.value;
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
        if (head == null) {
            throw new RuntimeException("队列已空");
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
        QueueByDoubleLinkedList_0201 q = new QueueByDoubleLinkedList_0201();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.size());
        System.out.println(q.isEmpty());
        System.out.println(q.peek());
        System.out.println("-------------");

        System.out.println(q.head.value + " -> " + q.head.next.value + " -> " + q.head.next.next.value + " -> " + q.head.next.next.next);
        System.out.println(q.tail.value + " -> " + q.tail.last.value + " -> " + q.tail.last.last.value + " -> " + q.tail.last.last.last);
        System.out.println("-------------");

        System.out.println(q.pop());
        System.out.println(q.size());
        System.out.println(q.isEmpty());
        System.out.println(q.peek());
        System.out.println(q.head.value + " -> " + q.head.next.value + " -> " + q.head.next.next);
        System.out.println(q.tail.value + " -> " + q.tail.last.value + " -> " + q.tail.last.last);
        System.out.println("-------------");

        System.out.println(q.pop());
        System.out.println(q.size());
        System.out.println(q.isEmpty());
        System.out.println(q.peek());
        System.out.println(q.head.value + " -> " + q.head.next);
        System.out.println(q.tail.value + " -> " + q.tail.last);
        System.out.println("-------------");

        System.out.println(q.pop());
        System.out.println(q.size());
        System.out.println(q.isEmpty());
//        System.out.println(q.peek());
        System.out.println(q.head);
        System.out.println(q.tail);
    }
}
