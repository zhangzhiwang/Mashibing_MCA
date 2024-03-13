package com.mashibing.dailyPractice.round1._2_2;

import java.util.Stack;

/**
 * 使用栈实现队列（非双端队列）
 */
public class QueueByStack_0202 {
    // 见实现：com.mashibing.dailyPractice.round2._0224.QueueByStack_0224
//    private Stack<Integer> stackA = new Stack<>();
//    private Stack<Integer> stackB = new Stack<>();
//
//    public void add(int num) {
//        stackA.push(num);
//    }
//
//    public int pop() {
//        if(stackA.isEmpty()) {
//            throw new RuntimeException("队列为空");
//        }
//
//        if(stackB.isEmpty()) {
//            while(!stackA.isEmpty()) {
//                stackB.push(stackA.pop());
//            }
//        }
//
//        return stackB.pop();
//    }
//
//    public int peek() {
//        if(stackA.isEmpty()) {
//            throw new RuntimeException("队列为空");
//        }
//
//        if(stackB.isEmpty()) {
//            while(!stackA.isEmpty()) {
//                stackB.push(stackA.pop());
//            }
//        }
//
//        return stackB.peek();
//    }
//
//    public boolean isEmpty() {
//        return stackA.isEmpty();
//    }
//
//    public int size() {
//        return stackA.size();
//    }
//
//    public static void main(String[] args) {
//        QueueByStack_0202 queue = new QueueByStack_0202();
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//
//        System.out.println("size = " + queue.size());
//        System.out.println("-----------");
//
//        System.out.println("pop = " + queue.pop());
//        System.out.println("size = " + queue.size());
//
//
//
//
//
//
//////        System.out.println("peek = " + queue.peek());
////        System.out.println("empty = " + queue.isEmpty());
////
////        System.out.println("B size = " + queue.stackB.size());
////        System.out.println("B peek = " + queue.stackB.peek());
////        System.out.println("A size = " + queue.stackA.size());
////        System.out.println("-----------");
////
////        queue.add(4);
////        System.out.println("B size = " + queue.stackB.size());
////        System.out.println("B peek = " + queue.stackB.peek());
////        System.out.println("A size = " + queue.stackA.size());
////        System.out.println("-----------");
////
////        System.out.println("pop = " + queue.pop());
////        queue.add(5);
////        System.out.println("pop = " + queue.pop());
////        System.out.println("pop = " + queue.pop());
////        System.out.println("B size = " + queue.stackB.size());
//////        System.out.println("B peek = " + queue.stackB.peek());
////        System.out.println("A size = " + queue.stackA.size());
////        System.out.println("-----------");
//    }
}
