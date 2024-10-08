package com.mashibing.dailyPractice.round4;

import com.mashibing.list.DoubleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 用双向链表实现栈
 */
public class StackByDoubleLinkedList_0723 {
    private DoubleNode<Integer> head;
    private int size;

    public void add(int i) {
        DoubleNode<Integer> node = new DoubleNode<>(i);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head.last = node;
            head = node;
        }
        size++;
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("Stack is empty!");
        }

        int result = head.value;
        head = head.next;
        if (head != null) {
            head.last = null;
        }

        size--;
        return result;
    }

    public int peek() {
        if (size == 0) {
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
        StackByDoubleLinkedList_0723 stack = new StackByDoubleLinkedList_0723();
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

        System.out.println("pop = " + stack.pop());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());
//        System.out.println("peek = " + stack.peek());
//        System.out.println("pop = " + stack.pop());
    }
}