package com.mashibing.dailyPractice.round5;

import com.mashibing.list.SingleNode;
import com.mashibing.others.DuiShuQiUtil;

/**
 * 把一个无序的单向链表按照某个值调整成左边小于该值、中间相等、右边大于该值的形式（小于区和大于区无需排序）
 */
public class SmallerEqualBigger_0807 {
    public static SingleNode<Integer> smallerEqualBigger(SingleNode<Integer> head, int k) {
        if(head == null) {
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
            if(head.value < k) {
                if(sH == null) {
                    sT = sH = head;
                } else {
                    sT = sT.next = head;
                }
            } else if (head.value == k) {
                if(eH == null) {
                    eH = head;
                } else {
                    eT.next = head;
                }

                eT = head;
            } else {
                if(mH == null) {
                    mH = head;
                } else {
                    mT.next = head;
                }

                mT = head;
            }

            head = n;
        }

        if(sH != null) {
            if(eH != null) {
                sT.next = eH;
            } else if (mH != null) {
                sT.next = mH;
            }
        }

        if(eH != null) {
            if (mH != null) {
                eT.next = mH;
            }
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

        // 1、只有小于区
//        int k = 100;
//        DuiShuQiUtil.printSingleList(n1);
//        SingleNode<Integer> head = smallerEqualBigger(n1, k);
//        DuiShuQiUtil.printSingleList(head);

        // 2、只有大于区
//        int k = 1;
//        DuiShuQiUtil.printSingleList(n1);
//        SingleNode<Integer> head = smallerEqualBigger(n1, k);
//        DuiShuQiUtil.printSingleList(head);

        // 3、只有等于区
//        n1 = new SingleNode<>(80);
//        n2 = new SingleNode<>(80);
//        n3 = new SingleNode<>(80);
//        n4 = new SingleNode<>(80);
//        n5 = new SingleNode<>(80);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
//        int k = 80;
//        DuiShuQiUtil.printSingleList(n1);
//        SingleNode<Integer> head = smallerEqualBigger(n1, k);
//        DuiShuQiUtil.printSingleList(head);

        // 4、只有小于区和等于区
//        n1 = new SingleNode<>(60);
//        n2 = new SingleNode<>(80);
//        n3 = new SingleNode<>(50);
//        n4 = new SingleNode<>(80);
//        n5 = new SingleNode<>(30);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
//        int k = 80;
//        DuiShuQiUtil.printSingleList(n1);
//        SingleNode<Integer> head = smallerEqualBigger(n1, k);
//        DuiShuQiUtil.printSingleList(head);

        // 5、只有小于区和大于区
//        int k = 45;
//        DuiShuQiUtil.printSingleList(n1);
//        SingleNode<Integer> head = smallerEqualBigger(n1, k);
//        DuiShuQiUtil.printSingleList(head);

        // 6、只有等于区和大于区
        n1 = new SingleNode<>(160);
        n2 = new SingleNode<>(80);
        n3 = new SingleNode<>(150);
        n4 = new SingleNode<>(80);
        n5 = new SingleNode<>(130);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        int k = 80;
        DuiShuQiUtil.printSingleList(n1);
        SingleNode<Integer> head = smallerEqualBigger(n1, k);
        DuiShuQiUtil.printSingleList(head);
    }
}
