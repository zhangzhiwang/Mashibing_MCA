package com.mashibing.dailyPractice.round3._No61_70;

import com.mashibing.list.SingleNode;

/**
 * 给定一个单链表的头结点，判断该链表是否为回文结构
 */
public class IsPalindromeList_0312 {
    public static boolean isPalindromeList(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return true;
        }

        SingleNode<Integer> f = head;
        SingleNode<Integer> s = head;
        while(f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        f = null;
        SingleNode<Integer> n = s;
        while(s != null) {
            n = s.next;
            s.next = f;
            f = s;
            s = n;
        }

        s = head;
        while(s != null && s != f) {
            if(s.value != f.value) {
                return false;
            }
            s = s.next;
            f = f.next;
        }

        return true;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n2 = new SingleNode<>(2);
        SingleNode<Integer> n3 = new SingleNode<>(3);
        SingleNode<Integer> n4 = new SingleNode<>(2);
        SingleNode<Integer> n5 = new SingleNode<>(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println(isPalindromeList(n1));
    }
}
