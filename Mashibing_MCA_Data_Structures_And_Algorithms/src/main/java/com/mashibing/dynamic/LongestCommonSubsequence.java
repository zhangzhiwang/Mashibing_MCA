package com.mashibing.dynamic;

/**
 * 动态规划题目6：
 * 给定两个字符串str1和str2，返回这两个字符串的最长公共子序列长度。
 * 比如 ： str1 = “a12b3c456d”，str2 = “1ef23ghi4j56k”，最长公共子序列是“123456”，所以返回长度6。
 *
 * 思路：
 * 这道题目是双样本对应模型。递归设计思路：考查样本1在0-i位置上和样本2在0-j位置上的最大公共子序列的长度，即str1[0...i]和str2[0...j]的最大公共子序列的长度，
 * 至于i以后和j以后的字符不考虑。
 * 双样本对应模型的最佳实践是考虑每一个样本最后一个位置会出现哪些情况即可，即i位置和j位置。按照这个思路，最长公共子序列的情况有以下三种：
 * 1、最长公共子序列一定是以i位置的字符结尾，但不一定以j位置的字符结尾，即一定要考虑i位置但不一定要考虑j位置
 * 2、最长公共子序列一定是以j位置的字符结尾，但不一定以i位置的字符结尾，即一定要考虑j位置但不一定要考虑i位置
 * 3、最长公共子序列一定是以i位置的字符结尾同时也已j位置的字符结尾，所以这种情况下i和j位置的字符是一样的，即str1[i]=str2[j]
 *
 * leetcode地址：https://leetcode.com/problems/longest-common-subsequence/
 *
 * 课程：体系班课时192-194
 */
public class LongestCommonSubsequence {
    // ---------------版本一：暴力递归版---------------
    public static int longestCommonSubsequence1(String str1, String str2) {
        if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }

        return recurse1(str1.toCharArray(), str1.length() - 1, str2.toCharArray(), str2.length() - 1);
    }

    /**
     *
     * @param str1Arr 字符串str1的字符序列
     * @param i 考查str1序列的0到i位置的字符
     * @param str2Arr 字符串str2的字符序列
     * @param j 考查str2序列的0到j位置的字符
     * @return
     */
    private static int recurse1(char[] str1Arr, int i, char[] str2Arr, int j) {
        if(i == 0 && j == 0) {// base case1：当这两个字符序列都只有一个字符，那么只判断这两个字符是不是相等即可，如果相等最长子序列的长度就是1
            return str1Arr[i] == str2Arr[j] ? 1 : 0;
        } else if(i == 0) {// base case2：当str1只有一个字符而str2不只一个字符时，从后面往前面依次考查str2的每一个字符
            if(str1Arr[i] == str2Arr[j]) {// 如果str2的j位置上的字符正好和str1的唯一一个字符相等，返回1即可，因为最大子序列的长度就是1
                return 1;
            } else {// 否则倒序考查str2的每一个字符是不是和str1的唯一的字符相等
                return recurse1(str1Arr, i, str2Arr, j - 1);
            }
        } else if(j == 0) {// base case3：上面是str1只有一个字符的情况，这里是str2只有一个字符的情况，考查过程和上面一样
            if(str1Arr[i] == str2Arr[j]) {
                return 1;
            } else {
                return recurse1(str1Arr, i - 1, str2Arr, j);
            }
        } else {// 普遍情况：str1和str2都不只有1个元素
            // 按照双样本对应模型的最佳实践，每个样本只考虑最后一个元素的情况即可
            /*
             情况1：公共子序列一定是以i位置的字符结束，不一定以j位置的字符结束
             1、公共子序列一定以i位置字符结尾，说明i位置不用考察了，从i-1位置往前考查即可
             2、不一定以j位置的字符结束，说明公共子序列可能包含j位置的字符，那么j位置的字符就需要考查
             */
            int p1 = recurse1(str1Arr, i - 1, str2Arr, j);
            // 情况2：公共子序列一定是以j位置的字符结束，不一定以i位置的字符结束，所以i位置必须考查，j位置不用考查，从j-1位置往前考查即可
            int p2 = recurse1(str1Arr, i, str2Arr, j - 1);
            // 情况3：公共子序列既以i位置的字符结尾又以j位置的字符结尾，说明i和j位置的字符相等，所以这两个位置都不用考查了，从i-1和j-1往前考查即可
            /*
             当str1Arr[i]!=str2Arr[j]时，p3=0，或者给p3赋值一个无意义的数也可以，比如一个负数，只要不影响选出最大值就行。
             为什么要这样，因为当str1Arr[i]!=str2Arr[j]时，p3不需要参与最大值的选拔，原因在老师代码的注释里写得很详细，位置：class19.Code04_LongestCommonSubsequence
             */
            int p3 = str1Arr[i] == str2Arr[j] ? 1 + recurse1(str1Arr, i - 1, str2Arr, j - 1) : 0;

            return Math.max(p1, Math.max(p2, p3));
        }
    }
    // -------------------------------------------
    // ---------------版本三：最终版---------------
    /*
    跳过版本二，直接版本三。
    分析：通过版本一的递归过程可以看到只有两个变量i和j影响整个递归过程，所以可以使用一个二维数组来表示：横坐标是i，纵坐标是j。
    设str1的长度是N，str2的长度是M，i的取值范围是[0,N-1]，j的取值范围是[0,M-1]，所以二维数组的总规模是N*M。
    这个二维矩阵怎么填？
    1、根据base case1可以确定[0][0]位置的值
    2、根据base case2可以确定第一列的值，第一列的每个格子的值要么取决于str1Arr[i]和str2Arr[j]是不是相等，要么取决于它下面的格子
    3、根据base case3可以确定第一行的值，第一行的每个格子的值要么取决于str1Arr[i]和str2Arr[j]是不是相等，要么取决于它左面的格子，
    至此，第一行和第一列都填满了
    4、最后看普适位置的格子怎么填，也就是上面p1、p2和p3的情况，可以看出任意一个普适格子[i][j]的值要么取决于它左面的格子，要么取决于它下面的格子，要么取决于它左下的格子
    5、分析普适格子的填入顺序。在填好第一行和第一列后，后面的顺序是从左往右，从下往上。
     */
    public static int longestCommonSubsequence2(String str1, String str2) {
        if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }

        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();
        int N = str1Arr.length;
        int M = str2Arr.length;
        int[][] buf = new int[N][M];
        // base case1
        buf[0][0] = str1Arr[0] == str2Arr[0] ? 1 : 0;
        // base case2：填入第一列的数据
        for(int j = 1; j < M; j++) {
            buf[0][j] = str1Arr[0] == str2Arr[j] ? 1 : buf[0][j - 1];
        }
        // base case3：填入第一行的数据
        for(int i = 1; i < N; i++) {
            buf[i][0] = str1Arr[i] == str2Arr[0] ? 1 : buf[i - 1][0];
        }
        // 普适位置
        for(int i = 1; i < N; i++) {
            for(int j = 1; j < M; j++) {
                int p1 = buf[i - 1][j];
                int p2 = buf[i][j - 1];
                int p3 = str1Arr[i] == str2Arr[j] ? 1 + buf[i - 1][j - 1] : 0;
                buf[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return buf[N - 1][M - 1];
    }
    // -------------------------------------------

    public static void main(String[] args) {
        // 没有写测试，直接贴到leetcode测试
    }
}
