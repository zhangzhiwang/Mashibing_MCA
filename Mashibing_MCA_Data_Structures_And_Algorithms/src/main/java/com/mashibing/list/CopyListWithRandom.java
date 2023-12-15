package com.mashibing.list;

import com.mashibing.others.DuiShuQiUtil;

/**
 * 题目：给定一个单链表，每一个节点Node都有两个指针，一个是指向它下一个节点的next指针，通过next指针可以组成一个正常的单链表；还有一个random指针，该指针可以指向链表中随机的任意一个节点，也可以指向null。要求复制一个这样的链表出来，返回复制链表的头部。
 * 课程：体系班课时80
 * 思路：
 * 1、每一个节点都复制一个自己出来，然后复制的节点挂在自己后面，复制节点的next指向原节点的下一个节点。
 * 比如：原链表是1->2->3->null，经过这一步变成：1->1'->2->2'->3->3'->null，其中1'是1的复制节点，以此类推，"->"代表每个节点的next指针
 * 2、原链表每个节点的random指针为：1=>3、2=>1、3=>3，其中"=>"代表random指针。拿第一个节点1举例子：1的random指针指向了3，那么1'的random指针应该指向3'，怎么做到呢？
 * 通过1可以找到1'，再通过1的random指针找到3，而3的复制节点3'就是3的next，所以3'能找到，1'的random指针指向3'，其它的以此类推。
 * 3、经过上面两步，原链表就已经复制出来了，但是原链表和复制链表混插在一起了，最后一步就是把它们分开。
 * 4、整体的时间复杂度O(N)，额外空间复杂度O(1)（以后如果不特别说明"额外空间复杂度"简称为"空间复杂度"）
 */
public class CopyListWithRandom {
    static class SingleNodeWithRandom {
        public int data;
        public SingleNodeWithRandom next;
        public SingleNodeWithRandom random;

        public SingleNodeWithRandom(int data) {
            this.data = data;
        }
    }

    public SingleNodeWithRandom copyListWithRandom(SingleNodeWithRandom head) {
        if(head == null) {
            return null;
        }
        /**
         * 1->2->3->null
         * 1->1'->2->2'->3->3'->null
         */
        SingleNodeWithRandom cur = head;
        SingleNodeWithRandom oriNext;// 以下三个步骤oriNext只记录最原始链表的下一个节点
        while (cur != null) {
            oriNext = cur.next;
            cur.next = new SingleNodeWithRandom(cur.data * 10);// 注意：按照题目要求拷贝链表的节点的值应该和原链表节点的值一样，但是为了测试能够区分出来这里将拷贝节点的值扩大了10倍，面试时不要这样做
            cur.next.next = oriNext;
            cur = oriNext;
        }

        /*
         设置copy链表每一个节点的random指针
         1=>3、2=>1、3=>3
         1'=>3'、2'=>1'、3'=>3'
         */
        cur = head;// 将cur重置为老链表的头部
        while(cur != null) {
            cur.next.random = cur.random != null ? cur.random.next : null;
            cur = cur.next.next;
        }

        /*
         将原链表和复制链表分开
         1->1'->2->2'->3->3'->null
         */
        cur = head;// 将cur重置为老链表的头部
        SingleNodeWithRandom copyHead = cur.next;
        SingleNodeWithRandom copyCur = copyHead;
        while(cur != null) {
            oriNext = cur.next.next;
            copyCur.next = oriNext == null ? null : oriNext.next;
            cur.next = oriNext;
            cur = oriNext;
            copyCur = copyCur.next;
        }

        return copyHead;
    }

    public static void main(String[] args) {
        SingleNodeWithRandom n1 = new SingleNodeWithRandom(1);
        SingleNodeWithRandom n2 = new SingleNodeWithRandom(2);
        SingleNodeWithRandom n3 = new SingleNodeWithRandom(3);
        n1.next = n2;
        n2.next = n3;
        n1.random = n3;
        n2.random = n1;
        n3.random = n3;

        System.out.println("原始链表：");
        SingleNodeWithRandom cur=n1;
        while(cur != null) {
            System.out.print(cur.data + " -> ");
            cur = cur.next;
        }
        System.out.println();
        System.out.println("---------------");

        cur=n1;
        while(cur != null) {
            System.out.print(cur.data + " => " + (cur.random == null ? "null" : cur.random.data));
            cur = cur.next;
            System.out.println();
        }
        System.out.println("---------------");

        System.out.println("拷贝后的新链表：");
        SingleNodeWithRandom copyHead = new CopyListWithRandom().copyListWithRandom(n1);
        cur=copyHead;
        while(cur != null) {
            System.out.print(cur.data + " -> ");
            cur = cur.next;
        }
        System.out.println();
        System.out.println("---------------");

        cur=copyHead;
        while(cur != null) {
            System.out.print(cur.data + " => " + (cur.random == null ? "null" : cur.random.data));
            cur = cur.next;
            System.out.println();
        }
        System.out.println("---------------");
    }
}
