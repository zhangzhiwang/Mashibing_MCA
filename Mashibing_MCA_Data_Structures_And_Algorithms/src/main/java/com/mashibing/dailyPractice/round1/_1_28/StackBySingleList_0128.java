package com.mashibing.dailyPractice.round1._1_28;

import com.mashibing.list.SingleNode;

/**
 * 用单链表实现栈，栈的功能有：新增元素、弹出元素、查看队首元素值、是否为空、队列大小。
 */
public class StackBySingleList_0128<T> {
    private SingleNode<T> head;
    private int size;

    public void add(T t) {
        SingleNode<T> node = new SingleNode<>(t);
        if(head != null) {
            node.next = head;
        }
        head = node;
        size++;
    }

    public T pop() {
        if(head == null) {
            return null;
        }

        T result = head.value;
        head = head.next;
        size--;
        return result;
    }

    public T peek() {
        if(head == null) {
            return null;
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
        StackBySingleList_0128 stack = new StackBySingleList_0128();
        stack.add(1);
        stack.add(2);
        stack.add(3);

        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println("-------------");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("peek = " + stack.peek());
        System.out.println("size = " + stack.size());
        System.out.println(stack.isEmpty());
    }
}
