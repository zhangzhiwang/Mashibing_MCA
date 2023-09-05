package com.mashibing.list;

/**
 * 反转双向链表
 * 课程：新手班课时27
 * 思路：见com.mashibing.preInEclipse.junior.list.ReverseDoubleList注释
 */
public class ReverseDoubleList {
    public static void main(String[] args) {
        DoubleNode n1 = new DoubleNode(1);
        DoubleNode n2 = new DoubleNode(2);
        DoubleNode n3 = new DoubleNode(3);
        n1.next = n2;
        n2.next = n3;
        n2.last = n1;
        n3.last = n2;

        System.out.println(n1.data + " -> " + n1.next.data + " -> " + n1.next.next.data);
        System.out.println(n3.data + " => " + n3.last.data + " => " + n3.last.last.data);
        System.out.println("----------------");

        n1 = reverseDoubleList(n1);
        System.out.println(n1.data + " -> " + n1.next.data + " -> " + n1.next.next.data);
        System.out.println(n1.next.next.data + " => " + n1.next.next.last.data + " => " + n1.next.next.last.last.data);
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        if (head == null) {
            return null;
        }

        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }

        return pre;
    }
}
