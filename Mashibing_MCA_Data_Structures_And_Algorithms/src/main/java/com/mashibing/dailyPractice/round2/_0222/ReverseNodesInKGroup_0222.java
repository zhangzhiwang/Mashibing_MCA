package com.mashibing.dailyPractice.round2._0222;

/**
 * 给定一个单向链表，每k个节点分为一组，每组内的节点进行反转，不足k各节点的不分组也不反转（原样保持不变），最后组成一个新的完整的链表并返回新链表的头结点。
 */
public class ReverseNodesInKGroup_0222 {
    /**
     * leetcode
     */
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k < 2) {
            return head;
        }

        ListNode end = findKEnd(head, k);
        if(end == null) {
            return head;
        }

        ListNode newHead = end;
        ListNode start = head;
        ListNode next = null;
        while(start != null) {
            next = end.next;
            reverseInGroup(start, end);
            end = findKEnd(next, k);
            if(end == null) {
                start.next = next;
                return newHead;
            } else {
                start.next = end;
            }
            start = next;
        }

        return newHead;
    }

    private ListNode findKEnd(ListNode head, int k) {
        if(head == null) {
            return null;
        }

        while(k-- > 1) {
            head = head.next;
            if(head == null) {
                break;
            }
        }
        return head;
    }

    private void reverseInGroup(ListNode start, ListNode end) {
        ListNode pre = null;
        ListNode next = start;
        while(start != end) {
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        start.next = pre;
    }
}
