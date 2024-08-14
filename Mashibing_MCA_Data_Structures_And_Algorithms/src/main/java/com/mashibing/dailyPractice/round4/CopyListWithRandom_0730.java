package com.mashibing.dailyPractice.round4;

import com.mashibing.list.CopyListWithRandom;

/**
 * 给定一个单链表，每一个节点Node都有两个指针，一个是指向它下一个节点的next指针，通过next指针可以组成一个正常的单链表；
 * 还有一个random指针，该指针可以指向链表中随机的任意一个节点，也可以指向null。要求复制一个这样的链表出来，返回复制链表的头部，原链表不能破坏。
 */
public class CopyListWithRandom_0730 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }

        Node c = head;
        Node n = head;
        while (c != null) {
            n = c.next;
            Node node = new Node(c.val);
            c.next = node;
            node.next = n;
            c = n;
        }

        c = head;
        while (c != null) {
            c.next.random = c.random == null ? null : c.random.next;
            c = c.next.next;
        }

        c = head;
        n = head;
        Node nH = null;
        Node nC = null;
        while (c != null) {
            n = c.next.next;
            if(nH == null) {
                nC = nH = c.next;
            } else {
                nC = nC.next = c.next;
            }
            c.next = n;
            c = n;
        }

        return nH;
    }

    public static void main(String[] args) {
        // 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
        
        Node n1 = new CopyListWithRandom_0730().new Node(1);
        Node n2 = new CopyListWithRandom_0730().new Node(2);
        Node n3 = new CopyListWithRandom_0730().new Node(3);
        n1.next = n2;
        n2.next = n3;
        n1.random = n3;
        n2.random = n1;
        n3.random = n3;

        System.out.println("原始链表：");
        Node cur=n1;
        while(cur != null) {
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
        System.out.println();
        System.out.println("---------------");

        cur=n1;
        while(cur != null) {
            System.out.print(cur.val + " => " + (cur.random == null ? "null" : cur.random.val));
            cur = cur.next;
            System.out.println();
        }
        System.out.println("---------------");

        System.out.println("拷贝后的新链表：");
        Node copyHead = new CopyListWithRandom_0730().copyRandomList(n1);
        cur=copyHead;
        while(cur != null) {
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
        System.out.println();
        System.out.println("---------------");

        cur=copyHead;
        while(cur != null) {
            System.out.print(cur.val + " => " + (cur.random == null ? "null" : cur.random.val));
            cur = cur.next;
            System.out.println();
        }
        System.out.println("---------------");
    }
}
