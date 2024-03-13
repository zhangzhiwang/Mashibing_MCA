package com.mashibing.dailyPractice.round1._2_2;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用队列（非双端队列）实现栈
 */
public class StackByQueue_0202 {
    private List<Integer> listA = new LinkedList<>();
    private List<Integer> listB = new LinkedList<>();

    public void add(int num) {
        listA.add(num);
    }

    public int pop() {
        if(listA.isEmpty()) {
            throw new RuntimeException("栈为空");
        }

        int N = listA.size();
        for(int i = 0; i < N - 1; i++){// 不能写成i < listA.size() - 1，因为在循环体里remove操作会使listA的size减小，所以每循环一次size减小1，导致循环条件变化
            listB.add(listA.remove(0));
        }

        int result = listA.remove(0);

        List<Integer> tmp = listA;
        listA = listB;
        listB = tmp;
        return result;
    }

    public int peek() {
        if(listA.isEmpty()) {
            throw new RuntimeException("栈为空");
        }

        return listA.get(listA.size() - 1);
    }

    public boolean isEmpty() {
        return listA.isEmpty();
    }

    public int size() {
        return listA.size();
    }


    public static void main(String[] args) {
//        List<Integer> list = new LinkedList<>();
//        list.add(10);
//        list.add(20);
//        list.add(30);
//        System.out.println(list.get(list.size() - 1));
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));

        StackByQueue_0202 stack = new StackByQueue_0202();
        stack.add(1);
        stack.add(2);
        stack.add(3);

        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println("------------");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}