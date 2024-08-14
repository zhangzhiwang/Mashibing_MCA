package com.mashibing.dailyPractice.round4;

/**
 * 给定一个单向链表，每k个节点分为一组，每组内的节点进行反转，不足k各节点的不分组也不反转（原样保持不变），最后组成一个新的完整的链表并返回新链表的头结点。
 */
public class ReverseNodesInKGroup_0725 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        ListNode s = head;
        ListNode newHead = findStart(s, k);
        if (newHead == null) {
            return head;
        }

        head = newHead;
        while (newHead != null) {
            ListNode n = newHead.next;
            ListNode e = reverse(s, k);
            e.next = n;
            s = n;
            newHead = findStart(s, k);
            if(newHead != null) {
                e.next = newHead;
            }
        }

        return head;
    }

    private ListNode findStart(ListNode node, int k) {
        if(node == null) {
            return null;
        }

        while (node != null && --k > 0) {
            node = node.next;
        }

        return node;
    }

    private ListNode reverse(ListNode h, int k) {
        ListNode e = h;
        ListNode n = h;
        ListNode p = null;
        while(k-- > 0) {
            n = h.next;
            h.next = p;
            p = h;
            h = n;
        }
        return e;
    }

    public static void main(String[] args) {
        // leetcode：https://leetcode.com/problems/reverse-nodes-in-k-group/
    }
}
