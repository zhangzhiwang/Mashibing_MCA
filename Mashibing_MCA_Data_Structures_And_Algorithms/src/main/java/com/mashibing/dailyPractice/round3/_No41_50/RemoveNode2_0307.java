package com.mashibing.dailyPractice.round3._No41_50;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 无环单向链表删除指定节点
 */
public class RemoveNode2_0307 {
    public static SingleNode<Integer> removeNode2(SingleNode<Integer> head, SingleNode<Integer> targetNode) {
        if(head == null || (head.next == null && head == targetNode)) {
            return null;
        }
        if(targetNode == null) {
            return head;
        }

        if(head == targetNode) {
            head = head.next;
        }

        SingleNode<Integer> c = head;
        SingleNode<Integer> n = c;
        while(c != null) {
            n = c.next;
            if(n != targetNode) {
                c = n;
            } else {
                n = n.next;
                c.next = n;
                break;
            }
        }

        return head;
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

        DuiShuQiUtil.printSingleList(n1);

        SingleNode<Integer> head = removeNode2(n1, n3);
        DuiShuQiUtil.printSingleList(head);
    }
}