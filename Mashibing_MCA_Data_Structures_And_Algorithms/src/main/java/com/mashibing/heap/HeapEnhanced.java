package com.mashibing.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 加强堆
 * 课程：体系班课时60-61
 * 思路：见com.mashibing.preInEclipse.senior.heap.HeapEnhanced注释
 */
public class HeapEnhanced<T> {
    private ArrayList<T> arr = new ArrayList<>();
    private Map<T, Integer> indexMap = new HashMap<>();
    private int heapSize;
    private Comparator<T> comparator;

    public Map<T, Integer> getIndexMap() {
        return indexMap;
    }
    public HeapEnhanced(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public T get(int index) {
        return arr.get(index);
    }

    public void add(T t) {
        arr.add(t);// 先把新元素添加到数组的最后一个位置
        indexMap.put(t, heapSize);// 暂且认为新加入的元素在堆尾位置，后面会调
        heapInsert(arr, heapSize++);
    }

    public T pop() {
        if (arr.size() == 0) {
            throw new RuntimeException("加强堆已空！");
        }

        T result = arr.get(0);
        arr.set(0, arr.get(heapSize - 1));
        indexMap.remove(result);
        arr.remove(--heapSize);
        heapify(arr, 0);
        return result;
    }

    private void remove(T t) {
        if (!indexMap.containsKey(t)) {
            return;
        }

        int index = indexMap.get(t);// 被删除的元素在哪
        arr.set(index, arr.get(heapSize - 1));
        indexMap.remove(t);
        arr.remove(--heapSize);
        heapify(arr, index);
    }

    public void adjust(T t) {
        // 先确定变化的元素在哪个位置，然后顺序调用heapInsert和heapify
        int index = indexMap.get(t);
        heapInsert(arr, index);
        heapify(arr, index);
    }

    private void heapInsert(ArrayList<T> arr, int index) {
        // heapInsert的本质就是将被考察元素和它的父节点做大小比较，从而而决定要不要跟父节点交换位置
        while (comparator.compare(arr.get(index), arr.get((index - 1) / 2)) < 0) {// 无论是大根堆还是小根堆，只要和父节点的不相等就交换
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(ArrayList<T> arr, int index) {
        int leftIndex = 2 * index + 1;
        while (leftIndex < heapSize) {
            int i = leftIndex + 1 < heapSize && comparator.compare(arr.get(leftIndex), arr.get(leftIndex + 1)) > 0 ? leftIndex + 1 : leftIndex;
            i = comparator.compare(arr.get(i), arr.get(index)) > 0 ? index : i;
            if (i == index) {
                return;
            }

            swap(arr, index, i);
            index = leftIndex;
            leftIndex = 2 * index + 1;
        }
    }

    private void swap(ArrayList<T> arr, int i, int j) {
        T o1 = arr.get(i);
        T o2 = arr.get(j);
        arr.set(i, o2);
        arr.set(j, o1);

        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }

    public static void main(String[] args) {
        HeapEnhanced<PriorityQueueTest.PriorityQueueTest_Student> heapEnhanced = new HeapEnhanced<>(new PriorityQueueTest.AgeDesc());
        heapEnhanced.add(new PriorityQueueTest.PriorityQueueTest_Student(100, 10, "a"));
        heapEnhanced.add(new PriorityQueueTest.PriorityQueueTest_Student(200, 20, "b"));
        heapEnhanced.add(new PriorityQueueTest.PriorityQueueTest_Student(300, 30, "c"));
        heapEnhanced.add(new PriorityQueueTest.PriorityQueueTest_Student(400, 40, "d"));
        heapEnhanced.add(new PriorityQueueTest.PriorityQueueTest_Student(500, 50, "e"));
        System.out.println("size = " + heapEnhanced.size());
        System.out.println(heapEnhanced.isEmpty());
        System.out.println(heapEnhanced.getIndexMap());
        System.out.println("----------------");

//        while(!heapEnhanced.isEmpty()) {
//            System.out.println(heapEnhanced.pop());
//        }
//        System.out.println("size = " + heapEnhanced.size());
//        System.out.println(heapEnhanced.isEmpty());
//        System.out.println("----------------");

//        PriorityQueueTest.PriorityQueueTest_Student student = heapEnhanced.get(3);
//        System.out.println("改变前student = " + student);
//        student.age = 1;
//        System.out.println("改变后student = " + student);
//        heapEnhanced.adjust(student);
//        while(!heapEnhanced.isEmpty()) {
//            System.out.println(heapEnhanced.pop());
//        }
//        System.out.println("----------------");

//        heapEnhanced.remove(student);
//        while (!heapEnhanced.isEmpty()) {
//            System.out.println(heapEnhanced.pop());
//        }
    }
}
