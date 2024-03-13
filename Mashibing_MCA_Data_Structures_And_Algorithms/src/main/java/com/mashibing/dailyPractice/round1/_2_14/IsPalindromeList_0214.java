package com.mashibing.dailyPractice.round1._2_14;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 给定一个单链表的头结点，判断该链表是否为回文结构
 */
public class IsPalindromeList_0214 {
    public static boolean isPalindromeList(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return true;
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

        boolean result = true;
        SingleNode<Integer> tmpHead = head;
        while(tmpHead != null && tmpHead != pre) {
            if(tmpHead.value != pre.value) {
                result =  false;
//                break; // 注意：一旦发现不是回文结构不能马上返回false，还要将下面的流程走完，因为要还原原链表
            }

            tmpHead = tmpHead.next;
            pre = pre.next;
        }

        tmpHead = pre;
        pre = null;
        next = fast;
        while(fast != tmpHead) {
            next = fast.next;
            fast.next = pre;
            pre = fast;
            fast = next;
        }
        tmpHead.next = pre;

        return result;
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

        DuiShuQiUtil.printSingleList(n1);
        boolean b = isPalindromeList(n1);
        System.out.println("b = " + b);
        DuiShuQiUtil.printSingleList(n1);
    }
}
