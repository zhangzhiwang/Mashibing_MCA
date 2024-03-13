package com.mashibing.list;

/**
 * 题目：假如给定一个链表为1->2->3->4->5->6->7->8，将链表调成1->8->2->7->3->6->4->5（链表节点的个数可能为奇数个也可能为偶数个）
 * 思路：按照com.mashibing.list.IsPalindromeList的思路就可以做到
 * 课程：体系班课时77
 */
public class IsPalindromeList2 {
    /**
     * 之所以该方法没有设置返回值是因为调整完之后新链表的头部就是原链表的头部，所以没必要新链表头部
     * @param head
     */
    public static void isPalindromeList2(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return;
        }

        // 1、找中点
        SingleNode<Integer> fast = head;
        SingleNode<Integer> slow = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转右边的链表
        SingleNode<Integer> pre = slow;
        SingleNode<Integer> cur = slow.next;
        SingleNode<Integer> next = cur;
        pre.next = null;
        while(cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 设置所有两个指针L和R并进行连接
        SingleNode<Integer> L = head;
        SingleNode<Integer> R = pre;
        SingleNode<Integer> lNext = L;
        SingleNode<Integer> rNext = R;
        while(L != null && R != null) {
            lNext = L.next;
            rNext = R.next;
            L.next = R;
            L = lNext;
            R.next = L;
            R = rNext;
        }
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n2 = new SingleNode<>(2);
        SingleNode<Integer> n3 = new SingleNode<>(3);
//        SingleNode<Integer> n4 = new SingleNode<>(4);
//        SingleNode<Integer> n5 = new SingleNode<>(5);
//        SingleNode<Integer> n6 = new SingleNode<>(6);
//        SingleNode<Integer> n7 = new SingleNode<>(7);
        n1.next = n2;
        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
//        n5.next = n6;
//        n6.next = n7;

        SingleNode<Integer> head = n1;
        while(head != null) {
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.println();
        System.out.println("------------");

        isPalindromeList2(n1);

        head = n1;
        while(head != null) {
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.println();
        System.out.println("------------");
    }
}
