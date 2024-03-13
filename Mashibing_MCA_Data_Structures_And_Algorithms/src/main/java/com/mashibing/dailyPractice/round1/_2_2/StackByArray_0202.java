package com.mashibing.dailyPractice.round1._2_2;

/**
 * 用数组实现栈
 */
public class StackByArray_0202 {
    private int[] arr;
    private int head;
    private int size;

    public StackByArray_0202(int len) {
        this.arr = new int[len];
    }

    public void add(int num) {
        if(size == arr.length) {
            throw new RuntimeException("队列已满");
        }

        arr[head++] = num;
        if(head == arr.length) {
            head = 0;
        }

        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("队列已空");
        }

        /**
         * arr[]中内容的解释：
         * 从添加元素的add方法中可以看出，head是下一个存放元素的索引，也就是说head的上一个位置是最后一个元素所在的位置，
         * 所以弹出的元素自然是head的上一个位置。如果head处于0的位置，那么最后一个元素是在arr.length - 1的位置，应该将这个元素弹出，
         * 弹出后head应该往前退一个位置，由于现在head处在0的位置，所以应该推到arr.length - 1的位置。所以三元运算符中的"第二元"的含义就是：
         * 将arr.length - 1的位置的元素给弹出去，然后将arr.length - 1复制给head，即让head到arr.length - 1位置上来，下回添加元素时将新元素放在arr.length - 1位置上，
         * 如果head现在不在0位置，就是三元运算符中的"第三元"：返回head-1位置的元素并让head来到head-1位置，合在一起写就是arr[--head]。
         */
        int result = arr[head == 0 ? head = arr.length - 1 : --head];
        size--;
        return result;
    }

    public int peek() {
        if(size == 0) {
            throw new RuntimeException("队列已空");
        }

        return arr[head == 0 ? arr.length - 1 : head - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        StackByArray_0202 stack = new StackByArray_0202(3);
        System.out.println("head = " + stack.head);
        stack.add(1);
        stack.add(2);
        stack.add(3);
//        stack.add(3);
        System.out.println("size = " + stack.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("peek = " + stack.peek());
        System.out.println("head = " + stack.head);
        System.out.println("--------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("size = " + stack.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("peek = " + stack.peek());
        System.out.println("head = " + stack.head);
        System.out.println("--------------");

//        stack.add(3);
//        System.out.println("size = " + stack.size());
//        System.out.println("isEmpty = " + stack.isEmpty());
//        System.out.println("peek = " + stack.peek());
//        System.out.println("head = " + stack.head);
//        System.out.println("--------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("size = " + stack.size());
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("peek = " + stack.peek());
        System.out.println("head = " + stack.head);
        System.out.println("--------------");

        System.out.println("pop = " + stack.pop());
        System.out.println("size = " + stack.size());
        System.out.println("isEmpty = " + stack.isEmpty());
//        System.out.println("peek = " + stack.peek());
        System.out.println("head = " + stack.head);
        System.out.println("--------------");
    }
}
