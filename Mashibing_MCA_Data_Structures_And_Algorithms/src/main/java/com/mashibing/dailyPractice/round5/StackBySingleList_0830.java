package com.mashibing.dailyPractice.round5;

import com.mashibing.dailyPractice.round4.StackBySingleList_0722;
import com.mashibing.list.SingleNode;

/**
 * 用单链表实现栈，栈的功能有：新增元素、弹出元素、查看栈顶元素值、是否为空、栈大小。
 */
public class StackBySingleList_0830 {
    private SingleNode<Integer> head;
    private int size;

    public void add(int n) {
        SingleNode<Integer> node = new SingleNode<>(n);
        if (head != null) {
            node.next = head;
        }
        head = node;
        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("Stack is empty!");
        }

        int result = head.value;
        head = head.next;
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
        StackBySingleList_0830 stack = new StackBySingleList_0830();
        System.out.println("size = " + stack.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        stack.add(1);
        stack.add(2);
        stack.add(3);
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
