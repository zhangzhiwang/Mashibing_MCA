package com.mashibing.dailyPractice.round1._1_29;

/**
 * 给定两个有序链表的头部h1和h2，合并这两个链表后组成一个新的有序链表，返回新链表的头部
 */
public class MergeTwoList_0129 {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null) {
            return null;
        } else if(list1 != null && list2 == null) {
            return list1;
        } else if(list2 != null && list1 == null) {
            return list2;
        }

        ListNode smallHead = list1.val <= list2.val ? list1 : list2;
        ListNode bigHead = smallHead == list1 ? list2 : list1;
        if(smallHead.next == null) {
            smallHead.next = bigHead;
            return smallHead;
        }

        ListNode c1 = smallHead.next;
        ListNode c2 = bigHead;
        ListNode p = smallHead;
        while(c1 != null && c2 != null) {
            if(c1.val > c2.val) {
                p.next = c2;
                p = c2;
                c2 = c2.next;
            } else {
                p.next = c1;
                p = c1;
                c1 = c1.next;
            }
        }

        p.next = c1 == null ? c2 : c1;

        return smallHead;
    }

    public static void main(String[] args) {
        // 测试：https://leetcode.com/problems/merge-two-sorted-lists
    }
}
