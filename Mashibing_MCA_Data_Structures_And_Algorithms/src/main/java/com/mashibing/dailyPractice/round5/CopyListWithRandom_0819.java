package com.mashibing.dailyPractice.round5;

/**
 * 给定一个单链表，每一个节点Node都有两个指针，一个是指向它下一个节点的next指针，通过next指针可以组成一个正常的单链表；
 * 还有一个random指针，该指针可以指向链表中随机的任意一个节点，也可以指向null。要求复制一个这样的链表出来，返回复制链表的头部，原链表不能破坏。
 */
public class CopyListWithRandom_0819 {
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
            Node _c = new Node(c.val);
            c.next = _c;
            _c.next = n;
            c = n;
        }

        c = head;
        while (c != null) {
            n = c.next.next;
            c.next.random = c.random == null ? null : c.random.next;
            c = n;
        }

        c = head;
        Node copyHead = null;
        while (c != null) {
            if(copyHead == null) {
                copyHead = c.next;
            }
            n = c.next.next;
            c.next.next = n == null ? null : n.next;
            c.next = n;
            c = n;
        }

        return copyHead;
    }

    public static void main(String[] args) {
        // 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
    }
}
