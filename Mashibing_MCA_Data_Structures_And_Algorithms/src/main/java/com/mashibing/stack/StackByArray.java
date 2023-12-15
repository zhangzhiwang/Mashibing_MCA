package com.mashibing.stack;

/**
 * 用数组实现栈
 * 课程：体系班课时22
 * 思路：见com.mashibing.preInEclipse.senior.stack.StackByArray注释
 */
public class StackByArray {
    private int[] arr;
    private int nextIndex;// 尾元素的下一个元素
    private int size;


    public StackByArray(int arrLength) {
        if(arrLength <= 0) {
            throw new RuntimeException("数组长度有误！");
        }

        arr = new int[arrLength];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(int num) {
        if(size == arr.length) {
            throw new RuntimeException("栈已满！");
        }

        arr[nextIndex++] = num;
        size++;
    }

    public int pop() {
        if(size == 0) {
            throw new RuntimeException("栈已空！");
        }

        int result = arr[--nextIndex];
        //arr[nextIndex] = null;// 注意：如果数组里面存放的是对象，要把nextIndex的位置置为null，以免出现内存泄漏
        size--;
        return result;
    }

    public int peek() {
        if(size == 0) {
            throw new RuntimeException("栈已空！");
        }

        return arr[nextIndex - 1];
    }

    public static void main(String[] args) {
        StackByArray sba = new StackByArray(5);
        System.out.println(sba.isEmpty());
        System.out.println(sba.size());
        System.out.println("-----------");

        sba.add(1);
        System.out.println(sba.isEmpty());
        System.out.println(sba.size());
        System.out.println("peek：" + sba.peek());
        System.out.println("-----------");

        sba.add(2);
        sba.add(3);
        sba.add(4);
        sba.add(5);
        System.out.println(sba.isEmpty());
        System.out.println(sba.size());
        System.out.println("peek：" + sba.peek());
        System.out.println("-----------");

        int result = sba.pop();
        System.out.println(sba.isEmpty());
        System.out.println(sba.size());
        System.out.println("peek：" + sba.peek());
        System.out.println("-----------");

        sba.pop();
        sba.pop();
        sba.pop();
        sba.pop();
        System.out.println(sba.isEmpty());
        System.out.println(sba.size());
//        System.out.println("peek：" + sba.peek());
        System.out.println("-----------");
    }
}
