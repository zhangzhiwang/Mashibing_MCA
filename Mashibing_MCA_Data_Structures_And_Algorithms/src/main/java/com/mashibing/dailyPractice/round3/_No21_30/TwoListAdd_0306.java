package com.mashibing.dailyPractice.round3._No21_30;

import com.mashibing.dailyPractice.round1._1_29.TwoListAdd_0129;

/**
 * 给定两个链表的头节点head1和head2，每一个链表都认为从左到右是某个数字的低位到高位，返回两个数字相加之后的结果所代表的的链表（两个数都是非负数）。
 */
public class TwoListAdd_0306 {
    /**
     * 由于本题目要在leetcode上去验证，所以要使用leetcode给出的节点类
     */
    static class ListNode {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            return null;
        }

        int size1 = listSize(l1);
        int size2 = listSize(l2);
        ListNode longHead = size1 >= size2 ? l1 : l2;
        ListNode shortHead = longHead == l1 ? l2 : l1;

        ListNode lH = longHead;
        ListNode tmpNode = lH;
        int tmp = 0;
        while(lH != null && shortHead != null) {
            int v = shortHead.val + lH.val + (tmp == 0 ? tmp : tmp--);
            tmp = v >= 10 ? ++tmp : tmp;
            lH.val = v % 10;
            tmpNode = lH;
            lH = lH.next;
            shortHead = shortHead.next;
        }

        while(lH != null) {
            int v = lH.val + (tmp == 0 ? tmp : tmp--);
            tmp = v >= 10 ? ++tmp : tmp;
            lH.val = v % 10;
            tmpNode = lH;
            lH = lH.next;
        }

        if(tmp > 0) {
            ListNode n = new ListNode(1);
            tmpNode.next = n;
        }

        return longHead;
    }

    private int listSize(ListNode head) {
        int size = 1;
        while(head != null) {
            head = head.next;
            size++;
        }

        return size;
    }
}
