package com.mashibing.dailyPractice.round3._No41_50;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 单向链表删除所有具有指定值的节点
 */
public class RemoveNode_0307 {
    public static SingleNode<Integer> removeNode(SingleNode<Integer> head, int target) {
        if(head == null) {
            return null;
        }

        while(head != null) {
            if(head.value == target) {
                head = head.next;
                continue;
            }
            break;
        }
        if(head == null) {
            return null;
        }

        SingleNode<Integer> c = head;
        SingleNode<Integer> n = c;
        while(c != null) {
            n = c.next;
            while(n != null && n.value == target) {
                n = n.next;
            }
            c.next = n;
            c = n;
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

        SingleNode<Integer> head = removeNode(n1, 100);
        DuiShuQiUtil.printSingleList(head);
    }
}
