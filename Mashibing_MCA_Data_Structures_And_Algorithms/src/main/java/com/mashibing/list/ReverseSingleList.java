package com.mashibing.list;

/**
 * 单链表的反转
 * 课程：新手班课时26
 * 思路：见com.mashibing.preInEclipse.junior.list.ReverseSingleList注释
 */
public class ReverseSingleList {
    public static void main(String[] args) {
        SingleNode n1 = new SingleNode(1);
        SingleNode n2 = new SingleNode(2);
        SingleNode n3 = new SingleNode(3);
        n1.next = n2;
        n2.next = n3;

        System.out.println(n1.data + " -> " + n1.next.data + " -> " + n1.next.next.data);
        n1 = reverseSingleList(n1);
        System.out.println(n1.data + " -> " + n1.next.data + " -> " + n1.next.next.data);
    }

    public static SingleNode reverseSingleList(SingleNode head) {
        if(head == null) {
            return null;
        }

        SingleNode pre = null;
        SingleNode next = null;
        while(head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }
}
