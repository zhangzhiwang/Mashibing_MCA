package com.mashibing.dailyPractice.round2._0222;

import com.mashibing.list.SingleNode;

/**
 * 用单链表实现栈，栈的功能有：新增元素、弹出元素、查看栈顶元素值、是否为空、栈大小。
 */
public class StackBySingleList_0222 {
    private SingleNode<Integer> head;
    private int size;

    public void add(int num) {
        SingleNode<Integer> node = new SingleNode<>(num);
        if(head == null) {
            head = node;
        } else {
            node.next = head;
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
        StackBySingleList_0222 stack = new StackBySingleList_0222();
        stack.add(1);
        stack.add(2);
        stack.add(3);

        System.out.println(stack.size);
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.head.value);
        System.out.println("---------");

        System.out.println(stack.pop());
        System.out.println(stack.size);
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.head.value);
        System.out.println("---------");

        System.out.println(stack.pop());
        System.out.println(stack.size);
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.head.value);
        System.out.println("---------");

        System.out.println(stack.pop());
        System.out.println(stack.size);
        System.out.println(stack.isEmpty());
//        System.out.println(stack.peek());
        System.out.println(stack.head);
        System.out.println("---------");
    }
}
