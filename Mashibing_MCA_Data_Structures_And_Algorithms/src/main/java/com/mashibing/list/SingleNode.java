package com.mashibing.list;

/**
 * 单链表节点
 */
public class SingleNode<T> {
    public T value;
    public SingleNode<T> next;

    public SingleNode(T value) {
        this.value = value;
    }

    public static <T> void printSingleNodeList(SingleNode<T> head) {
        while(head != null) {
            System.out.print(head.value);
            head = head.next;
            if(head != null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "SingleNode{" +
                "value=" + value +
                '}';
    }
}
