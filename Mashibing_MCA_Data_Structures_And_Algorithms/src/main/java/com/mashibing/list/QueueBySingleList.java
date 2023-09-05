package com.mashibing.list;

/**
 * 使用单链表构造队列
 * 课程：新手班课时28
 * 思路：见com.mashibing.preInEclipse.junior.list.QueueBySingleList注释
 */
public class QueueBySingleList<T> {
    private SingleNode<T> head;
    private SingleNode<T> tail;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 添加元素
     * @param value
     */
    public void offer(T value) {
        SingleNode cur = new SingleNode(value);
        if(tail == null) {
            tail = cur;
            head = cur;
        } else {
            tail.next = cur;
            tail = cur;
        }

        size++;
    }

    public T pop() {
        if(head == null) {
            return null;
        }

        T data = head.data;
        head = head.next;
        size--;
        if(head == null) {// 防止tail造成内存泄漏
            tail = null;
        }
        return data;
    }

    public T peek() {
        if(head == null) {
            return null;
        }

       return head.data;
    }





    public static void main(String[] args) {
        QueueBySingleList<Integer> queue = new QueueBySingleList<>();
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());

        queue.offer(3);
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        queue.offer(5);
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());

        System.out.println("---------");
        Integer res = queue.pop();
        System.out.println("res = " + res);
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        System.out.println("---------");
        res = queue.pop();
        System.out.println("res = " + res);
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());

        System.out.println("---------");
        System.out.println(queue.peek());
        queue.offer(1);
        System.out.println(queue.peek());
    }
}
