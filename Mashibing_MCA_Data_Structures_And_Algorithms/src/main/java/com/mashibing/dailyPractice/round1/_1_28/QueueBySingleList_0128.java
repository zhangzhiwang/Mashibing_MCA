package com.mashibing.dailyPractice.round1._1_28;

import com.mashibing.list.SingleNode;

/**
 * 用单链表实现队列，队列的功能有：新增元素、弹出元素、查看队首元素值、是否为空、队列大小。
 */
public class QueueBySingleList_0128<T> {
    private SingleNode<T> head;
    private SingleNode<T> tail;
    private int size;

    public void add(T t) {
        SingleNode<T> node = new SingleNode<>(t);
        if(tail == null) {
            head = node;
        } else {
            tail.next = node;
        }

        tail = node;
        size++;
    }

    public T pop() {
        if(head == null) {
            return null;
        }

        T result = head.value;
        head = head.next;
        if(head == null) {// 这个很关键，当将队列最后一个元素弹出去之后，由于tail还在指着最后一个元素，为了避免内存泄漏，要将tail指向空
            tail = null;
        }
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
        QueueBySingleList_0128<Integer> queue = new QueueBySingleList_0128<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.peek());
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        System.out.println("-----------");
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
    }
}
