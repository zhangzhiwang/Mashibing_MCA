package com.mashibing.dailyPractice.round3._No61_70;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2，请实现一个函数，如果两个链表相交就返回相交的第一个节点，如果不相交就返回null
 */
public class FindFirstIntersectNode_0312 {
    public static SingleNode findFirstIntersectNode(SingleNode h1, SingleNode h2) {
        if(h1 == null || h2 == null) {
            return null;
        }

        SingleNode loopNode1 = findLoopNode(h1);
        SingleNode loopNode2 = findLoopNode(h2);
        SingleNode finalHead = null;
        if(loopNode1 == null && loopNode2 == null) {
            finalHead = findInterNodeNoLoop(h1, h2);
        } else if (loopNode1 != null && loopNode2 != null) {
            finalHead = findInterNodeLoop(h1, h2, loopNode1, loopNode2);
        } else {
            finalHead = null;
        }

        return finalHead;
    }

    private static SingleNode findLoopNode(SingleNode head) {
        if(head.next == null) {
            return null;
        }

        SingleNode f = head;
        SingleNode s = head;
         do {
            if(f.next != null && f.next.next != null) {
                f = f.next.next;
            } else {
                return null;
            }
            s = s.next;
        } while(f != s);

        f = head;
        while(f != s) {
            f = f.next;
            s = s.next;
        }

        return f;
    }

    private static SingleNode findInterNodeNoLoop(SingleNode head1, SingleNode head2) {
        SingleNode c1 = head1;
        int len = 0;
        while (c1.next != null) {
            c1 = c1.next;
            len++;
        }

        SingleNode c2 = head2;
        while (c2.next != null) {
            c2 = c2.next;
            len--;
        }

        if(c1 != c2) {
            return null;
        }

        SingleNode longHead = len >= 0 ? head1 : head2;
        SingleNode shortHead = longHead == head1 ? head2 : head1;
        int tmp = Math.abs(len);
        while (tmp-- > 0) {
            longHead = longHead.next;
        }

        while(longHead != shortHead) {
            longHead = longHead.next;
            shortHead = shortHead.next;
        }
        return longHead;
    }

    private static SingleNode findInterNodeLoop(SingleNode head1, SingleNode head2, SingleNode loop1, SingleNode loop2) {
        SingleNode c1 = loop1.next;
        while (c1 != loop1) {
            if(c1 == loop2) {
                return c1;
            }
            c1 = c1.next;
        }

        if(loop1 != loop2) {
            return null;
        }

        c1 = head1;
        int len = 0;
        while (c1 != loop1) {
            c1 = c1.next;
            len++;
        }

        SingleNode c2 = head2;
        while (c2 != loop1) {
            c2 = c2.next;
            len--;
        }

        SingleNode longHead = len >= 0 ? head1 : head2;
        SingleNode shortHead = longHead == head1 ? head2 : head1;
        int tmp = Math.abs(len);
        while (tmp-- > 0) {
            longHead = longHead.next;
        }

        while(longHead != shortHead) {
            longHead = longHead.next;
            shortHead = shortHead.next;
        }
        return longHead;
    }

    public static void main(String[] args) {
//        int i = 10;
//        System.out.println(Math.abs(i--));
//        System.out.println(i);

        SingleNode<Integer> head1_n1 = new SingleNode<>(1);
        SingleNode<Integer> head1_n2 = new SingleNode<>(2);
        SingleNode<Integer> head1_n3 = new SingleNode<>(3);
        SingleNode<Integer> head1_n4 = new SingleNode<>(4);
        SingleNode<Integer> head1_n5 = new SingleNode<>(5);
        SingleNode<Integer> head1_n6 = new SingleNode<>(6);
        SingleNode<Integer> head1_n7 = new SingleNode<>(7);
        SingleNode<Integer> head1_n8 = new SingleNode<>(8);
        head1_n1.next = head1_n2;
        head1_n2.next = head1_n3;
        head1_n3.next = head1_n4;
        head1_n4.next = head1_n5;
        head1_n5.next = head1_n6;
        head1_n6.next = head1_n7;
        head1_n7.next = head1_n8;
        head1_n8.next = head1_n5;
        DuiShuQiUtil.printSingleList(head1_n1);

        SingleNode<Integer> head2_n1 = new SingleNode<>(10);
        SingleNode<Integer> head2_n2 = new SingleNode<>(20);
        SingleNode<Integer> head2_n3 = new SingleNode<>(30);
        SingleNode<Integer> head2_n4 = new SingleNode<>(40);
        SingleNode<Integer> head2_n5 = new SingleNode<>(50);
        head2_n1.next = head2_n2;
        head2_n2.next = head2_n3;
        head2_n3.next = head2_n4;
        head2_n4.next = head2_n5;
        head2_n5.next = head1_n2;
        DuiShuQiUtil.printSingleList(head2_n1);

        SingleNode<Integer> crossNode = findFirstIntersectNode(head1_n1, head2_n1);
        System.out.println("crossNode = " + (crossNode == null ? null : crossNode.value));
    }
}
