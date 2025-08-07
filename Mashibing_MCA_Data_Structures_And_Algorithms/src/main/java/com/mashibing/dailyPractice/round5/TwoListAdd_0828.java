package com.mashibing.dailyPractice.round5;

/**
 * 给定两个链表的头节点head1和head2，每一个链表都认为从左到右是某个数字的低位到高位，返回两个数字相加之后的结果所代表的的链表（两个数都是非负数）。
 */
public class TwoListAdd_0828 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        int len1 = size(l1);
        int len2 = size(l2);
        ListNode longHead = len1 >= len2 ? l1 : l2;
        ListNode shortHead = longHead == l1 ? l2 : l1;

        int t = 0;
        ListNode lH = longHead;
        ListNode lc = null;
        while (shortHead != null) {
            int v = lH.val + shortHead.val + (t == 0 ? t : t--);
            if(v >= 10) {
                v = v % 10;
                t++;
            }

            lH.val = v;
            lc = lH;
            lH = lH.next;
            shortHead = shortHead.next;
        }

        while (lH != null) {
            int v = lH.val + (t == 0 ? t : t--);
            if(v >= 10) {
                v = v % 10;
                t++;
            }

            lH.val = v;
            lc = lH;
            lH = lH.next;
        }

        if(t != 0) {
            ListNode node = new ListNode(t);
            lc.next = node;
        }

        return longHead;
    }

    private int size(ListNode l) {
        int len = 0;
        while (l != null) {
            len++;
            l = l.next;
        }
        return len;
    }

    public static void main(String[] args) {
        // leetcode测试地址：https://leetcode.com/problems/add-two-numbers/
    }
}