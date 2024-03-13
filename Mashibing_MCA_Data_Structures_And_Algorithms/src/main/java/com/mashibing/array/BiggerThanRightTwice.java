package com.mashibing.array;

/**
 * 归并排序的另一个题目——BiggerThanRightTwice
 * 题目：对于一个数组的任意位置i上的数，找出它右边所有乘以2之后仍然小于它的数的个数，找出数组中所有这样的数的个数。
 * 解释：比如一个数组[9,1,4,5,0,2,1]，
 * 1、对于子一个元素9来说，后面哪些数乘以2之后还比9小呢？答案是：1、4、0、2、1，因为5乘以2之后大于9了，所以一共有5个；
 * 2、对于1来说，符合要求的数只有0一个，共1个；
 * 3、以此类推，对于4来说共2个，后面的2乘以2之后正好等于4，不行，必须要小于的，所以一共有2个；
 * 4、对于5来说共3个；
 * 5、对于0来说共0个；
 * 6、对于2来说共0个；
 * 7、对于1来说共0个，所以总共有11个。
 *
 * 思路：本质上还是归并排序，在归并的时候算出数量，和最小和问题与逆序对问题不一样的是，本题不是在归并的过程中计算数量，而是单独用一个循环来计算数量，然后再重新循环进行正常的merge，
 * 实际的时间复杂度是O(2N)，忽略常数项还是O(N)。
 *
 * 课程：体系班课时34-35
 */
public class BiggerThanRightTwice {
    public static void main(String[] args) {
//        int[] arr = {9, 1, 4, 5, 0, 2, 1};
//        int result = getBiggerThanRightTwiceCount(arr);
//        System.out.println("result = " + result);

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (getBiggerThanRightTwiceCount(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int getBiggerThanRightTwiceCount(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return f(arr, 0, arr.length - 1);
    }

    private static int f(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }

        int M = L + ((R - L) >> 1);
        int leftCount = f(arr, L, M);
        int rightCount = f(arr, M + 1, R);
        int mergeCount = merge(arr, L, M, R);
        return leftCount + rightCount + mergeCount;
    }

    private static int merge(int[] arr, int L, int M, int R) {
        // 在归并时进行两次循环，第一次循环单独计算数量，第二次循环才是传统意义上的merge
        int x = M + 1;// 在右组符合条件的数的范围是[M+1,x)，右边不能取到x
        int totalCount = 0;
        // 以下循环是用来单独计算数量的
        for (int i = L; i <= M; i++) {// 左组从左到右循环
            while (x <= R && arr[i] > arr[x] * 2) {// 右组从左到右循环
                x++;
            }
            totalCount += x - (M + 1);// 由于右边取不到x，所以算数量的时候不能+1，直接右边界-左边界即可
        }
        // 以上虽然是两层循环嵌套，但是时间复杂度是O(N)而不是O(N^2)，因为无论是外层循环还是内层循环都是从左往右走一遍，都不会反复地从头遍历

        // 下面的循环是用来专门merge的
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        int p1 = L;
        int p2 = M + 1;
        /*
        为什么在逆序对那道题里面可以在同一个while循环里面既计数又往help数组里面放，而本题则必须在外面单独计数？
        原因就在于逆序对那道题还有最小和这两个题计数的标准和往help数组里面放的标准是一样的，所以可以在一个while循环里面进行。
        本题中这两个标准是不一样的：往help数组里面放的标准是：如果正序遍历，那么谁小谁往help数组里面放，只要小就行不需要小于2倍；
        同理，如果逆序遍历，那么谁大谁往help数组里面放，只要打就行不需要大两倍，而计数的标准是必须要大两倍才可以，所以符合往help数组里面放的标准不一定符合计数的标准，
        所以不能在一个while循环里面完成。
         */
        while (p1 <= M && p2 <= R) {
            help[helpIndex++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[helpIndex++] = arr[p1++];
        }

        while (p2 <= R) {
            help[helpIndex++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return totalCount;
    }

    // 以下是对数器
    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
