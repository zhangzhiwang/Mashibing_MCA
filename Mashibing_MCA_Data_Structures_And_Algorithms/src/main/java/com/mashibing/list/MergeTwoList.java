package com.mashibing.list;

/**
 * 合并两个链表
 * 题目：给定两个有序链表的头部h1和h2，合并这两个链表后组成一个新的有序链表，返回新链表的头部
 * 课程：新手班课时33
 * 思路：见com.mashibing.preInEclipse.junior.list.MergeTwoList注释
 */
public class MergeTwoList {
    public static void main(String[] args) {
        // 链表1：1-5-6-8
        SingleNode<Integer> head1 = new SingleNode<>(1);
        head1.next = new SingleNode(5);
        head1.next.next = new SingleNode(6);
        head1.next.next.next = new SingleNode(8);

        // 链表2：2-7-9-10-15
        SingleNode<Integer> head2 = new SingleNode<>(2);
        head2.next = new SingleNode(7);
        head2.next.next = new SingleNode(9);
        head2.next.next.next = new SingleNode(10);
        head2.next.next.next.next = new SingleNode(15);

        SingleNode<Integer> head = mergeTwoList(head1, head2);
        while(head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static SingleNode<Integer> mergeTwoList(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        if(head1 == null && head2 == null) {
            return null;
        }

        if(head1 == null ^ head2 == null) {
            return head1 == null ? head2 : head1;
        }

        SingleNode<Integer> head = head1.data <= head2.data ? head1 : head2;// 两个链表的头节点谁小谁是最终链表的头部
        /*
         既然最小的头部节点已经确定了，它就不参与后续的比较了，下面就是比较最终返回的头结点（head）的下一个节点和另一个节点的头结点大小。
         让head节点的下一个节点为cur1，另一个链表的头结点是cur2，以后就是cur1和cur2比较了
         */
        SingleNode<Integer> cur1 = head.next;
        SingleNode<Integer> cur2 = head == head1 ? head2 : head1;
        SingleNode<Integer> pre = head;

        while(cur1 != null && cur2 != null) {
            SingleNode<Integer> tmpNode = cur1.data <= cur2.data ? cur1 : cur2;
            pre.next = tmpNode;
            if(tmpNode == cur2) {
                cur2 = cur2.next;
            } else {
                cur1 = cur1.next;
            }
            pre = pre.next;
        }

        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }
}
