package com.mashibing.dailyPractice.round3._No61_70;

import com.mashibing.dailyPractice.round1._2_14.CopyListWithRandom_0214;

/**
 * 给定一个单链表，每一个节点Node都有两个指针，一个是指向它下一个节点的next指针，通过next指针可以组成一个正常的单链表；
 * 还有一个random指针，该指针可以指向链表中随机的任意一个节点，也可以指向null。要求复制一个这样的链表出来，返回复制链表的头部，原链表不能破坏。
 */
public class CopyListWithRandom_0312 {
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
        Node n = head;
        while(c != null) {
            n = c.next;
            Node node = new Node(c.val);
            c.next = node;
            node.next = n;
            c = n;
        }

        c = head;
        n = c;
        while(c != null) {
            n = c.next.next;
            if(c.random != null) {
                Node node = new Node(c.random.val);
                c.next.random = node;
            }
            c = n;
        }

        Node newHead = null;
        Node newC = null;
        c = head;
        n = c;
        while(c != null) {
            if(newHead == null) {
                newHead = c.next;
                newC = c.next;
            }

            n = c.next.next;
            c.next = n;
            if(n != null) {
                newC.next = n.next;
                newC = n.next;
            }
            c = n;
        }

        return newHead;
    }
}