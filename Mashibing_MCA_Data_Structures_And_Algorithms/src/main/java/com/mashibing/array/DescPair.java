package com.mashibing.array;

/**
 * 逆序对
 * 题目：给定一个数组，找出逆序对的个数。
 * 解释：逆序对就是两两一组，前一个数比后一个数大，对于数组任意一个元素x，找它的逆序对是以x为起点往后找，不能往前找，往前找组成的逆序对不算。
 * 比如数组[3,1,2,5,0]：
 * 1、以3开头的逆序对有：[3,1]、[3,2]、[3,0]，共3个；
 * 2、以1开头的逆序对有：[1,0]，共1个；
 * 3、以1开头的逆序对有：[2,0]，共1个；
 * 4、以5开头的逆序对有：[5,0]，共1个；
 * 5、以0开头的没有逆序对，共0个，所以一共有逆序对：3 + 1 + 1 + 1 + 0 = 6个
 *
 * 思路：
 * 逆序对的思路本质上也是归并排序，它和最小和问题在归并时的思路是相反的：最小和问题找的是一个元素右边有多少个比它大的，逆序对问题找的是一个元素的右边有多少个比它小的。
 * 逆序对在归并时，两个数组要从右往左比较，将相等的和较大的数拷贝进help数组，而在典型的归并排序和最小和问题中都是从左往右比较两个数组以及将小数放进help数组，所以过程是相反的。
 * 通过归并排序、最小和、逆序对这三道题来看，当p1位置的数和p2位置的数的大小能分胜负时或者两者相等时，究竟是将p1数拷贝到help数组里还是将p2数拷贝到help数组里，
 * 其实本质上就一句话：比较后你想让p1指针往后移动还是想让p2指针往后移动，如果是想让p1指针往后移动，那么无论p1位置的数是大是小还是相等都应该把p1数拷贝到help数组里，p2数同理。
 * 那怎么知道到底将哪个数拷贝到help数组里呢？那就看你比较p1数和p2数的目的是什么，为什么要对这两个数作比较？你想干什么？弄清楚之后你就知道要把谁拷贝到help数组里了。
 * 分开来说：
 * 1、对于归并排序，你的目的只是从小到大排序，没有第二个目的了，那么在比较出来p1数和p2数谁大谁小之后，该拷贝谁呢？首先明确目的：你的目的就是为了从小到大排序，
 * 而且左数组和右数组在各自的内部已经是有序的了，现在要做的是让这两个组合并后有序，如果比较的结果是p1数<p2数，因为格子的组内已经是有序的了，既然p1数比p2数小，
 * 那么p2数后面的就不用考查了，因为后面的比p2数还大，所以我要完后移动p1，看看p1的下一个数是不是比p2小，所以要把p1拷贝到help数组里。
 * 如果p1数=p2数，具体拷贝谁还是得看你的目的，如果你的目的只是为了排序那么拷贝谁都可以，如果你的目的是除了排序还要保证排序的稳定性，那么必须拷贝p1数，如果拷贝p2数就不稳定了。
 * 2、对于最小和，你的目的之一是排序，还有一个目的是看看右组有多少个数比p1大，所以肯定也是谁小拷贝谁，相等时拷贝p2数，如果你拷贝p1数就不知道右组有多少个数比p1大了。
 * 3、对于逆序对，你的目的之一是排序，还有一个目的是看看右组有多少个数比p1小，那么当p1数<p2数时拷贝谁呢？首先左组和右组在给自的组内已经是有序的了，你还要看右组有多少个数比p1小，
 * 所以我要移动p2指针，让p2指针往左滑，一致滑到第一个比p1数小的位置，这样就可以算出右组有多少个数比p1位置小了，所以当p1数<p2数时当然要将较大的p2数拷贝到help数组里，
 * 而且遍历的顺序是倒序遍历。那么当p1数=p2数时拷贝谁呢？看是看目的，你的目的是让p2指针往左滑一直滑到第一个比p1数小的位置，所以当然相等时要拷贝p2数了。
 *
 * 课程：体系班课时33
 */
public class DescPair {
    public static void main(String[] args) {
        int[] arr = {3,1,2,5,0};
        int result = getDescPairNum(arr);
        System.out.println("result = " + result);

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (getDescPairNum(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int getDescPairNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return f(arr, 0, arr.length - 1);
    }

    public static int f(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }

        int M = L + ((R - L) >> 1);
        int leftDescPairNum = f(arr, L, M);
        int rightDescPairNum = f(arr, M + 1, R);
        int descPairNum = merge(arr, L, M, R);
        return leftDescPairNum + rightDescPairNum + descPairNum;
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int helpIndex = help.length - 1;
        int p1 = M;// 由于要由右往左比对，所以两个指针的初始位置都是右边界
        int p2 = R;
        int num = 0;

        while (p1 >= L && p2 >= M + 1) {// 注意：倒叙遍历左右两个数组，遍历完成后p1回到左组的左边界L，而不是到0，p2同理
            num += arr[p1] <= arr[p2] ? 0 : (p2 - (M + 1) + 1);
            help[helpIndex--] = arr[p1] <= arr[p2] ? arr[p2--] : arr[p1--];
        }

        while (p1 >= L) {
            help[helpIndex--] = arr[p1--];
        }

        while (p2 >= M + 1) {
            help[helpIndex--] = arr[p2--];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return num;
    }

    // 以下为对数器
    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
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
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
