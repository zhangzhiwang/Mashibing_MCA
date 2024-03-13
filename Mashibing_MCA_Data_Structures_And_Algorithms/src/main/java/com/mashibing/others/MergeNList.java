package com.mashibing.others;

import com.mashibing.list.SingleNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并多个有序链表
 * 题目：给定一个数组，这个数组里面的每一个元素都是一个有序链表（单链表）的头部，即每一个元素都代表一个有序链表，将这些链表融合到一起形成一个新的有序链表，返回新链表的头部
 * 课程：新手班课时43
 * 思路：见com.mashibing.preInEclipse.junior.list.MergeNList注释
 */
public class MergeNList {
    public static void main(String[] args) {
//        SingleNode[] arr = {null, null, null};
//        System.out.println(arr.length);// 每一个元素都是null，arr.length=3
//
//        PriorityQueue heap = new PriorityQueue();
//        heap.add(null);// 传入null会报NPE
//        System.out.println(heap.isEmpty());

        // 1-3-5
        SingleNode<Integer> n1 = new SingleNode<>(1);
        SingleNode<Integer> n3 = new SingleNode<>(3);
        SingleNode<Integer> n5 = new SingleNode<>(5);
        n1.next = n3;
        n3.next = n5;
        SingleNode.printSingleNodeList(n1);
        // 2-4-6
        SingleNode<Integer> n2 = new SingleNode<>(2);
        SingleNode<Integer> n4 = new SingleNode<>(4);
        SingleNode<Integer> n6 = new SingleNode<>(6);
        n2.next = n4;
        n4.next = n6;
        SingleNode.printSingleNodeList(n2);
        // 2-4-6
        SingleNode<Integer> n7 = new SingleNode<>(7);
        SingleNode<Integer> n8 = new SingleNode<>(8);
        SingleNode<Integer> n9 = new SingleNode<>(9);
        n7.next = n8;
        n8.next = n9;
        SingleNode.printSingleNodeList(n7);

        SingleNode[] arr = {n1, n2, n7};

        System.out.println("------------");
        SingleNode<Integer> newHead = mergeNList(arr);
        SingleNode.printSingleNodeList(newHead);
    }

    public static SingleNode<Integer> mergeNList(SingleNode[] headArr) {
        if (headArr == null || headArr.length == 0) {
            return null;
        }

        // heap默认是小根堆，但是SingleNode是一个符合类型，必须告诉它怎么排序
        PriorityQueue<SingleNode<Integer>> heap = new PriorityQueue<>(new Comparator<SingleNode<Integer>>() {
            @Override
            public int compare(SingleNode<Integer> o1, SingleNode<Integer> o2) {
                return o1.value - o2.value;// 如果为负数返回o1，正好正序排序
            }
        });

        for(SingleNode node : headArr) {
            if(node != null) {// 防止headArr出现这种情况：[null, null, null]，这种情况headArr的长度是3，最开始的校验校验不出来
                heap.add(node);// 如果不判空的话，PriorityQueue的add方法如果传入一个null会报NPE
            }
        }

        if(heap.isEmpty()) {
            return null;
        }

        SingleNode<Integer> head = heap.poll();// 弹出的第一个元素一定是最终返回的链表的头部，最终返回它即可
        SingleNode<Integer> cur = head;// 既然head是最终被返回的，所以中途就不要再动它了，如果后续过程需要head来参与运算，那么要找一个替身来代替它做操作，这里的cur就充当了"替身"

        while(!heap.isEmpty()) {
            if(cur.next != null) {
                heap.add(cur.next);
            }
            SingleNode<Integer> node = heap.poll();
            cur.next = node;
            cur = cur.next;
        }

        return head;
    }
}
