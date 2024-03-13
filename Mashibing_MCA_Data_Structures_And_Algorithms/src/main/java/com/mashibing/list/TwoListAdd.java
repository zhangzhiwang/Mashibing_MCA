package com.mashibing.list;

/**
 * 两个链表对应位置上的数相加
 * 题目：给定两个链表的头节点head1和head2，每一个链表都认为从左到右是某个数字的低位到高位，返回两个数字相加之后的结果所代表的的链表。
 * 课程：新手班课时32
 * 思路：见com.mashibing.preInEclipse.junior.list.TwoListAdd注释
 */
public class TwoListAdd {
    public static void main(String[] args) {
        // 链表1：7-3-5-9-9，代表数字：99537
        SingleNode<Integer> head1 = new SingleNode<>(9);
        head1.next = new SingleNode(9);
        head1.next.next = new SingleNode(9);
//        head1.next.next.next = new SingleNode(9);
//        head1.next.next.next.next = new SingleNode(9);

        // 链表2：8-6-9，代表数字：968
        SingleNode<Integer> head2 = new SingleNode<>(1);
//        head2.next = new SingleNode(6);
//        head2.next.next = new SingleNode(9);

        SingleNode<Integer> head = twoListAdd(head1, head2);
        while(head != null) {
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static SingleNode<Integer> twoListAdd(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        if(head1 == null && head2 == null) {// 两个都为空
            return null;
        }

        SingleNode<Integer> head = null;
        if(head1 == null ^ head2 == null) {// 其中有一个为空
            head = head1 == null ? head2 : head1;
            return head;
        }

        int len1 = getLength(head1);
        int len2 = getLength(head2);
        SingleNode<Integer> longList = null;
        SingleNode<Integer> shortList = null;
//        if(len1 >= len2) {// 确定两个链表谁是长链表谁是短链表
//            longList = head1;
//            shortList = head2;
//        } else {
//            longList = head2;
//            shortList = head1;
//        }
        // 以上判断长短的代码可以简写为：
        longList = len1 >= len2 ? head1 : head2;// 谁长谁是长链表
        shortList = longList == head1 ? head2 : head1;// 如果长链表是head1那么head2就是短链表，反之同理
        head = longList;

        // 阶段1：长链表有，短链表也有
        int carry = 0;// 进位信息
        SingleNode<Integer> longListLastNode = null;// 长链表的最后一个节点，用于最后补进位节点
        while(shortList != null) {// 短链表不为空长链表肯定也不为空
            int sum = longList.value + shortList.value + carry;
            longList.value = sum % 10;
            carry = sum / 10;
            longListLastNode = longList;// 在longList跳到下一个节点之前记录当前长链表节点，以免最后补充的进位节点不知道往哪里挂
            longList = longList.next;
            shortList = shortList.next;
        }

        // 阶段2：长链表有短链表已经没有了，从上面循环挑出来就代表短链表没有了
        while(longList != null) {
            int sum = longList.value + carry;
            longList.value = sum % 10;
            carry = sum / 10;
            longListLastNode = longList;// 注意：一定要在跳到下一个之前记录
            longList = longList.next;
        }

        // 阶段3：长链表也遍历完了，看看需不需要在长链表的后面补进位节点
        if(carry != 0) {
            longListLastNode.next = new SingleNode(carry);
        }

        return head;
    }

    public static int getLength(SingleNode<Integer> head) {
        int len = 0;
        while(head != null) {
            len++;
            head = head.next;
        }

        return len;
    }
}
