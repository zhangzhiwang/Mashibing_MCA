package com.mashibing.dailyPractice.round2._0222;

import com.mashibing.list.DoubleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 用双向链表实现队列
 */
public class QueueByDoubleLinkedList_0222 {
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
        if(head == null) {
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
        QueueByDoubleLinkedList_0222 queue = new QueueByDoubleLinkedList_0222();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        DuiShuQiUtil.printDoubleList(queue.head);
        System.out.println(queue.size);
        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());
        System.out.println("-------------");

        System.out.println(queue.pop());
        System.out.println(queue.size);
        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());
        DuiShuQiUtil.printDoubleList(queue.head);
        System.out.println(queue.head.last);
        System.out.println(queue.head.next.value);
        System.out.println("-------------");

//        System.out.println(queue.pop());
//        System.out.println(queue.size);
//        System.out.println(queue.isEmpty());
//        System.out.println(queue.peek());
//        DuiShuQiUtil.printDoubleList(queue.head);
//        System.out.println("head = " + queue.head.value);
//        System.out.println("tail = " + queue.tail.value);
//        System.out.println("-------------");
//
//        System.out.println(queue.pop());
//        System.out.println(queue.size);
//        System.out.println(queue.isEmpty());
////        System.out.println(queue.peek());
//        DuiShuQiUtil.printDoubleList(queue.head);
//        System.out.println("head = " + queue.head);
//        System.out.println("tail = " + queue.tail);
//        System.out.println("-------------");
    }
}
