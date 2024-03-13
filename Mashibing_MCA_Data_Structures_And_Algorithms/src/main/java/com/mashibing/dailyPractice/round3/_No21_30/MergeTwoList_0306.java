package com.mashibing.dailyPractice.round3._No21_30;

import com.mashibing.dailyPractice.round1._1_29.MergeTwoList_0129;

/**
 * 给定两个有序链表的头部h1和h2，合并这两个链表后组成一个新的有序链表，返回新链表的头部
 */
public class MergeTwoList_0306 {
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
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }

        ListNode smallHead = list1.val <= list2.val ? list1 : list2;
        ListNode bigHead = smallHead == list1 ? list2 : list1;
        ListNode c1 = smallHead.next;
        ListNode c2 = bigHead;
        ListNode tmp = smallHead;
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
        if(c1 != null) {
            tmp.next = c1;
        } else {
            tmp.next = c2;
        }

        return smallHead;
    }
}
