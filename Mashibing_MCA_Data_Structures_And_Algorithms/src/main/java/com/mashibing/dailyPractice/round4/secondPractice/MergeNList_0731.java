package com.mashibing.dailyPractice.round4.secondPractice;

import com.mashibing.dailyPractice.round4.MergeNList_0726;
import com.mashibing.others.DuiShuQiUtil;

import java.util.Arrays;
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
public class MergeNList_0731 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        //        heap.addAll(Arrays.asList(lists));// leetcode的测试用例里面ListNode数组有元素为null的情况，所以要用循环往堆里面添加数据
        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }
            heap.add(node);
        }


        ListNode h = null;
        ListNode c = null;
        while (!heap.isEmpty()) {
            ListNode poll = heap.poll();
            if (h == null) {
                c = h = poll;
            } else {
                c.next = poll;
                c = poll;
            }

            if(poll.next != null) {
                heap.add(poll.next);
            }
        }

        return h;
    }

    public static void main(String[] args) {
        // leetcode:https://leetcode.com/problems/merge-k-sorted-lists/

        MergeNList_0731 m = new MergeNList_0731();

        ListNode node1 = m.new ListNode(1);
        ListNode node1_4 = m.new ListNode(4);
        ListNode node1_4_5 = m.new ListNode(5);
        node1.next = node1_4;
        node1_4.next = node1_4_5;

        ListNode node2 = m.new ListNode(1);
        ListNode node2_3 = m.new ListNode(3);
        ListNode node2_4 = m.new ListNode(4);
        node2.next = node2_3;
        node2_3.next = node2_4;

        ListNode node3 = m.new ListNode(2);
        ListNode node3_6 = m.new ListNode(6);
        node3.next = node3_6;

        ListNode[] lists = new ListNode[3];
        lists[0] = node1;
        lists[1] = node2;
        lists[2] = node3;
        System.out.println(m.mergeKLists(lists).val);
    }
}
