package com.mashibing.dailyPractice.round3._No21_30;

import com.mashibing.list.DoubleNode;

/**
 * 用双链表实现双端队列，双端队列的功能有：头部新增元素、尾部新增元素、头部弹出元素、尾部弹出元素、查看头部元素值、查看尾部元素值、是否为空、队列大小。
 */
public class DequeByDoubleList_0306 {
    private DoubleNode<Integer> head;
    private DoubleNode<Integer> tail;
    private int size;

    public void addFromHead(int num) {
        DoubleNode<Integer> n = new DoubleNode<>(num);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            n.next = head;
            head.last = n;
            head = n;
        }
        size++;
    }

    public void addFromTail(int num) {
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

    public int popFromHead() {
        if (size == 0) {
            throw new RuntimeException("双端队列已空");
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

    public int popFromTail() {
        if (size == 0) {
            throw new RuntimeException("双端队列已空");
        }

        int r = tail.value;
        tail = tail.last;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return r;
    }

    public int peekFromHead() {
        if (size == 0) {
            throw new RuntimeException("双端队列已空");
        }
        return head.value;
    }
    
    public int peekFromTail() {
        if (size == 0) {
            throw new RuntimeException("双端队列已空");
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
        DequeByDoubleList_0306 d = new DequeByDoubleList_0306();
        d.addFromHead(1);
        d.addFromHead(2);
        d.addFromHead(3);

//        d.addFromTail(1);
//        d.addFromTail(2);
//        d.addFromTail(3);

//        System.out.println("size = " + d.size());
//        System.out.println("isEmpty = " + d.isEmpty());
//        System.out.println("peek = " + d.peekFromHead());
//        System.out.println("---------");
//
//        System.out.println("pop = " + d.popFromHead());
//        System.out.println("size = " + d.size());
//        System.out.println("isEmpty = " + d.isEmpty());
//        System.out.println("peek = " + d.peekFromHead());
//        System.out.println("---------");
//
//        System.out.println("pop = " + d.popFromHead());
//        System.out.println("size = " + d.size());
//        System.out.println("isEmpty = " + d.isEmpty());
//        System.out.println("peek = " + d.peekFromHead());
//        System.out.println("---------");
//
//        System.out.println("pop = " + d.popFromHead());
//        System.out.println("size = " + d.size());
//        System.out.println("isEmpty = " + d.isEmpty());
////        System.out.println("peek = " + d.peekFromHead());
//        System.out.println("---------");

        System.out.println("size = " + d.size());
        System.out.println("isEmpty = " + d.isEmpty());
        System.out.println("peek = " + d.peekFromTail());
        System.out.println("---------");

        System.out.println("pop = " + d.popFromTail());
        System.out.println("size = " + d.size());
        System.out.println("isEmpty = " + d.isEmpty());
        System.out.println("peek = " + d.peekFromTail());
        System.out.println("---------");

        System.out.println("pop = " + d.popFromTail());
        System.out.println("size = " + d.size());
        System.out.println("isEmpty = " + d.isEmpty());
        System.out.println("peek = " + d.peekFromTail());
        System.out.println("---------");

        System.out.println("pop = " + d.popFromTail());
        System.out.println("size = " + d.size());
        System.out.println("isEmpty = " + d.isEmpty());
//        System.out.println("peek = " + d.peekFromTail());
        System.out.println("---------");
    }
}
