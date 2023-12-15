package com.mashibing.array.sort;

/**
 * 题目：给定一个无序数组，要求给它排好序，这个数组有一个特点：就是排好序之后，每一个元素跟它之前的位置相比移动的步数都不会超过K步，K的值题目会给出。
 * 课程：体系班课时55
 * 思路：一个数组被排好序后，来到0位置的元素在原数组中最远的距离是在0+k位置上，再远的元素就不会最多移动k步来到0位置了。所以就用原数组[0,k]范围的数构建一个小根堆，该小根堆有k+1个元素，
 * 堆顶元素一定是整个数组最小的元素，把它弹出来放到0位置，然后再把k+1位置的数放入到小根堆，再弹出堆顶元素放在1位置，再把k+2位置的元素加入到小根堆，以此类推，弹出一个加入一个，
 * 知道数组里面的所有元素都加进小根堆，然后小根堆弹完位置，整个数组就排好序了。
 */
public class MoveInKStepSort {
    public static void main(String[] args) {
        // 题目：给定一个数组[3,4,1,2,5]，排好序后每一个元素都不会移动超过2步，将该数组排好序。
        int[] arr = {3,4,1,2,5};// k = 2

    }

    public static void moveInKStepSort(int[] arr, int k) {
        if(arr == null || arr.length < 2) {
            return;
        }

        int heapSize = k + 1;// 将k+1个元素放入小根堆，即heapSize=k+1
        for(int i = k; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }

        while(heapSize != 0) {

        }
    }

    private static void add(int[] arr, int num, int heapSize) {
        // 先把新加入的元素放到数组的最后一个位置
        arr[heapSize] = num;
        heapInsert(arr, heapSize);
    }

    private static void heapInsert(int[] arr, int index) {
        while(arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static int pop(int[] arr, int heapSize) {
        int result = arr[0];
        // 先把堆的头元素放到最末尾
        swap(arr, 0, --heapSize);
        heapify(arr, 0, heapSize);
        return result;
    }

    private static void heapify(int[] arr, int index, int heapSize) {
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
            index = largestValueIndex;
            leftIndex = 2 * index + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
