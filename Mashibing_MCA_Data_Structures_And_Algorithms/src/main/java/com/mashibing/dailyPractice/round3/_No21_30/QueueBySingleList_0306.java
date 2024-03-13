package com.mashibing.dailyPractice.round3._No21_30;

import com.mashibing.list.SingleNode;

/**
 * 用单链表实现队列，队列的功能有：新增元素、弹出元素、查看队首元素值、是否为空、队列大小。
 */
public class QueueBySingleList_0306 {
    private SingleNode<Integer> head;
    private SingleNode<Integer> tail;
    private int size;

    public void add(int num) {
        SingleNode<Integer> node = new SingleNode<>(num);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("队列已空");
        }

        int r = head.value;
        head = head.next;
        if (head == null) {
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
        QueueBySingleList_0306 q = new QueueBySingleList_0306();
        q.add(1);
        q.add(2);
        q.add(3);

        System.out.println("size = " + q.size());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("peek = " + q.peek());
        System.out.println("---------");

        System.out.println("pop = " + q.pop());
        System.out.println("size = " + q.size());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("peek = " + q.peek());
        System.out.println("---------");

        System.out.println("pop = " + q.pop());
        System.out.println("size = " + q.size());
        System.out.println("isEmpty = " + q.isEmpty());
        System.out.println("peek = " + q.peek());
        System.out.println("---------");

        System.out.println("pop = " + q.pop());
        System.out.println("size = " + q.size());
        System.out.println("isEmpty = " + q.isEmpty());
//        System.out.println("peek = " + q.peek());
        System.out.println("---------");
    }
}
