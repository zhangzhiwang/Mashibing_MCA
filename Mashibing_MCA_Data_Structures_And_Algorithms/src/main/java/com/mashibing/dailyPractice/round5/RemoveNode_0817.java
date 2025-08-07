package com.mashibing.dailyPractice.round5;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 单向链表删除所有具有指定值的节点
 */
public class RemoveNode_0817 {
    public static SingleNode<Integer> removeNode(SingleNode<Integer> head, int k) {
        if(head == null) {
            return null;
        }

        while (head.value == k) {
            head = head.next;
        }

        SingleNode<Integer> c = head.next;
        SingleNode<Integer> p = head;
        while (c != null) {
            if(c.value == k) {
                c = c.next;
            } else {
                p.next = c;
                p = c;
                c = c.next;
            }
        }

        return head;
    }

    // 老师的代码
    public static SingleNode<Integer> removeValue(SingleNode<Integer> head, int num) {
        // head来到第一个不需要删的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        // 1 ) head == null
        // 2 ) head != null
        SingleNode<Integer> pre = head;
        SingleNode<Integer> cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n2 = new SingleNode<>(1);
        SingleNode<Integer> n3 = new SingleNode<>(2);
        SingleNode<Integer> n4 = new SingleNode<>(6);
        SingleNode<Integer> n5 = new SingleNode<>(3);
        SingleNode<Integer> n6 = new SingleNode<>(1);
        SingleNode<Integer> n7 = new SingleNode<>(1);
        SingleNode<Integer> n8 = new SingleNode<>(9);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;

        DuiShuQiUtil.printSingleList(n1);

        int k = 3;
        System.out.println("correct ans:");
        SingleNode<Integer> head = removeValue(n1, k);
        DuiShuQiUtil.printSingleList(head);
        System.out.println("my ans:");
        SingleNode<Integer> head1 = removeNode(n1, k);
        DuiShuQiUtil.printSingleList(head1);
    }
}
