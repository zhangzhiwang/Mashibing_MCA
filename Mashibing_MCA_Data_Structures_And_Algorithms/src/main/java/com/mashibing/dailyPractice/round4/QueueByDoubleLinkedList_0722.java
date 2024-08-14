package com.mashibing.dailyPractice.round4;

import com.mashibing.list.DoubleNode;
import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 用双向链表实现队列
 */
public class QueueByDoubleLinkedList_0722 {
    private DoubleNode<Integer> head;
    private DoubleNode<Integer> tail;
    private int size;

    public void add(int i) {
        DoubleNode<Integer> node = new DoubleNode<>(i);
        if(head == null) {
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
        if(size == 0) {
            throw new RuntimeException("Queue is empty!");
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
        QueueByDoubleLinkedList_0722 queue = new QueueByDoubleLinkedList_0722();
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        queue.add(1);
        queue.add(2);
        queue.add(3);

        DuiShuQiUtil.printDoubleList(queue.head);
        System.out.println("-------------");
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
