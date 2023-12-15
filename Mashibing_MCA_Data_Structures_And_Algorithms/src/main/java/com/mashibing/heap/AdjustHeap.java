package com.mashibing.heap;

/**
 * 题目：已知给定一个大根堆数组，但是其中的一个元素被换掉了，重新调整这个堆。
 * 思路：堆中的某个数被换掉了，假设该位置上的数值由A变成了B，A被换掉之前是一个完整且正确的大根堆，突然由A换成B，B比A大还是比A小不清楚，
 * 要想重新调整该堆就要先调一下heapInsert然后再调heapify，这两个过程都调用一遍之后，整个堆就调好了。
 * 这个节点由A换成B后无外乎就两种情况：要么不用动，动的话要么向上动要么向下动，所以heapInsert和heapify只能命中一个过程。
 * 课程：体系班课时52
 */
public class AdjustHeap {
    private int[] arr;// 大根堆
    private int headSize;

    public AdjustHeap(int arrSize) {
        this.arr = new int[arrSize];
    }

    /**
     * 随机替换一个元素为num
     */
    public void changeOneNum(int num) {
        if(arr.length == 0) {
            throw new RuntimeException("堆已空！");
        }

        int randomIndex = (int)(Math.random() * headSize);
        arr[randomIndex] = num;
        heapInsert(arr, randomIndex);
        heapify(arr, randomIndex);
    }

    public void add(int num) {
        if(headSize == arr.length) {
            throw new RuntimeException("堆已满！");
        }

        // 先把新加入的元素放到数组的最后一个位置
        arr[headSize] = num;
        heapInsert(arr, headSize++);
    }

    public int pop() {
        if(arr.length == 0) {
            throw new RuntimeException("堆已空！");
        }

        int result = arr[0];
        // 先把堆的头元素放到最末尾
        swap(arr, 0, --headSize);
        heapify(arr, 0);
        return result;
    }

    public int peek() {
        if(arr.length == 0) {
            throw new RuntimeException("堆已空！");
        }

        return arr[0];
    }

    public int size() {
        return headSize;
    }

    public boolean isEmpty() {
        return headSize == 0;
    }

    private void heapInsert(int[] arr, int index) {
        while(arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index) {
        int leftIndex = 2 * index + 1;
        while(leftIndex < headSize) {// 存在左孩子就进入循环
            // 左右孩子中较大的索引
            int largestValueIndex = leftIndex + 1 < headSize && arr[leftIndex] < arr[leftIndex + 1] ? leftIndex + 1 : leftIndex;
            // 上面左右孩子的大小比较出来了但是还没有跟父节点比较
            largestValueIndex = arr[index] > arr[largestValueIndex] ? index : largestValueIndex;
            if(index == largestValueIndex) {
                break;
            }

            swap(arr, index, largestValueIndex);
            index = largestValueIndex;
            leftIndex = 2 * index + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        AdjustHeap ah = new AdjustHeap(10);
        ah.add(1);
        ah.add(2);
        ah.add(3);
        ah.add(4);
        ah.add(5);
        ah.add(6);
        ah.add(7);
        ah.add(8);
        ah.add(9);
        ah.add(10);
        System.out.println(ah.peek());
        ah.changeOneNum(-1);
        System.out.println(ah.peek());
        System.out.println("-----------");

        while(!ah.isEmpty()) {
            System.out.print(ah.pop() + "\t");
        }
    }
}
