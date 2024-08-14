package com.mashibing.dailyPractice.round4;

import com.mashibing.list.DoubleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 用双链表实现双端队列，双端队列的功能有：头部新增元素、尾部新增元素、头部弹出元素、尾部弹出元素、查看头部元素值、查看尾部元素值、是否为空、队列大小。
 */
public class DequeueByDoubleLinkedList_0724 {
    public static DoubleNode<Integer> head;
    public static DoubleNode<Integer> tail;
    private int size;

    public void addFromHead(int i) {
        DoubleNode<Integer> node = new DoubleNode<>(i);
        if(head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.last = node;
            head = node;
        }
        size++;
    }

    public void addFromTail(int i) {
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

    public int popFromHead() {
        if(size == 0) {
            throw new RuntimeException("Dequeue is empty!");
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

    public int popFromTail() {
        if(size == 0) {
            throw new RuntimeException("Dequeue is empty!");
        }

        int result = tail.value;
        tail = tail.last;
        if(tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return result;
    }

    public int peekFromHead() {
        if(size == 0) {
            throw new RuntimeException("Dequeue is empty!");
        }

        return head.value;
    }

    public int peekFromTail() {
        if(size == 0) {
            throw new RuntimeException("Dequeue is empty!");
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
        DequeueByDoubleLinkedList_0724 queue = new DequeueByDoubleLinkedList_0724();
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());
        queue.addFromHead(1);
        queue.addFromTail(2);
        queue.addFromHead(3);

        DuiShuQiUtil.printDoubleList(queue.head);
        System.out.println("-------------");
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peekFromTail());
        System.out.println("-------------");

        System.out.println("pop = " + queue.popFromHead());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peekFromTail());
        System.out.println("-------------");

        System.out.println("pop = " + queue.popFromTail());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
        System.out.println("peek = " + queue.peekFromHead());
        System.out.println("-------------");

        System.out.println("pop = " + queue.popFromTail());
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());
//        System.out.println("peek = " + queue.popFromTail());
//        System.out.println("pop = " + queue.popFromHead());
    }
}
