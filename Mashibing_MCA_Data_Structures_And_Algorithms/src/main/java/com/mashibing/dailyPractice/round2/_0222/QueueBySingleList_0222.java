package com.mashibing.dailyPractice.round2._0222;

import com.mashibing.list.SingleNode;

/**
 * 用单链表实现队列，队列的功能有：新增元素、弹出元素、查看队首元素值、是否为空、队列大小。
 */
public class QueueBySingleList_0222 {
    private SingleNode<Integer> head;
    private SingleNode<Integer> tail;
    private int size;

    public void add(int num) {
        SingleNode<Integer> node = new SingleNode<>(num);
        if(tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
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
        if(head == null) {
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
        QueueBySingleList_0222 queue = new QueueBySingleList_0222();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.size);
        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());
        System.out.println("-------------");

        System.out.println(queue.pop());
        System.out.println(queue.size);
        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());
        System.out.println("-------------");

        System.out.println(queue.pop());
        System.out.println(queue.size);
        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());
        System.out.println("-------------");

        System.out.println(queue.pop());
        System.out.println(queue.size);
        System.out.println(queue.isEmpty());
//        System.out.println(queue.peek());
        System.out.println(queue.tail);
        System.out.println("-------------");
    }
}
