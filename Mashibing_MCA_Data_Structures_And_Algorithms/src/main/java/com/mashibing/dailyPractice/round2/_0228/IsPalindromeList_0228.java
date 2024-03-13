package com.mashibing.dailyPractice.round2._0228;

import com.mashibing.list.SingleNode;

/**
 * 给定一个单链表的头结点，判断该链表是否为回文结构
 */
public class IsPalindromeList_0228 {
    public static boolean isPalindromeList(SingleNode<Integer> head) {
        if(head == null) {
            return true;
        }

        SingleNode<Integer> f = head;
        SingleNode<Integer> s = head;
        while(f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        SingleNode<Integer> c = s;
        SingleNode<Integer> n = s;
        SingleNode<Integer> p = null;
        while(c != null) {
            n = c.next;
            c.next = p;
            p = c;
            c = n;
        }

        c = head;
        while(c != p && c != null) {
            if(c.value == p.value) {
                c = c.next;
                p = p.next;
                continue;
            }
            return false;
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
