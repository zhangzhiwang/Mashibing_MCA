package com.mashibing.queue;

import com.mashibing.list.DoubleNode;

/**
 * 用双向链表实现队列
 * 课程：体系班课时22
 * 思路：见com.mashibing.preInEclipse.senior.list.QueueByDoubleLinkedList注释
 */
public class QueueByDoubleLinkedList<T> {
    private int size;
    private DoubleNode<T> head;
    private DoubleNode<T> tail;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(T t) {
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

    public T pop() {
        if (size == 0) {
            throw new RuntimeException("队列已空");
        }

        T result = head.value;
        head = head.next;
        if (head != null) {// 注意：这个判断不要少
            head.last = null;
        } else {
            tail = null;// 保险起见可以同时把tail也设置为空
        }
        size--;
        return result;
    }

    public T peek() {
        if (size == 0) {
            throw new RuntimeException("队列已空");
        }

        return head.value;
    }

    public static void main(String[] args) {
        QueueByDoubleLinkedList<Integer> qbdll = new QueueByDoubleLinkedList<>();
        System.out.println(qbdll.size());
        System.out.println(qbdll.isEmpty());
        System.out.println("---------------");

        qbdll.add(1);
        System.out.println(qbdll.size());
        System.out.println(qbdll.isEmpty());
        System.out.println(qbdll.peek());
        System.out.println("---------------");

        qbdll.add(2);
        qbdll.add(3);
        System.out.println(qbdll.size());
        System.out.println(qbdll.isEmpty());
        System.out.println(qbdll.peek());
        System.out.println("---------------");

        System.out.println(qbdll.pop());
        System.out.println(qbdll.pop());
        System.out.println(qbdll.pop());
        System.out.println(qbdll.pop());
        System.out.println("size:" + qbdll.size());
        System.out.println("---------------");
    }
}
