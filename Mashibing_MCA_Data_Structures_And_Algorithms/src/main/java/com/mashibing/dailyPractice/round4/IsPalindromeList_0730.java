package com.mashibing.dailyPractice.round4;

import com.mashibing.list.SingleNode;

import java.util.Stack;

/**
 * 给定一个单链表的头结点，判断该链表是否为回文结构
 */
public class IsPalindromeList_0730 {
    public static boolean isPalindromeList(SingleNode<Integer> head) {
        if(head == null) {
            return true;
        }

        SingleNode<Integer> f = head;
        SingleNode<Integer> s = head;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        f = s;
        SingleNode<Integer> p = null;
        while (f != null) {
            s = f.next;
            f.next = p;
            p = f;
            f = s;
        }

        f = p;
        s = head;
        while (f != null && s != null) {
            if(s.value != f.value) {
                return false;
            }

            s = s.next;
            f = f.next;
        }

        s = p;
        while (p != null) {
            s = p.next;
            p.next = f;
            f = p;
            p = s;
        }
        return true;
    }

    // 老师的代码
    public static boolean isPalindrome1(SingleNode<Integer> head) {
        Stack<SingleNode<Integer>> stack = new Stack<SingleNode<Integer>>();
        SingleNode<Integer> cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n2 = new SingleNode<>(2);
        SingleNode<Integer> n3 = new SingleNode<>(3);
        SingleNode<Integer> n6 = new SingleNode<>(3);
        SingleNode<Integer> n4 = new SingleNode<>(2);
        SingleNode<Integer> n5 = new SingleNode<>(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n6;
        n6.next = n4;
        n4.next = n5;

        System.out.println("correct ans:");
        System.out.println(isPalindrome1(n1));
        System.out.println("my ans:");
        System.out.println(isPalindromeList(n1));
    }
}
