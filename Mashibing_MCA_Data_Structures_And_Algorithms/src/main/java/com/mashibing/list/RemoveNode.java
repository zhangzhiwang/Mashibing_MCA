package com.mashibing.list;

/**
 * 删除具有指定值的节点（单链表）
 * 课程：体系班课时21
 * 思路：见com.mashibing.preInEclipse.senior.list.RemoveNode注释
 */
public class RemoveNode {
    public static void main(String[] args) {
        // 2    2   2   3   1   2   2   5   2
        SingleNode<Integer> n1 = new SingleNode<>(2);
        SingleNode<Integer> n2 = new SingleNode<>(2);
        SingleNode<Integer> n3 = new SingleNode<>(2);
        SingleNode<Integer> n4 = new SingleNode<>(3);
        SingleNode<Integer> n5 = new SingleNode<>(1);
        SingleNode<Integer> n6 = new SingleNode<>(2);
        SingleNode<Integer> n7 = new SingleNode<>(2);
        SingleNode<Integer> n8 = new SingleNode<>(5);
        SingleNode<Integer> n9 = new SingleNode<>(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        System.out.println("原始链表：");

        SingleNode<Integer> head = n1;
        while(head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }

        SingleNode<Integer> newHead = removeNode(n1, 2);

        System.out.println();
        while(newHead != null) {
            System.out.print(newHead.data + " -> ");
            newHead = newHead.next;
        }
    }

    public static SingleNode<Integer> removeNode(SingleNode<Integer> head, int targetNum) {
        while(head != null) {
            if(head.data == targetNum) {
                head = head.next;
                continue;
            }
            break;
        }

        if(head == null) {
            return null;
        }

        SingleNode<Integer> cur = head;
        SingleNode<Integer> pre = head;
        while(cur != null) {
            if(cur.data != targetNum) {
                pre = cur;
                cur = cur.next;
                continue;
            }

            cur = cur.next;
            pre.next = cur;
        }

        return head;
    }
}
