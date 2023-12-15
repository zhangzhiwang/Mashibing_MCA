package com.mashibing.list;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 无环单向链表删除指定节点
 * 课程：体系班课时89
 */
public class RemoveNode2 {
    /**
     *
     * @param head 链表的头部
     * @param node 被删除的节点
     * @return
     */
    public static SingleNode<Integer> removeNode(SingleNode<Integer> head, SingleNode<Integer> node) {
        if(head == null || node == null) {
            return head;
        }

        if(head == node) {
            return head.next;
        }

        // 走到这里说明删除的不是头结点
        SingleNode<Integer> cur = head;
        SingleNode<Integer> pre = head;
        while(cur != node && cur != null) {
            cur = cur.next;
            if(cur == node) {
                pre.next = cur.next;
                break;
            }
            pre = cur;
        }

        return head;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n2 = new SingleNode<>(2);
        SingleNode<Integer> n3 = new SingleNode<>(3);
        SingleNode<Integer> n4 = new SingleNode<>(4);
        n1.next = n2;
        n2.next = n3;
        DuiShuQiUtil.printSingleList(n1);

        SingleNode<Integer> newHead = removeNode(n1, n4);
        DuiShuQiUtil.printSingleList(newHead);
    }
}
