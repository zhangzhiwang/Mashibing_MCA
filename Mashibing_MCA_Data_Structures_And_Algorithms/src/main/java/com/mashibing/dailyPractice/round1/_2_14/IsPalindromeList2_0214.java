package com.mashibing.dailyPractice.round1._2_14;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 假如给定一个链表为1->2->3->4->5->6->7->8，将链表调成1->8->2->7->3->6->4->5
 * （链表节点的个数可能为奇数个也可能为偶数个）
 */
public class IsPalindromeList2_0214 {
    public static SingleNode<Integer> isPalindromeList2(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return head;
        }

        SingleNode<Integer> fast = head;
        SingleNode<Integer> slow = head;
        while(fast.next != null) {
            fast = fast.next;
            if(fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }

        SingleNode<Integer> next = slow;
        SingleNode<Integer> pre = null;
        while(slow != null) {
            next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        fast = null;// 避免内存泄漏，或者不新申请下面的新变量n2也行，复用fast，用fast充当n2，这样可以节省一个变量但不便于阅读
        SingleNode<Integer> tmpHead = head;
        next = pre;
        SingleNode<Integer> n2 = tmpHead;
        while(tmpHead != null && tmpHead != pre) {
            next = pre.next;
            n2 = tmpHead.next;
            tmpHead.next = pre;
            pre.next = n2;
            tmpHead = n2;
            pre = next;
        }

        return head;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n2 = new SingleNode<>(2);
        SingleNode<Integer> n3 = new SingleNode<>(3);
        SingleNode<Integer> n4 = new SingleNode<>(4);
        SingleNode<Integer> n5 = new SingleNode<>(5);
        SingleNode<Integer> n6 = new SingleNode<>(6);
        SingleNode<Integer> n7 = new SingleNode<>(7);
        SingleNode<Integer> n8 = new SingleNode<>(8);
        SingleNode<Integer> n9 = new SingleNode<>(9);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;

        DuiShuQiUtil.printSingleList(n1);
        SingleNode<Integer> head = isPalindromeList2(n1);
        DuiShuQiUtil.printSingleList(head);
    }
}
