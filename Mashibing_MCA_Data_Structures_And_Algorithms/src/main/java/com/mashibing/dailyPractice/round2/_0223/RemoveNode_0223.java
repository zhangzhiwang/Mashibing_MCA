package com.mashibing.dailyPractice.round2._0223;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 单向链表删除所有具有指定值的节点
 */
public class RemoveNode_0223 {
    public static SingleNode<Integer> removeNode(SingleNode<Integer> head, int target) {
        if(head == null) {
            return null;
        }

        SingleNode<Integer> newHead = head;
        while(newHead != null && newHead.value == target) {
            newHead = newHead.next;
        }
        if(newHead == null) {
            return null;
        }

        SingleNode<Integer> pre = newHead;
        SingleNode<Integer> cur = newHead.next;
        while(cur != null) {
            if(cur.value != target) {
                pre.next = cur;
                pre = cur;
            }
            cur = cur.next;
        }
        pre.next = cur;

        return newHead;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n1_1 = new SingleNode<>(1);
        SingleNode<Integer> n2 = new SingleNode<>(2);
        SingleNode<Integer> n1_2 = new SingleNode<>(1);
        SingleNode<Integer> n1_3 = new SingleNode<>(1);
        SingleNode<Integer> n3 = new SingleNode<>(3);
        SingleNode<Integer> n1_4 = new SingleNode<>(1);
        SingleNode<Integer> n5 = new SingleNode<>(5);
        n1.next = n1_1;
//        n1_1.next = n2;
//        n2.next = n1_2;
//        n1_2.next = n1_3;
//        n1_3.next = n3;
//        n3.next = n1_4;
//        n1_4.next = n5;

        DuiShuQiUtil.printSingleList(n1);
        n1 = removeNode(n1, 1);
        DuiShuQiUtil.printSingleList(n1);
    }
}
