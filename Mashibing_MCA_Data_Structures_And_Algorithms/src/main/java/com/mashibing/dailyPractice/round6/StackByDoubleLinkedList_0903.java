package com.mashibing.dailyPractice.round6;

import com.mashibing.dailyPractice.round5.StackByDoubleLinkedList_0819;
import com.mashibing.list.DoubleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 用双向链表实现栈
 */
public class StackByDoubleLinkedList_0903 {
    private DoubleNode<Integer> head;
    private int size;

    public void add(int n) {
        DoubleNode<Integer> node = new DoubleNode<>(n);
        if (head != null) {
            node.next = head;
            head.last = node;
        }
        head = node;
        size++;
    }

    public int pop() {
        if (head == null) {
            throw new RuntimeException("Stack is empty!");
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
        if (head == null) {
            throw new RuntimeException("Stack is empty!");
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
        StackByDoubleLinkedList_0903 stack = new StackByDoubleLinkedList_0903();
        System.out.println("size = " + stack.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        stack.add(1);
        stack.add(2);
        stack.add(3);
        DuiShuQiUtil.printDoubleList(stack.head);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        stack.add(5);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("-------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
        System.out.println("peek = " + stack.peek());
        System.out.println("pop = " + stack.pop());
    }
}
