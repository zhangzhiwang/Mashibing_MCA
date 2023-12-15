package com.mashibing.heap;

/**
 * 用数组做一个大根堆
 * 课程：体系班课时48-51
 * 思路：见com.mashibing.preInEclipse.senior.heap.HeapByArray注释
 */
public class BigHeapByArray {
    private int[] arr;// 大根堆
    private int heapSize;

    public BigHeapByArray(int arrSize) {
        this.arr = new int[arrSize];
    }

    public void add(int num) {
        if(heapSize == arr.length) {
            throw new RuntimeException("堆已满！");
        }

        // 先把新加入的元素放到数组的最后一个位置
        arr[heapSize] = num;
        heapInsert(arr, heapSize++);
    }

    public int pop() {
       if(arr.length == 0) {
           throw new RuntimeException("堆已空！");
       }

       int result = arr[0];
       // 先把堆的头元素放到最末尾
       swap(arr, 0, --heapSize);
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
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    private void heapInsert(int[] arr, int index) {
        while(arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index) {
        int leftIndex = 2 * index + 1;
        while(leftIndex < heapSize) {// 存在左孩子就进入循环
            // 左右孩子中较大的索引
            int largestValueIndex = leftIndex + 1 < heapSize && arr[leftIndex] < arr[leftIndex + 1] ? leftIndex + 1 : leftIndex;
            // 上面左右孩子的大小比较出来了但是还没有跟父节点比较
            largestValueIndex = arr[index] > arr[largestValueIndex] ? index : largestValueIndex;
            if(index == largestValueIndex) {
                break;
            }

            swap(arr, index, largestValueIndex);
            index = largestValueIndex;// 这句不能少
            leftIndex = 2 * index + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        BigHeapByArray hba = new BigHeapByArray(10);
        hba.add(5);
        hba.add(20);
        hba.add(10);
        System.out.println(hba.peek());
        System.out.println("size = " + hba.size());
        System.out.println(hba.isEmpty());
        System.out.println("-----------");

        System.out.println(hba.pop());
        System.out.println(hba.pop());
        System.out.println(hba.pop());
        System.out.println("size = " + hba.size());
        System.out.println(hba.isEmpty());
        System.out.println("-----------");
    }
}
