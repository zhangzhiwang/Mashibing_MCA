package com.mashibing.dailyPractice.round3._No21_30;

import com.mashibing.list.SingleNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 单链表反转
 */
public class ReverseSingleList_0306 {
    public static SingleNode<Integer> reverseSingleList(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return head;
        }

        SingleNode<Integer> c = head;
        SingleNode<Integer> p = null;
        SingleNode<Integer> n = c;
        while(c != null) {
            n = c.next;
            c.next = p;
            p = c;
            c = n;
        }

        return p;
    }

    // 对数器
    public static SingleNode<Integer> testReverseLinkedList(SingleNode<Integer> head) {
        if (head == null) {
            return null;
        }
        ArrayList<SingleNode<Integer>> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        int N = list.size();
        for (int i = 1; i < N; i++) {
            list.get(i).next = list.get(i - 1);
        }
        return list.get(N - 1);
    }

    // for test
    public static SingleNode<Integer> generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        SingleNode<Integer> head = new SingleNode<Integer>((int) (Math.random() * (value + 1)));
        SingleNode<Integer> pre = head;
        while (size != 0) {
            SingleNode<Integer> cur = new SingleNode<Integer>((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    // for test
    public static List<Integer> getLinkedListOriginOrder(SingleNode<Integer> head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    // for test
    public static boolean checkLinkedListReverse(List<Integer> origin, SingleNode<Integer> head) {
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void f(SingleNode<Integer> head) {
        head = head.next;
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            SingleNode<Integer> node1 = generateRandomLinkedList(len, value);
            List<Integer> list1 = getLinkedListOriginOrder(node1);
            node1 = reverseSingleList(node1);
            if (!checkLinkedListReverse(list1, node1)) {
                System.out.println("Oops1!");
            }
        }
        System.out.println("test finish!");
    }
}
