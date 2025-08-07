package com.mashibing.dailyPractice.round6;

/**
 * 给定两个链表的头节点head1和head2，每一个链表都认为从左到右是某个数字的低位到高位，返回两个数字相加之后的结果所代表的的链表（两个数都是非负数）。
 */
public class TwoListAdd_1030 {
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

        int size1 = size(l1);
        int size2 = size(l2);
        ListNode longHead = size1 >= size2 ? l1 : l2;
        ListNode shortHead = longHead == l1 ? l2 : l1;
        ListNode retLongHead = longHead;

        int t = 0;
        ListNode follow = longHead;
        while (shortHead != null) {
            int v = shortHead.val + longHead.val + (t == 0 ? t : t--);
            if(v >= 10) {
                v %= 10;
                t++;
            }
            longHead.val = v;

            shortHead = shortHead.next;
            follow = longHead;
            longHead = longHead.next;
        }

        while (longHead != null) {
            int v = longHead.val + (t == 0 ? t : t--);
            if(v >= 10) {
                v %= 10;
                t++;
            }
            longHead.val = v;

            follow = longHead;
            longHead = longHead.next;
        }

        if(t != 0) {
            ListNode node = new ListNode(t);
            follow.next = node;
        }

        return retLongHead;
    }

    private int size(ListNode l) {
        int size = 0;
        while (l != null) {
            l = l.next;
            size++;
        }

        return size;
    }
}
