package com.mashibing.list;

/**
 * 题目：把一个无序的单向链表按照某个值调整成左边小于该值、中间相等、右边大于该值的形式（小于区和大于区无需排序）
 * 课程：体系班课时78-79
 * 思路：设置7个变量：小于区的头结点（小头）、小于区的尾结点（小尾）、等头、等尾、大头、大尾和一个保存下一个节点的临时变量，然后小尾连等头，等尾连大头，返回新链表的头部。
 * 注意：
 * 1、要考虑到没有小于区、没有等于区或者没有大于区的情况
 * 2、空间复杂度是O(1)，那么就要用原链表的节点进行操作从而形成新的链表，那么原链表各节点之间必须断连
 */
public class SmallerEqualBigger {
    public static SingleNode<Integer> getSmallerEqualBigger(SingleNode<Integer> head, int targetValue) {
        if (head == null) {
            return null;
        }

        SingleNode<Integer> sH = null;//smallHead
        SingleNode<Integer> sT = null;//smallTail
        SingleNode<Integer> eH = null;//equalHead
        SingleNode<Integer> eT = null;//equalTail
        SingleNode<Integer> mH = null;//moreHead
        SingleNode<Integer> mT = null;//moreTail
        SingleNode<Integer> next = null;
        while (head != null) {// 这里可以直接拿原链表的head操作，因为head不会返回
            next = head.next;
            head.next = null;// 现将原链表的节点从原链表脱离出来
            if (head.value < targetValue) {
                if(sT == null) {// 说明是小于区的第一个节点
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == targetValue) {
                if(eT == null) {// 说明是等于区的第一个节点
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if(mT == null) {// 说明是大于区的第一个节点
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        /*
         经过上面的while循环，原链表的各节点已经都拆开了，每一个节点都放入了小于区、等于区和大于区中的其中一个，每一个区都形成了一个小链表，
         下面要做的就是将小链表组成一个完整的链表：小尾连等头，等尾连大头，麻烦就麻烦在：小于区、等于区和大于区有可能存在也有可能不存在。
         */

        // 将小链表连成大链表
        // 当小于区存在的时候，只往下考虑一步即可，即只考虑sT连接谁即可，再后面的怎么连接不用考虑
        if(sT != null) {// 存在小于区
            if(eH != null) {// 存在等于区
                sT.next = eH;
            } else if(mH != null) {// 不存在等于区但存在大于区
                sT.next = mH;
            } else {// 等于区和大于区都没有
                return sH;// 直接返回小头就可以了
            }
        }

        // 同理，当等于区存在的时候，只往下考虑一步即可，即只考虑eT连接谁即可，再后面的怎么连接不用考虑
        if(eH != null) {// 存在等于区
            /*
             存在等于区，至于前面的小于区是不是存在这里不用考虑，因为如果存在小于区，那么在上面的if判断里就已经将小尾和等头连上了，
             如果不存在小于区，那么等头就是最终链表的头。所以，这里只管往后连就可以了。
             */
            if(mH != null) {// 存在大于区
                eT.next = mH;
            }
        }

        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(3);
        SingleNode<Integer> n2 = new SingleNode<>(3);
        SingleNode<Integer> n3 = new SingleNode<>(3);
        SingleNode<Integer> n4 = new SingleNode<>(3);
        SingleNode<Integer> n5 = new SingleNode<>(3);
        SingleNode<Integer> n6 = new SingleNode<>(3);
        SingleNode<Integer> n7 = new SingleNode<>(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        SingleNode<Integer> head = n1;
        while(head != null) {
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.println();
        System.out.println("-----------");

        SingleNode<Integer> newHead = getSmallerEqualBigger(n1, 3);
        while(newHead != null) {
            System.out.print(newHead.value + " -> ");
            newHead = newHead.next;
        }
        System.out.println();
        System.out.println("-----------");
    }
}
