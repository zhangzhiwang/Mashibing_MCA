package com.mashibing.dailyPractice.round1._2_14;

/**
 * 给定一个单链表，每一个节点Node都有两个指针，一个是指向它下一个节点的next指针，通过next指针可以组成一个正常的单链表；
 * 还有一个random指针，该指针可以指向链表中随机的任意一个节点，也可以指向null。要求复制一个这样的链表出来，返回复制链表的头部，原链表不能破坏。
 */
public class CopyListWithRandom_0214 {
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

        Node cur = head;
        Node next = cur;
        while(cur != null) {
            next = cur.next;
            Node node = new Node(cur.val);
            cur.next = node;
            node.next = next;
            cur = next;
        }

        cur = head;
        while(cur != null) {
            next = cur.next;
            next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }

        Node newHead = head.next;
        Node tmp = head;
        cur = newHead;
        while(cur.next != null) {
            next = cur.next;
            cur.next = cur.next.next;
            cur = cur.next;
            tmp.next = next;
            tmp = next;
        }
        tmp.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        // 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
    }
}
