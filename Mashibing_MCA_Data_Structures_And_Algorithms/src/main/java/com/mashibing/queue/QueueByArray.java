package com.mashibing.queue;

/**
 * 用数组实现队列
 * 课程：体系班课时22
 * 思路：见com.mashibing.preInEclipse.senior.list.QueueByArray注释
 */
public class QueueByArray {
    private int[] arr;
    private int headIndex;
    private int tailIndex;// tailIndex是队列中最后一个元素的下一个位置，也就是即将被插入的位置
    private int size;

    public QueueByArray(int len) {
        if(len <= 0) {
            throw new RuntimeException("长度必须大于0");
        }
        arr = new int[len];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int t) {
        if(size >= arr.length) {
            throw new RuntimeException("队列已满");
        }
        if(tailIndex == arr.length) {
            tailIndex = 0;
        }

        arr[tailIndex++] = t;
        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("队列已空");
        }

        int result = arr[headIndex++];

        if(headIndex == arr.length) {
            headIndex = 0;
        }

        size--;
        return result;
    }

    public int peek() {
        if(size == 0) {
            throw new RuntimeException("队列已空");
        }

        return arr[headIndex];
    }

    public static void main(String[] args) {
        QueueByArray qba = new QueueByArray(3);
        System.out.println(qba.size());
        System.out.println(qba.isEmpty());
        System.out.println("--------------");

        qba.add(1);
        qba.add(2);
        qba.add(3);
//        System.out.println(qba.size());
//        System.out.println(qba.isEmpty());
//        System.out.println("--------------");
//
//
        qba.pop();
//        qba.pop();
//        qba.pop();
//        while(!qba.isEmpty()) {
//            System.out.print(qba.pop() + "\t");
//        }
//        System.out.println();


        qba.add(4);
        qba.pop();
        qba.add(5);
        qba.pop();
        qba.add(6);
        System.out.println(qba.peek());
    }
}
