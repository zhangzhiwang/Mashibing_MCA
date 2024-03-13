package com.mashibing.dailyPractice.round1._2_14;

import com.mashibing.list.SingleNode;

/**
 * 找链表的各种中点
 */
public class FindListMid_0214 {
    /**
     * 1、如果链表的节点个数是偶数个那么返回上中点，奇数个则返回中间的节点
     * 思路：
     */
    public static SingleNode<Integer> findListMid1(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return head;
        }

        SingleNode<Integer> fast = head;
        SingleNode<Integer> slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 2、如果链表的节点个数是偶数个那么返回下中点，奇数个则返回中间的节点
     */
    public static SingleNode<Integer> findListMid2(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return head;
        }

        SingleNode<Integer> fast = head;
        SingleNode<Integer> slow = head;
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
            if(fast.next != null) {
                fast = fast.next;
            }
        }

        return slow;
    }

    /**
     * 3、如果链表的节点个数是偶数个那么返回上中点的前一个节点，奇数个则返回中间节点的前一个节点
     */
    public static SingleNode<Integer> findListMid3(SingleNode<Integer> head) {
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }

        SingleNode<Integer> fast = head.next;
        SingleNode<Integer> slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            if(fast.next != null) {
                slow = slow.next;
            }
        }

        return slow;
    }

    /**
     * 4、如果链表的节点个数是偶数个那么返回上中点，奇数个则返回中间节点的前一个节点
     */
    public static SingleNode<Integer> findListMid4(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return null;
        }

        SingleNode<Integer> fast = head.next;
        SingleNode<Integer> slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 或者下面这种实现也可以
//        while(fast.next != null) {
//            fast = fast.next;
//            if(fast.next != null) {
//                fast = fast.next;
//                slow = slow.next;
//            }
//        }

        return slow;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n2 = new SingleNode<>(2);
        SingleNode<Integer> n3 = new SingleNode<>(3);
        SingleNode<Integer> n4 = new SingleNode<>(4);
        SingleNode<Integer> n5 = new SingleNode<>(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

//        SingleNode<Integer> mid1 = findListMid1(n1);
//        System.out.println(mid1.value);

//        SingleNode<Integer> mid2 = findListMid2(n1);
//        System.out.println(mid2.value);

//        SingleNode<Integer> mid3 = findListMid3(n1);
//        System.out.println(mid3.value);

        SingleNode<Integer> mid4 = findListMid4(n1);
        System.out.println(mid4.value);
    }
}
