package com.mashibing.dailyPractice.round2._0222;

import com.mashibing.list.DoubleNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 双链表反转
 */
public class ReverseDoubleList_0222 {
    public static DoubleNode<Integer> reverseDoubleList(DoubleNode<Integer> head) {
        if(head == null) {
            return null;
        }

        DoubleNode<Integer> pre = null;
        DoubleNode<Integer> next = head;
        while(head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }

        return pre;
    }

    // 对数器
    // for test
    public static DoubleNode generateRandomDoubleList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
            size--;
        }
        return head;
    }

    // for test
    public static List<Integer> getDoubleListOriginOrder(DoubleNode<Integer> head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    // for test
    public static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
        DoubleNode end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < origin.size(); i++) {
            if (!origin.get(i).equals(end.value)) {
                return false;
            }
            end = end.last;
        }
        return true;
    }

    public static void main(String[] args) {
//        DoubleNode<Integer> n1 = new DoubleNode<>(1);
//        DoubleNode<Integer> n2 = new DoubleNode<>(2);
//        DoubleNode<Integer> n3 = new DoubleNode<>(3);
//        n1.next = n2;
//        n2.next = n3;
//        n2.last = n1;
//        n3.last = n2;
//
//        DoubleNode<Integer> cur = n1;
//        while(cur != null) {
//            System.out.print(cur.data + " -> ");
//            cur = cur.next;
//        }
//        System.out.println();
//
//        cur = n3;
//        while(cur != null) {
//            System.out.print(cur.data + " -> ");
//            cur = cur.last;
//        }

        int len = 50;
        int value = 100;
        int testTime = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            DoubleNode node3 = generateRandomDoubleList(len, value);
            List<Integer> list3 = getDoubleListOriginOrder(node3);
            node3 = reverseDoubleList(node3);
            if (!checkDoubleListReverse(list3, node3)) {
                System.out.println("Oops3!");
            }

            DoubleNode node4 = generateRandomDoubleList(len, value);
            List<Integer> list4 = getDoubleListOriginOrder(node4);
            node4 = reverseDoubleList(node4);
            if (!checkDoubleListReverse(list4, node4)) {
                System.out.println("Oops4!");
            }

        }
        System.out.println("test finish!");
    }
}
