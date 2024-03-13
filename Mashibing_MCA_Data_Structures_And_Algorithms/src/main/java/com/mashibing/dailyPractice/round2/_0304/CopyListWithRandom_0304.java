package com.mashibing.dailyPractice.round2._0304;

import com.mashibing.dailyPractice.round1._2_14.CopyListWithRandom_0214;

/**
 * 给定一个单链表，每一个节点Node都有两个指针，一个是指向它下一个节点的next指针，通过next指针可以组成一个正常的单链表；还有一个random指针，
 * 该指针可以指向链表中随机的任意一个节点，也可以指向null。要求复制一个这样的链表出来，返回复制链表的头部，原链表不能破坏。
 */
public class CopyListWithRandom_0304 {
    /**
     * leetcode给出的类
     */
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
        Node n = c;
        while(c != null) {
            n = c.next;
            Node node = new Node(c.val);
            c.next = node;
            node.next = n;
            c = n;
        }

        c = head;
        while (c != null) {
            c.next.random = c.random != null ? c.random.next : null;
            c = c.next.next;
        }

        c = head;
        n = c;
        Node newHead = null;
        Node newC = null;
        while (c != null) {
            if(newHead == null) {
                newHead = c.next;
                newC = newHead;
            } else {
                newC = newC.next = c.next;
            }
            n = c.next.next;
            c.next = n;
            c = n;
        }

        return newHead;
    }
}
