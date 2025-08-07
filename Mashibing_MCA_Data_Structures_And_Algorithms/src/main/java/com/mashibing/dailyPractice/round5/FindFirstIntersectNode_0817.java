package com.mashibing.dailyPractice.round5;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2，请实现一个函数：如果两个链表相交就返回相交的第一个节点，如果不相交就返回null
 */
public class FindFirstIntersectNode_0817 {
    public static SingleNode<Integer> findFirstIntersectNode(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        if((head1 == null && head2 == null) || (head1 == null ^ head2 == null)) {
            return null;
        }

        SingleNode<Integer> loopNode1 = findLoopNode(head1);
        SingleNode<Integer> loopNode2 = findLoopNode(head2);
        SingleNode<Integer> intersectNode = null;
        if(loopNode1 == null && loopNode2 ==null) {
            intersectNode = findIntersectNodeWithoutLoop(head1, head2);
        } else if (loopNode1 != null && loopNode2 !=null) {
            intersectNode = findIntersectNodeWithLoop(head1, loopNode1, head2, loopNode2);
        }

        return intersectNode;
    }

    private static SingleNode<Integer> findIntersectNodeWithoutLoop(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        SingleNode<Integer> c1 = head1;
        int len1 = 0;
        while (c1.next != null) {
            c1 = c1.next;
            len1++;
        }

        SingleNode<Integer> c2 = head2;
        int len2 = 0;
        while (c2.next != null) {
            c2 = c2.next;
            len2++;
        }

        if(c1 != c2) {
            return null;
        }

        SingleNode<Integer> longHead = len1 >= len2 ? head1 : head2;
        SingleNode<Integer> shortHead = longHead == head1 ? head2 : head1;
        int t = Math.abs(len1 - len2);
        while (t-- > 0) {
            longHead = longHead.next;
        }

        while (longHead != shortHead) {
            longHead = longHead.next;
            shortHead = shortHead.next;
        }

        return longHead;
    }

    private static SingleNode<Integer> findIntersectNodeWithLoop(SingleNode<Integer> head1, SingleNode<Integer> loopNode1, SingleNode<Integer> head2, SingleNode<Integer> loopNode2) {
        if(loopNode1 == loopNode2) {
            SingleNode<Integer> c1 = head1;
            int len1 = 0;
            while (c1 != loopNode1) {
                c1 = c1.next;
                len1++;
            }

            SingleNode<Integer> c2 = head2;
            int len2 = 0;
            while (c2 != loopNode2) {
                c2 = c2.next;
                len2++;
            }

            SingleNode<Integer> longHead = len1 >= len2 ? head1 : head2;
            SingleNode<Integer> shortHead = longHead == head1 ? head2 : head1;
            int t = Math.abs(len1 - len2);
            while (t-- > 0) {
                longHead = longHead.next;
            }

            while (longHead != shortHead) {
                longHead = longHead.next;
                shortHead = shortHead.next;
            }

            return longHead;
        } else {
            SingleNode<Integer> t = loopNode1.next;
            while (t != loopNode1) {
                if(t == loopNode2) {
                    return loopNode1;
                }

                t = t.next;
            }

            return null;
        }
    }

    private static SingleNode<Integer> findLoopNode(SingleNode<Integer> head) {
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }

        SingleNode<Integer> f = head.next.next;
        SingleNode<Integer> s = head.next;
        while (f != s) {
            if(f.next == null || f.next.next == null) {
                return null;
            }

            f = f.next.next;
            s = s.next;
        }

        f = head;
        while (f != s) {
            f = f.next;
            s = s.next;
        }
        return f;
    }

    // 老师的代码
    public static SingleNode<Integer> getIntersectNode(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        SingleNode<Integer> loop1 = getLoopNode(head1);
        SingleNode<Integer> loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    // 找到链表第一个入环节点，如果无环，返回null
    public static SingleNode<Integer> getLoopNode(SingleNode<Integer> head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // n1 慢  n2 快
        SingleNode<Integer> slow = head.next; // n1 -> slow
        SingleNode<Integer> fast = head.next.next; // n2 -> fast
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow fast  相遇
        fast = head; // n2 -> walk again from head
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
    public static SingleNode<Integer> noLoop(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        SingleNode<Integer> cur1 = head1;
        SingleNode<Integer> cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        // n  :  链表1长度减去链表2长度的值
        cur1 = n > 0 ? head1 : head2; // 谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头变成cur2
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 两个有环链表，返回第一个相交节点，如果不想交返回null
    public static SingleNode<Integer> bothLoop(SingleNode<Integer> head1, SingleNode<Integer> loop1, SingleNode<Integer> head2, SingleNode<Integer> loop2) {
        SingleNode<Integer> cur1 = null;
        SingleNode<Integer> cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        SingleNode<Integer> head1 = new SingleNode<>(1);// 本题目不关心节点的值，所以节点设置成什么值无所谓
        SingleNode<Integer> head1_n2 = new SingleNode<>(2);
        SingleNode<Integer> head1_n3 = new SingleNode<>(3);
        SingleNode<Integer> head1_n4 = new SingleNode<>(4);
        SingleNode<Integer> head1_n5 = new SingleNode<>(5);
        head1.next = head1_n2;
        head1_n2.next = head1_n3;
        head1_n3.next = head1_n4;
        head1_n4.next = head1_n5;
        System.out.println("链表1：");
        SingleNode<Integer> cur = head1;
        while(cur != null) {
            System.out.print(cur.value + " -> ");
            cur = cur.next;
        }
        System.out.println();

        SingleNode<Integer> head2 = new SingleNode<>(10);
        SingleNode<Integer> head2_n2 = new SingleNode<>(20);
        SingleNode<Integer> head2_n3 = new SingleNode<>(30);
        SingleNode<Integer> head2_n4 = new SingleNode<>(40);
        SingleNode<Integer> head2_n5 = new SingleNode<>(50);
        head2.next = head2_n2;
        head2_n2.next = head2_n3;
        head2_n3.next = head2_n4;
        head2_n4.next = head2_n5;
//        head2_n5.next = head2_n3;
        System.out.println("链表2：");
        DuiShuQiUtil.printSingleList(head2);
//        cur = head2;
//        while(cur != null) {
//            System.out.print(cur.value + " -> ");
//            cur = cur.next;
//        }
//        System.out.println();
//        System.out.println("----------");
//
//        System.out.println("correct ans:" + getIntersectNode(head1, head2));
//        System.out.println("my ans:" + findFirstIntersectNode(head1, head2));
//        System.out.println("----------");
//
        SingleNode<Integer> head3 = new SingleNode<>(100);
        SingleNode<Integer> head3_n2 = new SingleNode<>(200);
        SingleNode<Integer> head3_n3 = new SingleNode<>(300);
        SingleNode<Integer> head3_n4 = new SingleNode<>(400);
        SingleNode<Integer> head3_n5 = new SingleNode<>(500);
        head3.next = head3_n2;
        head3_n2.next = head3_n3;
        head3_n3.next = head3_n4;
        head3_n4.next = head3_n5;
        head3_n5.next = head3_n3;
        System.out.println("链表3：");
        DuiShuQiUtil.printSingleList(head3);
        System.out.println("----------");

//        System.out.println("correct ans:" + getIntersectNode(head3, head2));
//        System.out.println("my ans:" + findFirstIntersectNode(head3, head2));
//        System.out.println("----------");
//
        System.out.println("链表4：");
        SingleNode<Integer> head4 = new SingleNode<>(1000);
        SingleNode<Integer> head4_n2 = new SingleNode<>(2000);
        SingleNode<Integer> head4_n3 = new SingleNode<>(3000);
        SingleNode<Integer> head4_n4 = new SingleNode<>(4000);
        SingleNode<Integer> head4_n5 = new SingleNode<>(5000);
        head4.next = head4_n2;
//        head4_n2.next = head2_n5;
//        head4_n2.next = head3_n3;
        head4_n2.next = head4_n3;
        head4_n3.next = head4_n4;
        head4_n4.next = head4_n5;
        head4_n5.next = head3_n4;

        DuiShuQiUtil.printSingleList(head4);
        System.out.println("correct ans:" + getIntersectNode(head3, head4));
        System.out.println("my ans:" + findFirstIntersectNode(head3, head4));
        System.out.println("----------");
    }
}
