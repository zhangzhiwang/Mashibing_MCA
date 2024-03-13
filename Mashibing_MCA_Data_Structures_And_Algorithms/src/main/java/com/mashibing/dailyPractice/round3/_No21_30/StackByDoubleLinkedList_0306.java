package com.mashibing.dailyPractice.round3._No21_30;

import com.mashibing.list.DoubleNode;

/**
 * 用双向链表实现栈
 */
public class StackByDoubleLinkedList_0306 {
    private DoubleNode<Integer> head;
    private int size;

    public void add(int num) {
        DoubleNode<Integer> n = new DoubleNode<>(num);
        n.next = head;
        if (head != null) {
            head.last = n;
        }
        head = n;
        size++;
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("栈已空");
        }

        int r = head.value;
        head = head.next;
        if (head != null) {
            head.last = null;
        }
        size--;
        return r;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("栈已空");
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
        StackByDoubleLinkedList_0306 s = new StackByDoubleLinkedList_0306();
        s.add(1);
        s.add(2);
        s.add(3);

        System.out.println("size = " + s.size());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("peek = " + s.peek());
        System.out.println("---------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("peek = " + s.peek());
        System.out.println("---------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
        System.out.println("isEmpty = " + s.isEmpty());
        System.out.println("peek = " + s.peek());
        System.out.println("---------");

        System.out.println("pop = " + s.pop());
        System.out.println("size = " + s.size());
        System.out.println("isEmpty = " + s.isEmpty());
//        System.out.println("peek = " + s.peek());
        System.out.println("---------");
    }
}
