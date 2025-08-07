package com.mashibing.dailyPractice.round5;

/**
 * 给定一个单向链表，每k个节点分为一组，每组内的节点进行反转，不足k各节点的不分组也不反转（原样保持不变），最后组成一个新的完整的链表并返回新链表的头结点。
 */
public class ReverseNodesInKGroup_0819 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <= 1) {
            return head;
        }

        ListNode e = findEnd(head, k);
        if(e == null) {
            return head;
        }

        ListNode newHead = e;
        ListNode s = head;
        ListNode n = e.next;
        while (e != null) {
            reverseInGroup(s, k);
            s.next = n;
            e = findEnd(n, k);
            if(e == null) {
                return newHead;
            }

            s.next = e;
            s = n;
            n = e.next;
        }

        return newHead;
    }

    private ListNode findEnd(ListNode head, int k) {
        if(head == null) {
            return null;
        }

        while (k-- > 1) {
            head = head.next;
            if(head == null) {
                return null;
            }
        }

        return head;
    }

    private void reverseInGroup(ListNode s, int k) {
        ListNode n = s;
        ListNode p = null;
        while (k-- > 0) {
            n = s.next;
            s.next = p;
            p = s;
            s = n;
        }
    }

    public static void main(String[] args) {
        // leetcode：https://leetcode.com/problems/reverse-nodes-in-k-group/
    }
}
