package com.mashibing.dailyPractice.round3._No21_30;

import com.mashibing.list.SingleNode;

/**
 * 用单链表实现栈，栈的功能有：新增元素、弹出元素、查看栈顶元素值、是否为空、栈大小。
 */
public class StackBySingleList_0306 {
    private SingleNode<Integer> head;
    private int size;

    public void add(int num) {
        SingleNode<Integer> n = new SingleNode<>(num);
        n.next = head;
        head = n;
        size++;
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("栈已空");
        }

        int r = head.value;
        head = head.next;
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
        StackBySingleList_0306 s = new StackBySingleList_0306();
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
