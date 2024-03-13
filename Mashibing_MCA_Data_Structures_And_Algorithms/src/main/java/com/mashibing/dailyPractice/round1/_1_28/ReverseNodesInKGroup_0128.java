package com.mashibing.dailyPractice.round1._1_28;

import com.mashibing.dailyPractice.round3._No21_30.ReverseNodesInKGroup_0306;

/**
 * k个节点的组内反转
 * 题目：给定一个单向链表，每k个节点分为一组，每组内的节点进行反转，不足k各节点的不分组也不反转（原样保持不变），最后组成一个新的完整的链表并返回新链表的头结点。
 */
public class ReverseNodesInKGroup_0128 {
    /**
     * leetcode给出的已知类
     */
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode reverseKGroup(ListNode head, int k) {// 为了应和leetcode测试，将自己的SingleNode类替换为leetcode给出的已知节点类ListNode
        if(head == null || k <= 1) {
            return head;
        }

        ListNode end = findGroupEnd(head, k);
        if(end == null) {// 说明整个链表的节点个数都没有k个
            return head;
        }

        ListNode finalNewHead = end;
        ListNode start = head;
        ListNode preEnd = head;
        do {
            reverse(start, end);
            start = start.next;
            end = findGroupEnd(start, k);
            if(end == null) {
                break;
            }
            preEnd.next = end;
            preEnd = start;
        } while(true);

        return finalNewHead;
    }

    private ListNode findGroupEnd(ListNode start, int k) {
        while(start != null && --k != 0) {
            start = start.next;
        }

        return start;
    }

    /**
     * 这个局部反转比整个链表反转多一步，就是最后要将反正后的尾结点的next指向下一组反转前的头结点
     * @param start
     * @param end
     */
    private void reverse(ListNode start, ListNode end) {
        end = end.next;// 先让end往后走一步
        ListNode tmpStart = start;
        ListNode pre = null;
        ListNode next = null;
        while(start != end) {
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        tmpStart.next = start;// 这一步很关键
    }

    public static void main(String[] args) {
//        ListNode n1 = new SingleNode<>(1);
//        ListNode n2 = new SingleNode<>(2);
//        ListNode n3 = new SingleNode<>(3);
//        ListNode n4 = new SingleNode<>(4);
//        ListNode n5 = new SingleNode<>(5);
//        ListNode n6 = new SingleNode<>(6);
//        ListNode n7 = new SingleNode<>(7);
//        ListNode n8 = new SingleNode<>(8);
//        ListNode n9 = new SingleNode<>(9);
//        ListNode n10 = new SingleNode<>(10);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
//        n5.next = n6;
//        n6.next = n7;
//        n7.next = n8;
//        n8.next = n9;
//        n9.next = n10;
//        DuiShuQiUtil.printSingleList(n1);
//
//        ListNode head = reverseNodesInKGroup(n1, 3);
//        DuiShuQiUtil.printSingleList(head);

        // 没有对数器，直接到leetcode上测试即可：https://leetcode.com/problems/reverse-nodes-in-k-group/
    }
}
