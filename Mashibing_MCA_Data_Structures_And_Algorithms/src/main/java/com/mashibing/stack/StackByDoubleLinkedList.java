package com.mashibing.stack;

import com.mashibing.list.DoubleNode;

/**
 * 双向链表实现栈
 * 课程：体系班课时22
 * 思路：见com.mashibing.preInEclipse.senior.stack.StackByDoubleLinkedList注释
 */
public class StackByDoubleLinkedList {
    private DoubleNode<Integer> head;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(Integer num) {
        DoubleNode<Integer> cur = new DoubleNode<>(num);
        if(head != null) {
            head.next = cur;
            cur.last = head;
        }
        head = cur;
        size++;
    }

    public int pop() {
        if(isEmpty()) {
            throw new RuntimeException("栈已空！");
        }

        int result = head.data;
        head = head.last;
        if(head != null) {
            head.next = null;// 避免被弹出的元素内存泄漏
        }
        size--;
        return result;
    }

    public int peek() {
        if(isEmpty()) {
            throw new RuntimeException("栈已空！");
        }

        return head.data;
    }

    public static void main(String[] args) {
        StackByDoubleLinkedList sbdll = new StackByDoubleLinkedList();
        System.out.println(sbdll.isEmpty());
        System.out.println(sbdll.size());
        System.out.println("------------");

        sbdll.add(1);
        System.out.println(sbdll.isEmpty());
        System.out.println(sbdll.size());
        System.out.println("peek:" + sbdll.peek());
        System.out.println("------------");

        sbdll.add(2);
        sbdll.add(3);
        sbdll.add(4);
        sbdll.add(5);
        System.out.println(sbdll.isEmpty());
        System.out.println(sbdll.size());
        System.out.println("peek:" + sbdll.peek());
        System.out.println("------------");

        int result = sbdll.pop();
        System.out.println("result = " + result);
        System.out.println(sbdll.size());
        System.out.println("peek:" + sbdll.peek());
        System.out.println("------------");

        sbdll.pop();
        sbdll.pop();
        sbdll.pop();
        sbdll.pop();
        System.out.println(sbdll.isEmpty());
        System.out.println(sbdll.size());
//        System.out.println("peek:" + sbdll.peek());
    }
}
