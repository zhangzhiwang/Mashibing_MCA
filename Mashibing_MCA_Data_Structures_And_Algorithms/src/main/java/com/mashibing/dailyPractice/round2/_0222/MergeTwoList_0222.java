package com.mashibing.dailyPractice.round2._0222;

/**
 * 给定两个有序链表的头部h1和h2，合并这两个链表后组成一个新的有序链表，返回新链表的头部
 */
public class MergeTwoList_0222 {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null) {
            return null;
        } else if (list1 == null ^ list2 == null) {
            if(list1 != null) {
                return list1;
            } else {
                return list2;
            }
        }

        ListNode smallHead = list1.val <= list2.val ? list1 : list2;
        ListNode bigHead = smallHead == list1 ? list2 : list1;
        ListNode tmp = smallHead;
        ListNode c1 = smallHead.next;
        ListNode c2 = bigHead;
        while(c1 != null && c2 != null) {
            if(c1.val >= c2.val) {
                tmp.next = c2;
                tmp = c2;
                c2 = c2.next;
            } else {
                tmp.next = c1;
                tmp = c1;
                c1 = c1.next;
            }
        }

        tmp.next = c1 == null ? c2 : c1;
        return smallHead;
    }
}
