package com.mashibing.dailyPractice.round3._No31_40;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组，这个数组里面的每一个元素都是一个有序链表（单链表）的头部，即每一个元素都代表一个有序链表，将这些链表融合到一起形成一个新的有序链表，返回新链表的头部。
 * leetcode原题：
 * 给你一个链表数组，每个链表都已经按升序排列，请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 比如：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeNList_0306 {
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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });

        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }

            heap.add(node);
        }
        if(heap.isEmpty()) {
            return null;
        }


        ListNode head = null;
        ListNode c = head;
        while(!heap.isEmpty()) {
            ListNode poll = heap.poll();
            if(head == null) {
                c = head = poll;
            } else {
                c.next = poll;
                c = poll;
            }

            if(poll.next != null) {
                heap.add(poll.next);
            }
        }

        return head;
    }
}
