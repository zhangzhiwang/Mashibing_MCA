package com.mashibing.list;

/**
 * 单链表节点
 */
public class SingleNode<T> {
    public T data;
    public SingleNode next;

    public SingleNode(T data) {
        this.data = data;
    }

    public static <T> void printSingleNodeList(SingleNode<T> head) {
        while(head != null) {
            System.out.print(head.data);
            head = head.next;
            if(head != null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
