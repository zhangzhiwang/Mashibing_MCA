package com.mashibing.dailyPractice.round3._No21_30;

import com.mashibing.list.DoubleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 用双向链表实现队列
 */
public class QueueByDoubleLinkedList_0306 {
    private DoubleNode<Integer> head;
    private DoubleNode<Integer> tail;
    private int size;

    public void add(int num) {
        DoubleNode<Integer> n = new DoubleNode<>(num);
        if (tail == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            n.last = tail;
            tail = n;
        }
        size++;
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("队列已空");
        }

        int r = head.value;
        head = head.next;
        if (head != null) {
            head.last = null;
        } else {
            tail = null;
        }
        size--;
        return r;
    }

    public int peek() {
        if (size == 0) {
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
        QueueByDoubleLinkedList_0306 q = new QueueByDoubleLinkedList_0306();
        q.add(1);
        q.add(2);
        q.add(3);

        DuiShuQiUtil.printDoubleList(q.head);
        System.out.println("---------");

        System.out.println("size = " + q.size());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("peek = " + q.peek());
        System.out.println("---------");

        System.out.println("pop = " + q.pop());
        System.out.println("size = " + q.size());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("peek = " + q.peek());
        DuiShuQiUtil.printDoubleList(q.head);
        System.out.println("---------");

        System.out.println("pop = " + q.pop());
        System.out.println("size = " + q.size());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("peek = " + q.peek());
        DuiShuQiUtil.printDoubleList(q.head);
        System.out.println("---------");

        System.out.println("pop = " + q.pop());
        System.out.println("size = " + q.size());
        System.out.println("isEmpty = " + q.isEmpty());
//        System.out.println("peek = " + q.peek());
        System.out.println("---------");
    }
}