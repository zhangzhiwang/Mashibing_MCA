package com.mashibing.dailyPractice.round1._1_28;

import com.mashibing.list.DoubleNode;

/**
 * 用双链表实现双端队列，双端队列的功能有：头部新增元素、尾部新增元素、头部弹出元素、尾部弹出元素、查看头部元素值、查看尾部元素值、是否为空、队列大小。
 */
public class DequeByDoubleList_0128<T> {
    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int size;

    public void addFromTail(T t) {
        DoubleNode<T> node = new DoubleNode<>(t);
        if(tail == null) {
            head = node;
        } else {
            tail.next = node;
            node.last = tail;
        }
        tail = node;
        size++;
    }

    public void addFromHead(T t) {
        DoubleNode<T> node = new DoubleNode<>(t);
        if(head == null) {
            tail = node;
        } else {
            node.next = head;
            head.last = node;
        }
        head = node;
        size++;
    }

    public T popFromHead() {
        if(head == null) {
            return null;
        }

        T result = head.value;
        head = head.next;
        if(head == null) {
            tail = null;
        } else {
            head.last = null;
        }
        size--;

        return result;
    }

    public T popFromTail() {
        if(tail == null) {
            return null;
        }

        T result = tail.value;
        tail = tail.last;
        if(tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;

        return result;
    }

    public T peekHead() {
        if(head == null) {
            return null;
        }

        return head.value;
    }

    public T peekTail() {
        if(tail == null) {
            return null;
        }

        return tail.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        DequeByDoubleList_0128<Integer> dequeue = new DequeByDoubleList_0128<>();
        dequeue.addFromHead(3);
        dequeue.addFromHead(4);
        dequeue.addFromHead(5);
        dequeue.addFromTail(2);
        dequeue.addFromTail(1);

        System.out.println(dequeue.isEmpty());
        System.out.println(dequeue.size());
        System.out.println(dequeue.peekHead());
        System.out.println(dequeue.peekTail());
        System.out.println("--------------");

//        System.out.println(dequeue.popFromHead());
//        System.out.println(dequeue.popFromHead());
//        System.out.println(dequeue.popFromHead());
//        System.out.println(dequeue.popFromHead());
//        System.out.println(dequeue.popFromHead());
        System.out.println(dequeue.popFromTail());
        System.out.println(dequeue.popFromTail());
        System.out.println(dequeue.popFromTail());
        System.out.println(dequeue.popFromTail());
        System.out.println(dequeue.popFromTail());
    }
}