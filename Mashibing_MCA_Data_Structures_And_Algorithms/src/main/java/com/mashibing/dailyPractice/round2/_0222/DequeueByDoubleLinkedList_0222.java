package com.mashibing.dailyPractice.round2._0222;

import com.mashibing.list.DoubleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 用双链表实现双端队列，双端队列的功能有：头部新增元素、尾部新增元素、头部弹出元素、尾部弹出元素、查看头部元素值、查看尾部元素值、是否为空、队列大小。
 */
public class DequeueByDoubleLinkedList_0222 {
    private DoubleNode<Integer> head;
    private DoubleNode<Integer> tail;
    private int size;

    public void addFromHead(int num) {
        DoubleNode<Integer> node = new DoubleNode<>(num);
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

    public void addFromTail(int num) {
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

    public int popFromHead() {
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

    public int popFromTail() {
        if(tail == null) {
            throw new RuntimeException("队列已空");
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
        if(head == null) {
            throw new RuntimeException("队列已空");
        }

        return head.value;
    }

    public int peekFromTail() {
        if(tail == null) {
            throw new RuntimeException("队列已空");
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
        DequeueByDoubleLinkedList_0222 dequeue = new DequeueByDoubleLinkedList_0222();
        dequeue.addFromHead(1);
        dequeue.addFromHead(2);
        dequeue.addFromHead(3);

        System.out.println("从头部操作：");
        DuiShuQiUtil.printDoubleList(dequeue.head);
        System.out.println(dequeue.head.value);
        System.out.println(dequeue.size);
        System.out.println(dequeue.isEmpty());
        System.out.println(dequeue.peekFromHead());
        System.out.println("-----------");

        System.out.println(dequeue.popFromHead());
        System.out.println(dequeue.head.value);
        System.out.println(dequeue.size);
        System.out.println(dequeue.isEmpty());
        System.out.println(dequeue.peekFromHead());
        DuiShuQiUtil.printDoubleList(dequeue.head);
        System.out.println("-----------");

        System.out.println(dequeue.popFromHead());
        System.out.println(dequeue.head.value);
        System.out.println(dequeue.size);
        System.out.println(dequeue.isEmpty());
        System.out.println(dequeue.peekFromHead());
        DuiShuQiUtil.printDoubleList(dequeue.head);
        System.out.println("-----------");

        System.out.println(dequeue.popFromHead());
        System.out.println(dequeue.head);
        System.out.println(dequeue.tail);
        System.out.println(dequeue.size);
        System.out.println(dequeue.isEmpty());
//        System.out.println(dequeue.peekFromHead());
        DuiShuQiUtil.printDoubleList(dequeue.head);
        System.out.println("-----------");

        System.out.println("从尾部操作：");
        dequeue.addFromTail(1);
        dequeue.addFromTail(2);
        dequeue.addFromTail(3);

        DuiShuQiUtil.printDoubleList(dequeue.head);
        System.out.println(dequeue.head.value);
        System.out.println(dequeue.size);
        System.out.println(dequeue.isEmpty());
        System.out.println(dequeue.peekFromTail());
        System.out.println("-----------");

        System.out.println(dequeue.popFromTail());
        System.out.println(dequeue.size);
        System.out.println(dequeue.isEmpty());
        System.out.println(dequeue.peekFromTail());
        DuiShuQiUtil.printDoubleList(dequeue.head);
        System.out.println("-----------");

        System.out.println(dequeue.popFromTail());
        System.out.println(dequeue.size);
        System.out.println(dequeue.isEmpty());
        System.out.println(dequeue.peekFromTail());
        DuiShuQiUtil.printDoubleList(dequeue.head);
        System.out.println("-----------");

        System.out.println(dequeue.popFromTail());
        System.out.println(dequeue.size);
        System.out.println(dequeue.isEmpty());
//        System.out.println(dequeue.peekFromTail());
        DuiShuQiUtil.printDoubleList(dequeue.head);
        System.out.println(dequeue.head);
        System.out.println(dequeue.tail);
        System.out.println("-----------");
    }
}
