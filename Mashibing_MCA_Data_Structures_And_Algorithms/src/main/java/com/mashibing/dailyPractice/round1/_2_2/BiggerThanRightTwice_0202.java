package com.mashibing.dailyPractice.round1._2_2;

/**
 * 对于一个数组的任意位置i上的数，找出它右边所有乘以2之后仍然小于它的数的个数，找出数组中所有这样的数的个数。
 */
public class BiggerThanRightTwice_0202 {
    public static int biggerThanRightTwice(int[] arr) {
        if(arr == null || arr.length < 2) {
            return 0;
        }

        return recurse(arr, 0, arr.length - 1);
    }

    private static int recurse(int[] arr, int L ,int R) {
        if(L == R) {
            return 0;
        }

        int M = L + ((R - L) >> 1);
        int leftCount = recurse(arr, L, M);
        int rightCount = recurse(arr, M + 1, R);
        int mergeCount = merge(arr, L, M, R);
        return leftCount + rightCount + mergeCount;
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int p1 = M;
        int p2 = R;
        int helpIndex = help.length - 1;
        int count = 0;

        // 在merge前先计数
        int winR = M + 1;// winR是右指针到不了的位置，是开区间，当winR指针滑到某一个位置时，有效位的区间是[M + 1,winR-1]
        for(int i = L; i <= M; i++) {
            while(winR <= R && arr[i] > arr[winR] * 2) {
                winR++;
            }
            count += winR - M - 1;
        }

        // 传统的merge过程
        while(p1 >= L && p2 >= M + 1) {
            /*
            本题不能在merge的过程中计数，因为计数的标准和往help数组里放的标准不一样：计数的标准是当p1数比p2数的2倍还大的时候才计数，而我那个help数组里面放的
            标准是只要p1数大于p2数就把p1数放进去了，p1的指针就往前移了，但是单招计数的标准此时不能移动p1指针，只有p1数大于p2数2倍的时候才能移动p1指针。
            所以标准不一样按照最小和去写本题代码就是错误的。
             */
//            count += arr[p1] > arr[p2] * 2 ? p2 - M : 0;
            help[helpIndex--] = arr[p1] <= arr[p2] ? arr[p2--] : arr[p1--];
        }

        while(p1 >= L) {
            help[helpIndex--] = arr[p1--];
        }

        while(p2 >= M + 1) {
            help[helpIndex--] = arr[p2--];
        }

        for(int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return count;
    }

    // 对数器
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

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (biggerThanRightTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
