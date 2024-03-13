package com.mashibing.dailyPractice.round1._1_29;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 老师给的题目：
 * 给定一个数组，这个数组里面的每一个元素都是一个有序链表（单链表）的头部，即每一个元素都代表一个有序链表，将这些链表融合到一起形成一个新的有序链表，返回新链表的头部。
 *
 * leetcode原题：
 * 给你一个链表数组，每个链表都已经按升序排列，请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 比如：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 *
 * 下面的方法mergeKLists是老师题目的实现，leetcode原题在本实现基础上改造即可
 */
public class MergeNList_0129 {
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
        if(lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for(ListNode head : lists) {
            heap.add(head);
        }

        ListNode cur = null;
        ListNode head = null;
        while(!heap.isEmpty()) {
            ListNode node = heap.poll();
            if(cur == null) {
                cur = node;
                head = node;
            } else {
                cur.next = node;
                cur = node;
            }
            if(node.next != null) {
                heap.add(node.next);
            }
        }

        return head;
    }

    public static void main(String[] args) {
        // [[1,4,5],[1,3,4],[2,6]]
        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(1);
        ListNode h3 = new ListNode(2);

        ListNode n4_1 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        h1.next = n4_1;
        n4_1.next = n5;

        ListNode n3 = new ListNode(3);
        ListNode n4_2 = new ListNode(4);
        h2.next = n3;
        n3.next = n4_2;

        ListNode n6 = new ListNode(6);
        h3.next = n6;

        ListNode[] lists = {h1, h2, h3};
        ListNode listNode = new MergeNList_0129().mergeKLists(lists);// [1,1,2,3,4,4,5,6]
        while(listNode != null) {
            System.out.print(listNode.val + " -> ");
            listNode = listNode.next;
        }
        System.out.println();
    }
}
