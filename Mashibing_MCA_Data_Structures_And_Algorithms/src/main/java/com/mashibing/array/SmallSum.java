package com.mashibing.array;

/**
 * 最小和问题
 * 题目：给定一个数组，求该数组的最小和（假定给出的数组元素都是非负数）。
 * 解释：比如给定数组[1,3,5,2,4,6]，遍历每一个元素：
 * 1、把第一个元素1左侧所有比1小的元素全部加起来得到一个sum1，由于是第一个元素，所以它的左侧没有数也不可能存在比它小的，所以对于元素1来说最小和sum1=0；
 * 2、把第二个元素3左侧所有比3小的元素全部加起来得到一个sum2，所以sum2=1；
 * 3、把第三个元素5左侧所有比5小的元素全部加起来得到一个sum3，所以sum3=1 + 3；
 * 4、把第四个元素2左侧所有比2小的元素全部加起来得到一个sum4，所以sum4=1；
 * 5、把第五个元素4左侧所有比4小的元素全部加起来得到一个sum5，所以sum5=1 + 3 + 2；
 * 6、把第六个元素6左侧所有比6小的元素全部加起来得到一个sum6，所以sum6=1 + 3 + 5 + 2 + 4；
 * 那么最终整个数组的最小和就是每一个元素的最小和的和，即sumArr = sum1 + sum2 + …… + sum6，返回sumArr即可。
 *
 * 思路：
 * 如果按照上面题目解释的思路来做的话，那么会是for循环里面套for循环，时间复杂度为O(N^2)，明显不是最优解，最优解的时间复杂度为O(N*logN)。
 * 可以换一个思路：对于数组任意位置i上的一个元素来说，它右边有几个比它大的数，i元素就会在最终的sumArr中被加几次。比如上面的数组，第二个元素3，它右边有3个数比他大，
 * 那么在计算最终的sumArr时，3这个元素会被加3遍。所以，整个sumArr就可以看做：每一个元素在整个最小和计算中被加了几遍。
 * 实现思路：
 * 本质上就是一个归并排序，在归并排序的基础上进行累加和的计算。在对两个数组进行归并时，只有左数组的元素小时才进行累加和计算，要算出右数组有几个比左数组元素大的元素，累加进去；
 * 如果右数组的元素小，则正常归并不累加；当左右数组的元素相等时，将右数组的元素放入help数组。
 * 思路解释：
 * 1、归并的时候为什么左组小才累加？
 * 按照上面的思路，对于数组任意位置的一个元素x来说，它关心的是右边有多少个数比它大，最后的累加和x就会被加几次，它不关心左边有多少数比他大。
 * 所以在归并中如果右组p2位置的数比左组p1位置的数小，没有关系，因为p2位置的数不关心它左边有多少个数比它大，所以将p2位置的数放进help数组即可。
 * 如果有这样的疑问：那左右组排完序之后p2位置上的数不就跑到了p1位置上的数左边了吗，那不就存在一个p1位置的数比它大了吗？
 * 有这样的疑问就说明想偏了，题目没有让你给整个数组排序，上面的思路是：对于数组任意位置的一个元素x来说，它确实关心的是右边有多少个数比它大，但是是在原数组的顺序上进行比较的，
 * 不是按照排序后的数组顺序进行的。在归并排序的思路里，再往help数组里面放数之前，左右组都是按照数组的原序来分割的，所以再往help数组放之前，左组里面的任意数在原数组中肯定是在右组任何数的前面的
 * 所以右组p2位置的数小不能进行累加计算。换句话说，我们只是借助递归排序的思路来做这道题，但是题目没让你排序，你就不能给人家排序，再者说排完序后再求最小和还有什么意思吗？
 * 2、p1和p2位置的数相等为什么将p2的数放进help数组里？
 * 因为p2右边的数可能还和p1相等，必须让p2往后滑，滑到第一个比p1的数大为止，到这个时候才知道后有多少个数比p1数大，因为题目要求的是相等的数不计算在虽最小和内。
 *
 *
 * 课程：体系班课时31-33
 */
public class SmallSum {
    public static void main(String[] args) {
//        int[] arr = {1,3,5,2,4,6};
//        int sumAll = mergeSort(arr);
//        System.out.println("sumAll = " + sumAll);

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (mergeSort(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    public static int mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }

        return f(arr, 0, arr.length - 1);
    }

    private static int f(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }

        int M = L + ((R - L) >> 1);// 注意："(R - L) >> 1"要被小括号括起来再和L相加
        int leftSmallSum = f(arr, L, M);// 左组组内PK
        int rightSmallSum = f(arr, M + 1, R);// 右组组内PK
        int mergeSmallSum = merge(arr, L, M, R);// 左组和右组进行组间PK，PK完之后原来的左组和右组合并为一个组，然后共同作为更大一轮PK的左组

        return leftSmallSum + rightSmallSum + mergeSmallSum;
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        int p1 = L;
        int p2 = M + 1;
        int sumAll = 0;

        while (p1 <= M && p2 <= R) {
            /*
            这里要注意：在单纯的归并排序中，当arr[p1] == arr[p2]时，arr[p1]和arr[p2]谁放进help数组里面都可以，但是在求最小和的归并排序中必须放入右数组的元素，
            因为要知道有多少个右数组的元素比左数组当前元素大。
             */
            sumAll += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;// 注意：要先累加最小和再往help数组里面放，顺序不要搞反，因为下面p1和p2已经自增了
            help[helpIndex++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[helpIndex++] = arr[p1++];
        }

        while (p2 <= R) {
            help[helpIndex++] = arr[p2++];
        }

        // 这里不要忘了要将help数组覆盖进原数组的相应位置
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return sumAll;
    }

    // 以下是对数器
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

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
