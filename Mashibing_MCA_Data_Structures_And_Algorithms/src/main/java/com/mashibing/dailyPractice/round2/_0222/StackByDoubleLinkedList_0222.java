package com.mashibing.dailyPractice.round2._0222;

import com.mashibing.list.DoubleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 用双向链表实现栈
 */
public class StackByDoubleLinkedList_0222 {
    private DoubleNode<Integer> head;
    private int size;

    public void add(int num) {
        DoubleNode<Integer> node = new DoubleNode<>(num);
        if(head == null) {
            head = node;
        } else {
            node.next = head;
            head.last = node;
            head = node;
        }
        size++;
    }

    public int pop() {
        if(head == null) {
            throw new RuntimeException("队列已空");
        }

        int result = head.value;
        head = head.next;
        if(head != null) {
            head.last = null;
        }
        size--;
        return result;
    }

    public int peek() {
        if(head == null) {
            throw new RuntimeException("队列已空");
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
        StackByDoubleLinkedList_0222 stack = new StackByDoubleLinkedList_0222();
        stack.add(1);
        stack.add(2);
        stack.add(3);

//        DuiShuQiUtil.printDoubleList(stack.head);
        System.out.println(stack.size);
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println("----------");

        System.out.println(stack.pop());
        System.out.println(stack.size);
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        DuiShuQiUtil.printDoubleList(stack.head);
        System.out.println("----------");

        System.out.println(stack.pop());
        System.out.println(stack.size);
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        DuiShuQiUtil.printDoubleList(stack.head);
        System.out.println("----------");

        System.out.println(stack.pop());
        System.out.println(stack.size);
        System.out.println(stack.isEmpty());
//        System.out.println(stack.peek());
        System.out.println(stack.head);
        System.out.println("----------");
    }
}
