package com.mashibing.list;

/**
 * 用双向链表实现双端队列
 * 课程：新手班课时30
 * 思路：见com.mashibing.preInEclipse.junior.list.DequeByDoubleList注释
 */
public class DequeByDoubleList<T> {
    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int size;

    /**
     * 从头部加入数据
     * @param value
     */
    public void headPush(T value) {
        DoubleNode cur = new DoubleNode(value);
        if(head == null) {
            head = cur;
            tail = cur;
        } else {
            cur.next = head;
            head.last = cur;
            head = cur;
        }

        size++;
    }

    /**
     * 从尾部加入数据
     * @param value
     */
    public void tailPush(T value) {
        DoubleNode cur = new DoubleNode(value);
        if(tail == null) {
            head = cur;
            tail = cur;
        } else {
            tail.next = cur;
            cur.last = tail;
            tail = cur;
        }

        size++;
    }

    /**
     * 从头部弹出数据
     * @return
     */
    public T headPop() {
        if(head == null) {
            return null;
        }

        T res = head.value;
        head = head.next;
        if(head == null) {// head往后移动一个之后变为了空，说明原先就只有一个元素
            tail = null;// 防止被弹出的元素内存泄漏
        } else {
            head.last = null;
        }

        size--;
        return res;
    }

    /**
     * 从尾部弹出数据
     * @return
     */
    public T tailPop() {
        if(tail == null) {
            return null;
        }

        T res = tail.value;
        tail = tail.last;
        if(tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        size--;
        return res;
    }

    /**
     * 查看头部元素
     * @return
     */
    public T headPeek() {
        if(head == null) {
            return null;
        }

        return head.value;
    }

    /**
     * 查看尾部元素
     * @return
     */
    public T tailPeek() {
        if(tail == null) {
            return null;
        }

        return tail.value;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        DequeByDoubleList<Integer> dequeue = new DequeByDoubleList<>();
        System.out.println(dequeue.size());

        dequeue.headPush(1);
        dequeue.headPush(2);
        dequeue.headPush(3);
        dequeue.tailPush(4);
        dequeue.tailPush(5);
        dequeue.tailPush(6);
        System.out.println(dequeue.size());

        System.out.println("----------");
//        for(int i = 0; i < 6; i++) {
//            System.out.print(dequeue.headPop() + "\t");
//        }
//        System.out.println();

        for(int i = 0; i < 6; i++) {
            System.out.print(dequeue.tailPop() + "\t");
        }
        System.out.println();
    }
}
