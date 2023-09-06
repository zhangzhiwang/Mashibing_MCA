package com.mashibing.others;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 优先级队列
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();// java中的优先级队列实际上就是一个堆，默认是小根堆
        heap.add(5);
        heap.add(3);
        heap.add(7);
        heap.add(9);
        heap.add(1);
        System.out.println(heap.peek());// 查看堆顶元素，由于是默认是小根堆，所以返回的是最小的元素。peek方法是查看但不弹出
        System.out.println("---------");

        while(!heap.isEmpty()) {
            System.out.print(heap.poll() + "\t");// poll方法是弹出堆顶元素
        }
        System.out.println();
        System.out.println("============");

        // 大根堆
        PriorityQueue<Integer> heap2 = new PriorityQueue<>(new MyHeapComparator());// 由于传入了比较器，比较器的实现是按照大小倒序排序的，所以变成了大根堆
        heap2.add(5);
        heap2.add(3);
        heap2.add(7);
        heap2.add(9);
        heap2.add(1);
        System.out.println(heap2.peek());// 查看堆顶元素，由于是大根堆，所以返回的是最大的元素
        System.out.println("---------");

        while(!heap2.isEmpty()) {
            System.out.print(heap2.poll() + "\t");// poll方法是弹出堆顶元素
        }
        System.out.println();
        System.out.println("============");

        PriorityQueue<ComparatorTest.Student> heap3 = new PriorityQueue<>(new ComparatorTest.MyComparator3());
        ComparatorTest.Student s1 = new ComparatorTest.Student(3, "a", 10);
        ComparatorTest.Student s2 = new ComparatorTest.Student(7, "b", 30);
        ComparatorTest.Student s3 = new ComparatorTest.Student(5, "c", 20);
        ComparatorTest.Student s4 = new ComparatorTest.Student(1, "d", 19);
        ComparatorTest.Student s5 = new ComparatorTest.Student(9, "e", 15);
        heap3.add(s1);
        heap3.add(s2);
        heap3.add(s3);
        heap3.add(s4);
        heap3.add(s5);

        System.out.println(heap3.peek());// 按照比较指定的规则返回排序后的堆顶元素
        System.out.println("---------");

        while(!heap3.isEmpty()) {
            System.out.println(heap3.poll());// poll方法是弹出堆顶元素
        }
        System.out.println();
        System.out.println("============");
    }

    /**
     * 按照int倒叙排序
     */
    static class MyHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            if(o1 > o2) {
                return -1;
            } else if (o1 < 02) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
