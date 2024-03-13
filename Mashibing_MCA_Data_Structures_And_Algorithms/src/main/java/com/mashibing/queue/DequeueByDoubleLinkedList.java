package com.mashibing.queue;

import com.mashibing.list.DoubleNode;

/**
 * 用双向链表实现双端队列
 * 课程：体系班课时22
 * 思路：分别可以从队列的头部和尾部添加和弹出数据。（之前的eclipse工程无此代码）
 */
public class DequeueByDoubleLinkedList<T> {
    private int size;
    private DoubleNode<T> head;
    private DoubleNode<T> tail;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFromTail(T t) {
        DoubleNode<T> newNode = new DoubleNode<>(t);
        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.last = tail;
            tail = newNode;
        }
        size++;
    }

    public void addFromHead(T t) {
        DoubleNode<T> newNode = new DoubleNode<>(t);
        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            head.last = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public T popFromTail() {
        if (size == 0) {
            throw new RuntimeException("队列为空");
        }

        T result = tail.value;
        tail = tail.last;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;// 保险起见可以同时把head也设置为空
        }
        size--;
        return result;
    }

    public T popFromHead() {
        if (size == 0) {
            throw new RuntimeException("队列为空");
        }

        T result = head.value;
        head = head.next;
        if (head != null) {
            head.last = null;
        } else {
            tail = null;// 保险起见可以同时把tail也设置为空
        }

        size--;
        return result;
    }

    public T peekFromHead() {
        if (size == 0) {
            throw new RuntimeException("队列为空");
        }
        return head.value;
    }

    public T peekFromTail() {
        if (size == 0) {
            throw new RuntimeException("队列为空");
        }
        return tail.value;
    }

    public static void main(String[] args) {
        DequeueByDoubleLinkedList<Integer> dbdll = new DequeueByDoubleLinkedList<>();
        System.out.println(dbdll.size());
        System.out.println(dbdll.isEmpty());
        System.out.println("---------------");

        dbdll.addFromTail(1);
        dbdll.addFromTail(2);
        dbdll.addFromTail(3);
//        dbdll.addFromHead(1);
//        dbdll.addFromHead(2);
//        dbdll.addFromHead(3);
//        System.out.println("size:" + dbdll.size());
//        while(!dbdll.isEmpty()) {
////            System.out.print(dbdll.popFromHead() + "\t");
//            System.out.print(dbdll.popFromTail() + "\t");
//        }
//        System.out.println();

        System.out.println(dbdll.peekFromHead());
        System.out.println(dbdll.peekFromTail());
        System.out.println("---------------");

        dbdll.popFromTail();
        System.out.println(dbdll.peekFromTail());
        dbdll.popFromHead();
        System.out.println(dbdll.peekFromTail());
        System.out.println(dbdll.peekFromHead());
    }
}
