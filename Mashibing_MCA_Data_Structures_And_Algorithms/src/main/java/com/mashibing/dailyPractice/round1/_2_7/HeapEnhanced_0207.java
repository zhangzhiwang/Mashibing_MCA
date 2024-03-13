package com.mashibing.dailyPractice.round1._2_7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 加强堆：实现add、pop、resign、remove方法（本算法假设堆中没有重复的元素）
 */
public class HeapEnhanced_0207 {
    public ArrayList<Value_0207> arr = new ArrayList<>();
    public Map<Value_0207, Integer> map = new HashMap<>();// key：元素值，value：元素索引
    public Comparator<Value_0207> com;

    public HeapEnhanced_0207(Comparator<Value_0207> com) {// 根据传进来的比较器是正序排序还是倒序排序就决定了本堆是大根堆还是小根堆
        this.com = com;
    }

    class Value_0207 {
        public int value;

        public Value_0207(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Value_0207{" +
                    "value=" + value +
                    '}';
        }
    }

    public void add(int num) {
        Value_0207 value = new Value_0207(num);
        arr.add(value);
        map.put(value, arr.size() - 1);
        heapInsert(arr.size() - 1);
    }

    public int pop() {
        if (arr.size() == 0) {
            throw new RuntimeException("堆已空");
        }

        int result = arr.get(0).value;
        arr.set(0, arr.get(arr.size() - 1));
        arr.remove(arr.size() - 1);
        heapify(0);
        return result;
    }

    public void resign(Value_0207 value) {
        if (!map.containsKey(value)) {
            return;
        }

        int index = map.get(value);
        heapInsert(index);
        heapify(index);
    }

    public void remove(Value_0207 value) {
        if (!map.containsKey(value)) {
            return;
        }

        int N = arr.size();
        int index = map.get(value);
        arr.set(index, arr.get(N - 1));
        map.put(arr.get(N - 1), index);
        arr.remove(N - 1);
        map.remove(value);
        if(index == N - 1) {
            return;
        }

        heapInsert(index);
        heapify(index);
    }

    public void heapInsert(int index) {
        while (com.compare(arr.get(index), arr.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int index) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < arr.size()) {
            int largestOrSmallestIndex = leftIndex + 1 < arr.size()
                    && com.compare(arr.get(leftIndex + 1), arr.get(leftIndex)) < 0 ? leftIndex + 1 : leftIndex;
            largestOrSmallestIndex = com.compare(arr.get(largestOrSmallestIndex), arr.get(index)) < 0 ? largestOrSmallestIndex : index;
            if (largestOrSmallestIndex == index) {
                break;
            }

            swap(largestOrSmallestIndex, index);
            index = largestOrSmallestIndex;
            leftIndex = index * 2 + 1;
        }
    }

    private void swap(int i, int j) {
        Value_0207 valueI = arr.get(i);
        Value_0207 valueJ = arr.get(j);
        arr.set(j, valueI);
        arr.set(i, valueJ);

        map.put(valueI, j);
        map.put(valueJ, i);
    }

    public static void main(String[] args) {
        // 大根堆
        HeapEnhanced_0207 heap1 = new HeapEnhanced_0207(new Comparator<Value_0207>() {
            @Override
            public int compare(Value_0207 o1, Value_0207 o2) {
                return o2.value - o1.value;
            }
        });

        heap1.add(10);
        heap1.add(20);
        heap1.add(30);
        heap1.add(40);
        heap1.add(50);

//        while (heap1.arr.size() != 0) {
//            System.out.print(heap1.pop() + "\t");
//        }
//        System.out.println();
        System.out.println("arr:");
        System.out.println(heap1.arr);
        System.out.println("---------------");

        Value_0207 value_0207 = heap1.arr.get(0);
//        value_0207.value = 1;
//        heap1.resign(value_0207);
//        System.out.println("arr:");
//        System.out.println(heap1.arr);
//        while (heap1.arr.size() != 0) {
//            System.out.print(heap1.pop() + "\t");
//        }
//        System.out.println();
//        System.out.println("---------------");

        heap1.remove(value_0207);
        System.out.println("arr:");
        System.out.println(heap1.arr);
        while (heap1.arr.size() != 0) {
            System.out.print(heap1.pop() + "\t");
        }
        System.out.println();
        System.out.println("---------------");
    }
}
