package com.mashibing.dailyPractice.round1._2_2;

import com.mashibing.list.DoubleNode;

/**
 * 用双向链表实现栈
 */
public class StackByDoubleLinkedList_0202 {
    private DoubleNode<Integer> head;
    private int size;

    public void add(int num) {
        DoubleNode<Integer> node = new DoubleNode<>(num);
        if(head == null) {
            head = node;
        } else {
            head.next = node;
            node.last = head;
            head = node;
        }
        size++;
    }

    public int pop() {
        if(head == null) {
            throw new RuntimeException("栈为空");
        }

        int result = head.value;
        head = head.last;
        if(head != null) {
            head.next = null;
        }

        size--;
        return result;
    }

    public int peek() {
        if(head == null) {
            throw new RuntimeException("栈为空");
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
        StackByDoubleLinkedList_0202 stack = new StackByDoubleLinkedList_0202();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println("-----------");

        System.out.println(stack.head.value + " -> " + stack.head.last.value + " -> " + stack.head.last.last.value+ " -> " + stack.head.last.last.last);
        DoubleNode<Integer> first = stack.head.last.last;
        System.out.println(first.value + " -> " + first.next.value + " -> " + first.next.next.value+ " -> " + first.next.next.next);
        System.out.println("-----------");

        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println("-----------");

        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println("-----------");

        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
//        System.out.println(stack.peek());
        System.out.println("-----------");
    }
}
