package com.mashibing.dailyPractice.round2._0228;

import com.mashibing.list.SingleNode;

/**
 * 找链表的各种中点：
 * 1、如果链表的节点个数是偶数个那么返回上中点，奇数个则返回中间的节点；
 * 2、如果链表的节点个数是偶数个那么返回下中点，奇数个则返回中间的节点；
 * 3、如果链表的节点个数是偶数个那么返回上中点的前一个节点，奇数个则返回中间节点的前一个节点；
 * 4、如果链表的节点个数是偶数个那么返回上中点，奇数个则返回中间节点的前一个节点
 */
public class FindListMid_0228 {
    public static SingleNode<Integer> findListMid1(SingleNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }

        SingleNode<Integer> f = head;
        SingleNode<Integer> s = head;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

    public static SingleNode<Integer> findListMid2(SingleNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }

        SingleNode<Integer> f = head.next;
        SingleNode<Integer> s = head.next;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

    public static SingleNode<Integer> findListMid3(SingleNode<Integer> head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        SingleNode<Integer> f = head.next;
        SingleNode<Integer> s = head;
        while (f.next != null && f.next.next != null && f.next.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        return s;
    }

    public static SingleNode<Integer> findListMid4(SingleNode<Integer> head) {
        if (head == null || head.next == null) {
            return null;
        }

        SingleNode<Integer> f = head.next;
        SingleNode<Integer> s = head;
        while (f.next != null) {
            f = f.next;
            if(f.next != null) {
                f = f.next;
                s = s.next;
            }
        }

        return s;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n2 = new SingleNode<>(2);
        SingleNode<Integer> n3 = new SingleNode<>(3);
        SingleNode<Integer> n4 = new SingleNode<>(4);
        SingleNode<Integer> n5 = new SingleNode<>(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println(findListMid1(n1).value);
//        System.out.println(findListMid2(n1).value);
//        System.out.println(findListMid3(n1).value);
//        System.out.println(findListMid4(n1).value);
    }
}
