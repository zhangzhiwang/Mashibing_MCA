package com.mashibing.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 优先级队列测试
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(8);
        pq.add(1);
        System.out.println(pq.poll());
        System.out.println(pq.size());
        System.out.println("--------------");

        PriorityQueue<PriorityQueueTest_Student> pq2 = new PriorityQueue<>(new IdAsc());
        pq2.add(new PriorityQueueTest_Student(3, 10, "c"));
        pq2.add(new PriorityQueueTest_Student(1, 20, "b"));
        pq2.add(new PriorityQueueTest_Student(2, 30, "a"));

        System.out.println(pq2.poll());
        System.out.println(pq2.poll());
        System.out.println(pq2.poll());
    }

    static class PriorityQueueTest_Student {
        public int id;
        public int age;
        public String name;

        public PriorityQueueTest_Student(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "PriorityQueueTest_Student{" +
                    "id=" + id +
                    ", age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class AgeDesc implements Comparator<PriorityQueueTest_Student> {
        @Override
        public int compare(PriorityQueueTest_Student o1, PriorityQueueTest_Student o2) {
            return o2.age - o1.age;
        }
    }

    static class IdAsc implements Comparator<PriorityQueueTest_Student> {
        @Override
        public int compare(PriorityQueueTest_Student o1, PriorityQueueTest_Student o2) {
            return o1.id - o2.id;
        }
    }
}
