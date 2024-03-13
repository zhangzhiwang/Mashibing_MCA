package com.mashibing.dailyPractice.round2._0304;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2，请实现一个函数，如果两个链表相交就返回相交的第一个节点，如果不相交就返回null
 */
public class FindFirstIntersectNode_0304 {
    public static SingleNode<Integer> findFirstIntersectNode(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        if(head1 == null || head2 == null) {
            return null;
        }

        SingleNode<Integer> loopNode1 = findLoopNode(head1);
        SingleNode<Integer> loopNode2 = findLoopNode(head2);
        if(loopNode1 == null && loopNode2 == null) {
            return findNoLoopInterNode(head1, head2);
        } else if (loopNode1 != null && loopNode2 != null) {
            return findBothLoopInterNode(head1, head2, loopNode1, loopNode2);
        } else {
            return null;
        }
    }

    private static SingleNode<Integer> findLoopNode(SingleNode<Integer> head) {
        SingleNode<Integer> f = head;
        SingleNode<Integer> s = head;
        while(f != null) {
            if(f.next != null && f.next.next != null) {
                f = f.next.next;
                s = s.next;
            } else {
                return null;
            }

            if(f == s) {
                break;
            }
        }

        f = head;
        while(f != s) {
            f = f.next;
            s = s.next;
        }

        return f;
    }

    private static SingleNode<Integer> findNoLoopInterNode(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        SingleNode<Integer> c1 = head1;
        int len = 0;
        while(c1.next != null) {
            c1 = c1.next;
            len++;
        }

        SingleNode<Integer> c2 = head2;
        while(c2.next != null) {
            c2 = c2.next;
            len--;
        }

        if(c1 != c2) {
            return null;
        }

        SingleNode<Integer> longHead = len >= 0 ? head1 : head2;
        SingleNode<Integer> shortHead = longHead == head1 ? head2 : head1;
        int tmp = Math.abs(len);
        while(tmp-- > 0) {
            longHead = longHead.next;
        }

        while(longHead != shortHead) {
            longHead = longHead.next;
            shortHead = shortHead.next;
        }

        return longHead;
    }

    private static SingleNode<Integer> findBothLoopInterNode(SingleNode<Integer> head1, SingleNode<Integer> head2, SingleNode<Integer> loop1, SingleNode<Integer> loop2) {
        SingleNode<Integer> loop1Tmp = loop1.next;
        while(loop1Tmp != loop1) {
            if(loop1Tmp == loop2) {
                return loop1;
            }
            loop1Tmp = loop1Tmp.next;
        }

        if(loop1 != loop2) {
            return null;
        }

        SingleNode<Integer> c1 = head1;
        int len = 0;
        while(c1 != loop1) {
            c1 = c1.next;
            len++;
        }

        SingleNode<Integer> c2 = head2;
        while(c2 != loop1) {
            c2 = c2.next;
            len--;
        }

        SingleNode<Integer> longHead = len >= 0 ? head1 : head2;
        SingleNode<Integer> shortHead = longHead == head1 ? head2 : head1;
        int tmp = Math.abs(len);
        while(tmp-- > 0) {
            longHead = longHead.next;
        }

        while(longHead != shortHead) {
            longHead = longHead.next;
            shortHead = shortHead.next;
        }

        return longHead;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n1_2 = new SingleNode<>(2);
        SingleNode<Integer> n1_3 = new SingleNode<>(3);
        SingleNode<Integer> n1_4 = new SingleNode<>(4);
        SingleNode<Integer> n1_5 = new SingleNode<>(5);
        n1.next = n1_2;
        n1_2.next = n1_3;
        n1_3.next = n1_4;
        n1_4.next = n1_5;
//        n1_5.next = n1_3;

        SingleNode<Integer> n2 = new SingleNode<>(10);
        SingleNode<Integer> n2_2 = new SingleNode<>(20);
        SingleNode<Integer> n2_3 = new SingleNode<>(30);
        SingleNode<Integer> n2_4 = new SingleNode<>(40);
        SingleNode<Integer> n2_5 = new SingleNode<>(50);
        n2.next = n2_2;
        n2_2.next = n2_3;
        n2_3.next = n2_4;
        n2_4.next = n2_5;
        n2_5.next = n1_3;

        DuiShuQiUtil.printSingleList(n1);
        DuiShuQiUtil.printSingleList(n2);
        System.out.println(findFirstIntersectNode(n1, n2).value);
    }
}
