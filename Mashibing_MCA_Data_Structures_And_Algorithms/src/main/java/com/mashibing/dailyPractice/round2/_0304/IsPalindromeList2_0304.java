package com.mashibing.dailyPractice.round2._0304;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 假如给定一个链表为1->2->3->4->5->6->7->8，将链表调成1->8->2->7->3->6->4->5，链表节点的个数可能为奇数个也可能为偶数个
 */
public class IsPalindromeList2_0304 {
    public static void isPalindromeList2(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return;
        }

        SingleNode<Integer> f = head;
        SingleNode<Integer> s = head;
        while(f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        SingleNode<Integer> p = null;
        SingleNode<Integer> n = s;
        while(s != null) {
            n = s.next;
            s.next = p;
            p = s;
            s = n;
        }

        s = head;
        while (s != p && s != null) {
            f = s.next;
            n = p.next;
            s.next = p;
            p.next = f;
            s = f;
            p = n;
        }
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
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;

        DuiShuQiUtil.printSingleList(n1);
        isPalindromeList2(n1);
        DuiShuQiUtil.printSingleList(n1);
    }
}
