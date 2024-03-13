package com.mashibing.dailyPractice.round3._No21_30;

/**
 * 给定一个单向链表，每k个节点分为一组，每组内的节点进行反转，不足k各节点的不分组也不反转（原样保持不变），最后组成一个新的完整的链表并返回新链表的头结点。
 */
public class ReverseNodesInKGroup_0306 {
    /**
     * leetcode给出的已知类
     */
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
        if(head == null || head.next == null || k < 2) {
            return head;
        }

        ListNode end = findEnd(head, k);
        if(end == null) {
            return head;
        }
        ListNode newHead = end;
        ListNode start = head;
        while(start != null) {
            reverseInGroup(start, end);
            ListNode tmp = start.next;
            end = findEnd(tmp, k);
            if(end == null) {
                break;
            }

            start.next = end;
            start = tmp;
        }

        return newHead;
    }

    private ListNode findEnd(ListNode s, int k) {
        while(--k > 0 && s != null) {
            s = s.next;
        }
        return s;
    }

    private void reverseInGroup(ListNode h, ListNode e) {
        ListNode c = h;
        ListNode p = null;
        ListNode n = c;
        e = e.next;
        while (c != e) {
            n = c.next;
            c.next = p;
            p = c;
            c = n;
        }
        h.next = e;
    }
}
