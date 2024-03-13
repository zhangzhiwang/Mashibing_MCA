package com.mashibing.list;

/**
 * 用单链表实现栈
 * 课程：新手班课时29
 * 思路：见com.mashibing.preInEclipse.junior.list.StackBySingleList注释
 */
public class StackBySingleList<T> {
    private SingleNode<T> head;
    private int size;

    public void put(T value) {
        SingleNode<T> cur = new SingleNode<>(value);
        if(head == null) {
            head = cur;
        } else {
            cur.next = head;
            head = cur;
        }

        size++;
    }

    public T pop() {
        if(head == null) {
            return null;
        }

        T res = head.value;
        head = head.next;
        size--;
        return res;
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
        StackBySingleList<Integer> stack = new StackBySingleList<>();
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());

        stack.put(3);
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());
        stack.put(5);
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());
        stack.put(7);
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());

        System.out.println("----------");
        Integer res = stack.pop();
        System.out.println("res = " + res);
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());
        System.out.println("----------");
        res = stack.pop();
        System.out.println("res = " + res);
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());
        System.out.println("----------");
        res = stack.pop();
        System.out.println("res = " + res);
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());
        res = stack.pop();
        System.out.println("res = " + res);
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());
    }
}
