package com.mashibing.array;

/**
 * 题目：给定一个无序数组arr，求累加和落在[lower,upper]范围内的子数组的个数。
 * 解释：
 * 一个具体的例子：给定一个数组[1,3,5,-2,6,-8,7]，求它哪些子数组的累加和落在[8,11]这个范围内。
 *
 * 思路：
 * 1、求原数组的前缀和数组sumArr：[1,4,9,7,13,5,12]，在前缀和数组中任一位置i的值表示原数组中从0到i位置的累加和，比如sumArr[3] = arr[0] + arr[1] + arr[2] + arr[3]；
 * 2、任意两个位置组成的子数组的累加和可以看做是sumArr在这两个位置的减法。比如位置3至位置5组成的原数组是[-2,6,-8]，它的累加和是-4，可以看做是sumArr[5]-sumArr[2]；
 * 3、原数组一共有7个元素，下标范围为0-6，可以把原数组的子数组分类为：以位置0结尾的子数组、以位置1结尾的子数组……以位置6结尾的子数组。
 * 现在用以位置5结尾的子数组举例，假设一个子数组的范围为[x,5]，x和5都代表原数组的下标位置，那么从位置x到位置5的累加和就等同于sumArr[5]-sumArr[x-1]，而sumArr[5]=13，
 * 那么要想让x到5的累加和落在[8,11]这个区间内，就必须让sumArr[x-1]的范围落在[13-11,13-8]即[2,5]这个范围内。
 * 4、使用归并排，在归并时统计数量和归并分开
 * 整体时间复杂度：O(N*logN)
 *
 * 课程：体系班课时36-40
 */
public class CountOfRangeSum {
    public static void main(String[] args) {
        int[] arr = {1, 4, 9, 7, 13};
        int count = getCountOfRangeSum(arr, 8, 11);
        System.out.println("count = " + count);
    }

    public static int getCountOfRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        // 求前缀和数组
        int[] sumArr = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            sumArr[i] = sum;
        }

        return f(sumArr, 0, arr.length - 1, lower, upper);
    }

    private static int f(int[] sumArr, int L, int R, int lower, int upper) {
        /*
         只要不涉及左边界跑到中间点的右边或者右边界跑到中间点的左边这种情况，L就不可能大于R。
         当然如果懒得区分，可以统一写成"L>=R"，如果没有L>R的场景时实际上判断的就是L==R，不过这样做不太好。
         */
        if (L == R) {
            if (sumArr[L] >= lower && sumArr[L] <= upper) {
                return 1;// 找到了一个
            } else {
                return 0;
            }
        }

        int M = L + ((R - L) >> 1);
        int leftCount = f(sumArr, L, M, lower, upper);
        int rightCount = f(sumArr, M + 1, R, lower, upper);
        int mergeCount = merge(sumArr, L, M, R, lower, upper);
        return leftCount + rightCount + mergeCount;
    }

    private static int merge(int[] sumArr, int L, int M, int R, int lower, int upper) {
        // 统计数量和merge分离
        // 统计数量
        int x = L;
        int y = L;// 左组符合条件的范围是[x,y)，不包括y
        int count = 0;
        /*
        注意：下面虽然是循环套循环，但是时间复杂度不是O(N^2)或O(N^3)而是O(N)，因为左组和右组都具有单调性，都是单调递增的。
        这里的N是本次循环整个范围的数据个数，即N=R - L + 1，外层的for循环是从左往右遍历一遍右组，而右组的数据量是N/2，
        里面的while循环是从左遍历两遍左组，而左组的数据量也是N/2，忽略掉常数项就是O(N)。
         */
        for (int i = M + 1; i <= R; i++) {
            int min = sumArr[i] - upper;
            int max = sumArr[i] - lower;
            /*
            从左到右遍历一遍左组，不走回头路，因为左右组都是单调递增的，而lower和upper又是题目给出来的，是个定值，所以算出来的min和max也是单调递增的。
            就是说每进行一次for循环，x都不会从头开始走，都是接着上一次x的位置继续往后走，所以整个for循环结束后，while循环会执行多次，但这多次while循环
            加一起一共遍历了一遍左组，所以时间复杂度是O(N)，下面y的while循环同理。
             */
            // 循环条件是sumArr[x]<=min就错了，因为x会把所有等于min的值全滑过去，所以等于min的情况就统计不到了
            while (x <= M && sumArr[x] < min) {// 左范围找到第一个大于等于min的数，所以x能取到
                x++;
            }
            // 当sumArr[y]=max时y要继续往后滑，因为等于max的数可能有多个，都得算进去，这就是为什么y取不到的原因
            while (y <= M && sumArr[y] <= max) {// 右范围找到第一个数大于max的数，所以y取不到，所以范围是[x,y)
                y++;
            }

            count += y - x;// 由于y是取不到的，所以不需要加1
        }

        // 下面就是正常的merge
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[helpIndex++] = sumArr[p1] < sumArr[p2] ? sumArr[p1++] : sumArr[p2++];
        }

        while (p1 <= M) {
            help[helpIndex++] = sumArr[p1++];
        }

        while (p2 <= R) {
            help[helpIndex++] = sumArr[p2++];
        }

        for (int i = 0; i < help.length; i++) {// 这里遍历的是help的数组，将help数组里面的数覆盖回原数组的相应位置
            sumArr[L + i] = help[i];
        }

        return count;
    }
}
