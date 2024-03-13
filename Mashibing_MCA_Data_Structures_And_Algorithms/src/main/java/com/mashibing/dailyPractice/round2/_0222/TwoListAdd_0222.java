package com.mashibing.dailyPractice.round2._0222;

import com.mashibing.dailyPractice.round1._1_28.ReverseNodesInKGroup_0128;

/**
 * 给定两个链表的头节点head1和head2，每一个链表都认为从左到右是某个数字的低位到高位，返回两个数字相加之后的结果所代表的的链表（两个数都是非负数）。
 */
public class TwoListAdd_0222 {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            return null;
        }

        int size1 = listSize(l1);
        int size2 = listSize(l2);
        ListNode longHead = size1 >= size2 ? l1 : l2;
        ListNode shortHead = longHead == l1 ? l2 : l1;

        int tmp = 0;
        ListNode head1 = longHead;
        ListNode head2 = shortHead;
        ListNode tmpNode = null;
        while(head1 != null && head2 != null) {
            int value = head1.val + head2.val + tmp;
            if(tmp > 0) {
                tmp--;
            }
            tmp += value >= 10 ? 1 : 0;
            head1.val = value % 10;

            tmpNode = head1;
            head1 = head1.next;
            head2 = head2.next;
        }

        while(head1 != null) {
            int value = head1.val + tmp;
            if(tmp > 0) {
                tmp--;
            }
            tmp += value >= 10 ? 1 : 0;
            head1.val = value % 10;

            tmpNode = head1;
            head1 = head1.next;
        }

        if(tmp > 0) {
            ListNode node = new ListNode(tmp);
            tmpNode.next = node;
        }

        return longHead;
    }

    private static int listSize(ListNode head) {
        int size = 0;
        while(head != null) {
            size++;
            head = head.next;
        }
        return size;
    }
}
