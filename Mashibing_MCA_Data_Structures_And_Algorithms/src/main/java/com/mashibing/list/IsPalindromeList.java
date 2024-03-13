package com.mashibing.list;

/**
 * 题目：给定一个单链表的头结点，判断该链表是否为回文结构
 * 解释：回文：1->2->3->2->1
 * 思路：
 * 1、利用快慢指针找到链表的中点
 * 2、将中点的右边进行链表的反转，变成：1->2->3<-2<-1
 * 3、指定两个指针L和R，分别为做左链表的头部和右链表的头部
 * 4、两个头部比较数值是否相等，如果相等则逐个进行比较
 * 5、无论链表是否为回文结构，最终都要将原链表给调回原来的顺序
 * 课程：体系班课时77
 */
public class IsPalindromeList {
    public static boolean isPalindromeList(SingleNode<Integer> head) {
        if (head == null || head.next == null) {// 人为规定：空链表和单节点链表就是回文结构
            return true;
        }

        // 利用快慢指针找到链表的中点
        SingleNode<Integer> fast = head;
        SingleNode<Integer> slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        /*
         上面的循环结束后慢指针就是链表的中点，
         下面要做的就是将中点右边的部分反转
         */
        SingleNode<Integer> cur = slow.next;
        slow.next = null;
        SingleNode<Integer> pre = slow;
        SingleNode<Integer> next = cur;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        /*
         上线的while循环结束后pre就是右边链表逆序后的头部，
         下面要做的就是所有两个链表进行比较
         */
        SingleNode<Integer> R = pre;
        SingleNode<Integer> L = head;
        boolean result = true;
        while (L != null && R != null) {
            if (L.value != R.value) {
                result = false;
                break;
            }
            L = L.next;
            R = R.next;
        }
        // 上面循环退出来了就说明已经比较完了，原链表是不是回文结构就已经有结果了，下面要做的就是将原链表还原
        cur = pre;
        pre = null;
        next = cur;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 以上用到的所有临时指针变量都是局部变量，方法运行结束变量销毁，所以不需要考虑内存泄漏的问题，如果用到的临时变量是全局变量，那就要考虑内存泄漏的问题了
        return result;
    }

    public static void main(String[] args) {
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n2 = new SingleNode<>(1);
//        SingleNode<Integer> n3 = new SingleNode<>(3);
//        SingleNode<Integer> n4 = new SingleNode<>(3);
//        SingleNode<Integer> n5 = new SingleNode<>(2);
//        SingleNode<Integer> n6 = new SingleNode<>(1);
        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
//        n3.next = n5;
//        n5.next = n6;

        SingleNode<Integer> head = n1;
        while (head != null) {
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.println();
        System.out.println("---------");

        boolean result = isPalindromeList(n1);
        System.out.println("result = " + result);
    }
}
