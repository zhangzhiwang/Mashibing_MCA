package com.mashibing.dailyPractice.round3._No61_70;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 把一个无序的单向链表按照某个值调整成左边小于该值、中间相等、右边大于该值的形式（小于区和大于区无需排序）
 */
public class SmallerEqualBigger_0312 {
    public static SingleNode<Integer> smallerEqualBigger(SingleNode<Integer> head, int target) {
        if (head == null) {
            return null;
        }

        SingleNode<Integer> sH = null;
        SingleNode<Integer> sT = null;
        SingleNode<Integer> eH = null;
        SingleNode<Integer> eT = null;
        SingleNode<Integer> mH = null;
        SingleNode<Integer> mT = null;
        SingleNode<Integer> n = head;
        while (head != null) {
            n = head.next;
            head.next = null;
            if (head.value < target) {
                if (sT == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == target) {
                if (eT == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mT == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }

            head = n;
        }

        if (sH != null) {
            if (eH != null) {
                sT.next = eH;
            } else if (mH != null) {
                sT.next = mH;
            } else {
                return sH;
            }
        }

        if (eH != null) {
            eT.next = mH;
        }

        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(90);
        SingleNode<Integer> n2 = new SingleNode<>(80);
        SingleNode<Integer> n3 = new SingleNode<>(30);
        SingleNode<Integer> n4 = new SingleNode<>(40);
        SingleNode<Integer> n5 = new SingleNode<>(30);
        SingleNode<Integer> n6 = new SingleNode<>(10);
        SingleNode<Integer> n7 = new SingleNode<>(20);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        DuiShuQiUtil.printSingleList(n1);
        SingleNode<Integer> head = smallerEqualBigger(n1, 15);
        DuiShuQiUtil.printSingleList(head);
    }
}
