package com.mashibing.list;

/**
 * 找链表的中间节点
 * 思路：快慢指针初始位置的问题
 * 课程：体系班课时76
 */
public class FindListMid {
    /**
     * 如果链表的节点个数是偶数个那么返回上中点，奇数个则返回中间的节点
     * @param head
     * @return
     */
    public static SingleNode<Integer> findListMid1(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return head;
        }

        SingleNode<Integer> slow = head;
        SingleNode<Integer> fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 如果链表的节点个数是偶数个那么返回下中点，奇数个则返回中间的节点
     * @param head
     * @return
     */
    public static SingleNode<Integer> findListMid2(SingleNode<Integer> head) {
        if(head == null || head.next == null) {
            return head;
        }

        SingleNode<Integer> slow = head.next;
        SingleNode<Integer> fast = head.next;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 如果链表的节点个数是偶数个那么返回上中点的前一个节点，奇数个则返回中间节点的前一个节点
     * @param head
     * @return
     */
    public static SingleNode<Integer> findListMid3(SingleNode<Integer> head) {
        if(head == null || head.next == null || head.next.next == null) {// 这种需求就必须要求链表至少有三个节点
            return null;
        }

        SingleNode<Integer> slow = head;
        SingleNode<Integer> fast = head.next.next;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 如果链表的节点个数是偶数个那么返回上中点，奇数个则返回中间节点的前一个节点
     * @param head
     * @return
     */
    public static SingleNode<Integer> findListMid4(SingleNode<Integer> head) {
        if(head == null || head.next == null || head.next.next == null) {// 这种需求就必须要求链表至少有三个节点
            return head;
        }

        SingleNode<Integer> slow = head;
        SingleNode<Integer> fast = head.next;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
//        SingleNode<Integer> n2 = new SingleNode<>(2);
//        SingleNode<Integer> n3 = new SingleNode<>(3);
//        SingleNode<Integer> n4 = new SingleNode<>(4);
//        SingleNode<Integer> n5 = new SingleNode<>(5);
//        SingleNode<Integer> n6 = new SingleNode<>(6);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
//        n5.next = n6;

        SingleNode<Integer> head = n1;
        while(head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println();
        System.out.println("--------");

//        SingleNode<Integer> mid = findListMid1(n1);
//        System.out.println("mid.data = " + mid.data);
//        System.out.println("--------");

//        SingleNode<Integer> mid = findListMid2(n1);
//        System.out.println("mid.data = " + mid.data);
//        System.out.println("--------");

//        SingleNode<Integer> mid = findListMid3(n1);
//        System.out.println("mid.data = " + mid.data);
//        System.out.println("--------");

        SingleNode<Integer> mid = findListMid4(n1);
        System.out.println("mid.data = " + mid.data);
        System.out.println("--------");
    }
}
