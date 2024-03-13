package com.mashibing.dailyPractice.round1._2_1;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 无环单向链表删除指定节点
 */
public class RemoveNode2_0201 {
    public static SingleNode<Integer> removeNode(SingleNode<Integer> head, SingleNode<Integer> targetNode) {
        if(head == null || targetNode == null) {
            return head;
        }

        if(targetNode == head) {
            head = head.next;
            return head;
        }

        SingleNode<Integer> tmpHead = head;
        SingleNode<Integer> pre = tmpHead;
        tmpHead = tmpHead.next;
        while(tmpHead != null) {
            if(tmpHead != targetNode) {
                pre.next = tmpHead;
                pre = tmpHead;
            } else {
                pre.next = tmpHead.next;
                tmpHead = null;
                break;
            }

            tmpHead = tmpHead.next;
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
        SingleNode<Integer> n7 = new SingleNode<>(7);
        SingleNode<Integer> n8 = new SingleNode<>(8);
        SingleNode<Integer> n9 = new SingleNode<>(9);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;

        DuiShuQiUtil.printSingleList(n1);

        SingleNode<Integer> head = removeNode(n1, n9);
        DuiShuQiUtil.printSingleList(head);
    }
}