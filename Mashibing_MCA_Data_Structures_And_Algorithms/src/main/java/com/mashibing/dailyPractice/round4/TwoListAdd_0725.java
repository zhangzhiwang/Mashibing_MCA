package com.mashibing.dailyPractice.round4;

/**
 * 给定两个链表的头节点head1和head2，每一个链表都认为从左到右是某个数字的低位到高位，返回两个数字相加之后的结果所代表的的链表（两个数都是非负数）。
 */
public class TwoListAdd_0725 {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
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
        ListNode retHead = longHead;

        int tmp = 0;
        ListNode tmpNode = null;
        while (shortHead != null) {
            int value = longHead.val + shortHead.val + (tmp == 0 ? 0 : tmp--);
            if(value >= 10) {
                value %= 10;
                tmp++;
            }
            longHead.val = value;

            tmpNode = longHead;
            longHead = longHead.next;
            shortHead = shortHead.next;
        }

        while (longHead != null) {
            int value = longHead.val + (tmp == 0 ? 0 : tmp--);
            if(value >= 10) {
                value %= 10;
                tmp++;
            }
            longHead.val = value;

            tmpNode = longHead;
            longHead = longHead.next;
        }

        if(tmp != 0) {
            ListNode node = new ListNode(tmp);
            tmpNode.next = node;
        }

        return retHead;
    }

    private int size(ListNode node) {
        int size = 0;
        while (node != null) {
            node = node.next;
            size++;
        }

        return size;
    }

    public static void main(String[] args) {
        // leetcode测试地址：https://leetcode.com/problems/add-two-numbers/
    }
}
