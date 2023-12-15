package com.mashibing.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列（非双端队列）实现栈
 * 课程：体系班课时24
 * 思路：见com.mashibing.preInEclipse.senior.stack.StackByQueue注释
 */
public class StackByQueue {
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();

    public void add(int num) {
        if(queue1.isEmpty() && queue2.isEmpty()) {// 如果两个队列全是空，就往第一个队列加
            queue1.offer(num);
        } else if (!queue1.isEmpty()) {// 谁不为空就往谁里面加
            queue1.offer(num);
        } else {
            queue2.offer(num);
        }
    }

    public int pop() {
        if(queue1.isEmpty() && queue2.isEmpty()) {
            throw new RuntimeException("栈为空！");
        }

        int result = 0;
        if(!queue1.isEmpty()) {
            while(!queue1.isEmpty()) {
                if(queue1.size() != 1) {
                    queue2.offer(queue1.poll());
                } else {
                    result = queue1.poll();
                }
            }
        } else {
            while(!queue2.isEmpty()) {
                if(queue2.size() != 1) {
                    queue1.offer(queue2.poll());
                } else {
                    result = queue2.poll();
                }
            }
        }

        return result;
    }

    public int peek() {
        if(queue1.isEmpty() && queue2.isEmpty()) {
            throw new RuntimeException("栈为空！");
        }

        int result = 0;
        if(!queue1.isEmpty()) {
            while(!queue1.isEmpty()) {
                if(queue1.size() != 1) {
                    queue2.offer(queue1.poll());
                } else {
                    result = queue1.poll();
                    queue2.offer(result);
                }
            }
        } else {
            while(!queue2.isEmpty()) {
                if(queue2.size() != 1) {
                    queue1.offer(queue2.poll());
                } else {
                    result = queue2.poll();
                    queue1.offer(result);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        StackByQueue sbq = new StackByQueue();
        sbq.add(1);
        sbq.add(2);
        sbq.add(3);
        sbq.add(4);
        sbq.add(5);

        System.out.println(sbq.peek());
        System.out.println("---------");

        int result = sbq.pop();// 5
        System.out.println("result = " + result);
        System.out.println("---------");

        sbq.pop();// 4
        sbq.pop();// 3
        sbq.add(6);
        System.out.println(sbq.peek());
        result = sbq.pop();// 6
        System.out.println("result = " + result);
    }
}
