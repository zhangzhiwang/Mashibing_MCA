package com.mashibing.dailyPractice.round2._0223;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 无环单向链表删除指定节点
 */
public class RemoveNode2_0223 {
    public static SingleNode<Integer> removeNode2(SingleNode<Integer> head, SingleNode<Integer> targetNode) {
        if(head == null || targetNode == null) {
            return head;
        }

        if(head == targetNode) {
            head = head.next;
        }
        if(head == null) {
            return null;
        }

        SingleNode<Integer> cur = head;
        SingleNode<Integer> pre = null;
        while(cur != null) {
            if(cur != targetNode) {
                pre = cur;
                cur = cur.next;
            } else {
                cur = cur.next;
                pre.next = cur;
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
        SingleNode<Integer> n6 = new SingleNode<>(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        DuiShuQiUtil.printSingleList(n1);
        n1 = removeNode2(n1, n6);
        DuiShuQiUtil.printSingleList(n1);

    }
}
