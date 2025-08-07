package com.mashibing.dailyPractice.round5;

/**
 * 给定两个有序链表的头部h1和h2，合并这两个链表后组成一个新的有序链表，返回新链表的头部
 */
public class MergeTwoList_0819 {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null) {
            return null;
        } else if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }

        ListNode smallHead = list1.val <= list2.val ? list1 : list2;
        ListNode bigHead = smallHead == list1 ? list2 : list1;
        ListNode c = smallHead;
        ListNode p1 = smallHead.next;
        ListNode p2 = bigHead;
        while (p1 != null && p2 != null) {
            if(p1.val >= p2.val) {
                c.next = p2;
                c = p2;
                p2 = p2.next;
            } else {
                c.next = p1;
                c = p1;
                p1 = p1.next;
            }
        }

        if(p1 == null) {
            c.next = p2;
        } else {
            c.next = p1;
        }

        return smallHead;
    }

    public static void main(String[] args) {
        // 测试：https://leetcode.com/problems/merge-two-sorted-lists
    }
}
