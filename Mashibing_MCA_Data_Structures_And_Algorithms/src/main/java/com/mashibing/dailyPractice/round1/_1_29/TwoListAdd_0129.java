package com.mashibing.dailyPractice.round1._1_29;

/**
 * 给定两个链表的头节点head1和head2，每一个链表都认为从左到右是某个数字的低位到高位，返回两个数字相加之后的结果所代表的的链表（两个数都是非负数）。
 */
public class TwoListAdd_0129 {
    /**
     * 由于本题目要在leetcode上去验证，所以要使用leetcode给出的节点类
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null && l2 != null) {
            return l2;
        } else if (l1 != null && l2 == null) {
            return l1;
        }

        int size1 = size(l1);
        int size2 = size(l2);
        ListNode longHead = size1 >= size2 ? l1 : l2;
        ListNode shortHead = longHead == l1 ? l2 : l1;
        int tmp = 0;// 进位信息

        ListNode resultHead = null;// 如果返回一个全新的链表那么空间复杂度就变成O(N)了，版本二会对此做出优化——将结果合并到原长链表中，返回原长链表头结点
        ListNode tmpHead = resultHead;
        while (shortHead != null) {
            int firstSumValue = longHead.val + shortHead.val + (tmp == 0 ? 0 : tmp--);
            if (firstSumValue >= 10) {// 注意：等于10就进位，所以不要丢掉等号
                tmp++;
            }
            int firstValue = firstSumValue % 10;
            shortHead = shortHead.next;
            longHead = longHead.next;

            ListNode node = new ListNode(firstValue);
            if (tmpHead == null) {
                resultHead = node;
                tmpHead = node;
            } else {
                tmpHead.next = node;
                tmpHead = tmpHead.next;
            }
        }

        while (longHead != null) {
            int firstSumValue = longHead.val + (tmp == 0 ? 0 : tmp--);
            if (firstSumValue >= 10) {
                tmp++;
            }
            int firstValue = firstSumValue % 10;
            longHead = longHead.next;

            ListNode node = new ListNode(firstValue);
            tmpHead.next = node;
            tmpHead = tmpHead.next;
        }

        if(tmp > 0) {
            ListNode node = new ListNode(1);
            tmpHead.next = node;
        }

        return resultHead;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null && l2 != null) {
            return l2;
        } else if (l1 != null && l2 == null) {
            return l1;
        }

        int size1 = size(l1);
        int size2 = size(l2);
        ListNode longHead = size1 >= size2 ? l1 : l2;
        ListNode shortHead = longHead == l1 ? l2 : l1;
        ListNode retHeadtmp = longHead;
        ListNode longLastNode = null;
        int tmp = 0;// 进位信息

        while (shortHead != null) {
            int sumValue = longHead.val + shortHead.val + (tmp == 0 ? 0 : tmp--);
            if (sumValue >= 10) {
                tmp++;
            }
            int value = sumValue % 10;
            longHead.val = value;// 由于两个数都是非负数，所以将结果合并到长链表里面

            longLastNode = longHead;
            longHead = longHead.next;
            shortHead = shortHead.next;
        }

        while (longHead != null) {
            int sumValue = longHead.val + (tmp == 0 ? 0 : tmp--);
            if (sumValue >= 10) {
                tmp++;
            }
            int value = sumValue % 10;
            longHead.val = value;
            longLastNode = longHead;
            longHead = longHead.next;
        }

        if(tmp > 0) {
            ListNode node = new ListNode(1);
            longLastNode.next = node;
        }

        return retHeadtmp;
    }

    private int size(ListNode head) {
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }

        return size;
    }

    public static void main(String[] args) {
//        TwoListAdd_0129 t1 = new TwoListAdd_0129();
//        TwoListAdd_0129 t2 = t1;
//        System.out.println(t1);
//        System.out.println(t2);
//        System.out.println("-------------");
//
//        t1 = null;
//        System.out.println(t1);
//        System.out.println(t2);

//        ListNode h1 = new ListNode(5);
//        ListNode n6 = new ListNode(6);
//        ListNode n7 = new ListNode(7);
//        ListNode n9 = new ListNode(9);
//        h1.next = n6;
//        n6.next = n7;
//        n7.next = n9;
//
//        ListNode h2 = new ListNode(8);
//        ListNode n7_1 = new ListNode(7);
//        ListNode n6_1 = new ListNode(6);
//        h2.next = n7_1;
//        n7_1.next = n6_1;
//
//        ListNode listNode = new TwoListAdd_0129().addTwoNumbers(h1, h2);
//        System.out.println(listNode.val);
//        System.out.println(listNode.next.val);
//        System.out.println(listNode.next.next.val);
//        System.out.println(listNode.next.next.next.val);
//        System.out.println(listNode.next.next.next.next.val);

        // leetcode测试地址：https://leetcode.com/problems/add-two-numbers/
    }
}
