package com.mashibing.dailyPractice.round1._2_14;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交就返回相交的第一个节点。如果不相交就返回null。
 */
public class FindFirstIntersectNode_0214 {
    public static SingleNode<Integer> findFirstIntersectNode(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        if(head1 == null || head2 == null) {
            return null;
        }

        SingleNode<Integer> loopNode1 = findLoopNode(head1);
        SingleNode<Integer> loopNode2 = findLoopNode(head2);
        if(loopNode1 == null && loopNode2 == null) {
            return findNoLoopCrossNode(head1, head2);
        } else if(loopNode1 != null && loopNode2 != null) {
            return findLoopCrossNode(head1, head2, loopNode1, loopNode2);
        } else {
            return null;
        }
    }

    private static SingleNode<Integer> findLoopNode(SingleNode<Integer> head) {
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }

        SingleNode<Integer> fast = head.next.next;
        SingleNode<Integer> slow = head.next;
        while(fast != slow) {
            if(fast.next == null || fast.next.next == null) {
                return null;
            }

            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    private static SingleNode<Integer> findNoLoopCrossNode(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        SingleNode<Integer> cur1 = head1;
        int listSize = 0;

        while(cur1.next != null) {// 必须让循环结束时cur1停在最后一个节点上
            cur1 = cur1.next;
            listSize++;
        }

        SingleNode<Integer> cur2 = head2;
        while(cur2.next != null) {// 必须让循环结束时cur1停在最后一个节点上
            cur2 = cur2.next;
            listSize--;
        }

        if(cur1 != cur2) {
            return null;
        }

        SingleNode<Integer> longHead = listSize >= 0 ? head1 : head2;
        SingleNode<Integer> shortHead = longHead == head1 ? head2 : head1;
        while(Math.abs(listSize--) != 0) {
            longHead = longHead.next;
        }

        while(longHead != shortHead) {
            longHead = longHead.next;
            shortHead = shortHead.next;
        }

        return longHead;
    }

    private static SingleNode<Integer> findLoopCrossNode(SingleNode<Integer> head1, SingleNode<Integer> head2, SingleNode<Integer> loopNode1, SingleNode<Integer> loopNode2) {
        if(loopNode1 == loopNode2) {
            SingleNode<Integer> cur1 = head1;
            int listSize = 0;
            while(cur1 != loopNode1) {
                cur1 = cur1.next;
                listSize++;
            }

            SingleNode<Integer> cur2 = head2;
            while(cur2 != loopNode2) {
                cur2 = cur2.next;
                listSize--;
            }

            SingleNode<Integer> longHead = listSize >= 0 ? head1 : head2;
            SingleNode<Integer> shortHead = longHead == head1 ? head2 : head1;
            while(Math.abs(listSize--) != 0) {
                longHead = longHead.next;
            }

            while(longHead != shortHead) {
                longHead = longHead.next;
                shortHead = shortHead.next;
            }

            return longHead;
        } else {
            SingleNode<Integer> tmp = loopNode1.next;
            if(tmp == loopNode2) {// 防止loopNode1自己和自己成环
                return loopNode1;
            }
            while(tmp != loopNode1) {
                if(tmp == loopNode2) {
                    return loopNode1;
                }
                tmp = tmp.next;
            }

            return null;
        }
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
        head2_n3.next = head1_n7;
//        head2_n4.next = head2_n5;
//        head2_n4.next = head2_n2;

        SingleNode<Integer> crossNode = findFirstIntersectNode(head1_n1, head2_n1);
        System.out.println("crossNode = " + (crossNode == null ? null : crossNode.value));
    }
}
