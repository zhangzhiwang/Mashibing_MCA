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

        System.out.println(n1.value + " -> " + n1.next.value + " -> " + n1.next.next.value);
        System.out.println(n3.value + " => " + n3.last.value + " => " + n3.last.last.value);
        System.out.println("----------------");

        n1 = reverseDoubleList(n1);
        System.out.println(n1.value + " -> " + n1.next.value + " -> " + n1.next.next.value);
        System.out.println(n1.next.next.value + " => " + n1.next.next.last.value + " => " + n1.next.next.last.last.value);
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
