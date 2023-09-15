package com.mashibing.list;

/**
 * k个节点的组内反转
 * 题目：给定一个单向链表，每k个节点分为一组，每组内的节点进行反转，不足k各节点的不分组也不反转（原样保持不变），最后组成一个新的完整的链表并返回新链表的头结点。
 * 课程：新手班课时31
 * 思路：见com.mashibing.preInEclipse.junior.list.ReverseNodesInKGroup注释
 */
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        SingleNode<String> a = new SingleNode<>("a");
        SingleNode<String> b = new SingleNode<>("b");
        SingleNode<String> c = new SingleNode<>("c");
        SingleNode<String> d = new SingleNode<>("d");
        SingleNode<String> e = new SingleNode<>("e");
        SingleNode<String> f = new SingleNode<>("f");
        SingleNode<String> g = new SingleNode<>("g");
//        SingleNode<String> h = new SingleNode<>("h");
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
//        g.next = h;

        SingleNode<String> head = reverseNodesInKGroup(a, 30);
        while(head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println();
//        SingleNode<String> n2 = n1;
//        SingleNode<String> n3 = n2;
//
//        System.out.println(n1 == n3);// "=="比较的是引用所指对象的内存地址


    }

    public static SingleNode<String> reverseNodesInKGroup(SingleNode<String> head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        // 先看一下整个链表够不够分一组的
        SingleNode<String> end = findEnd(head, k);
        if (end == null) {// 说明整个链表都不够分一组的，不需要反转，直接返回头结点即可
            return head;
        }

        SingleNode<String> start = head;
        head = end;// 如果整个链表够分一组的，那么最终反转后的新链表头部就是第一组的尾结点
        SingleNode<String> lestEnd = null;// 上一组的尾结点
        do {
            reverseFromStartToEnd(start, end);
            lestEnd = start;
//            if(lestEnd.next == null ) {
//                return head;
//            }
            start = lestEnd.next;
            end = findEnd(start, k);
            if(end == null) {
                return head;
            }
            lestEnd.next = end;
        } while (lestEnd.next != null);// 如果上一组尾结点的下一个节点不是空说明有可能会存在下一组，继续循环

        return head;
    }

    public static SingleNode<String> findEnd(SingleNode<String> start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }

        return start;
    }

    public static void reverseFromStartToEnd(SingleNode<String> start, SingleNode<String> end) {
        SingleNode<String> cur = start;
        SingleNode<String> pre = null;
        SingleNode<String> next = null;
        end = end.next;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        start.next = end;// 这一步一定不要忘
    }
}
