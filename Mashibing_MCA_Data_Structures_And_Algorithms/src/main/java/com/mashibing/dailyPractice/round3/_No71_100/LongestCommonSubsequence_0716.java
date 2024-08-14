package com.mashibing.dailyPractice.round3._No71_100;

/**
 * 动态规划题目6——最长公共子序列：
 * 给定两个字符串str1和str2，返回这两个字符串的最长公共子序列长度。
 * 比如 ： str1 = “a12b3c456d”，str2 = “1ef23ghi4j56k”，最长公共子序列是“123456”，所以返回长度6。
 */
public class LongestCommonSubsequence_0716 {
    public int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || "".equals(text1) || text2 == null || "".equals(text2)) {
            return 0;
        }

        return recurse(text1.toCharArray(), text1.length() - 1, text2.toCharArray(), text2.length() - 1);
    }

    private static int recurse(char[] chars1, int i, char[] chars2, int j) {
        if (i == 0 && j == 0) {
            return chars1[i] == chars2[j] ? 1 : 0;
        } else if (i == 0) {
            if (chars1[i] == chars2[j]) {
                return 1;
            } else {
                return recurse(chars1, i, chars2, j - 1);
            }
        } else if (j == 0) {
            if (chars1[i] == chars2[j]) {
                return 1;
            } else {
                return recurse(chars1, i - 1, chars2, j);
            }
        } else {
            int p1 = recurse(chars1, i - 1, chars2, j);
            int p2 = recurse(chars1, i, chars2, j - 1);
            int p3 = chars1[i] == chars2[j] ? 1 + recurse(chars1, i - 1, chars2, j - 1) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || "".equals(text1) || text2 == null || "".equals(text2)) {
            return 0;
        }

        int N = text1.length();
        int M = text2.length();
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int[][] buf = new int[N][M];
        buf[0][0] = chars1[0] == chars2[0] ? 1 : 0;

        for (int i = 1; i < M; i++) {
            buf[0][i] = chars1[0] == chars2[i] ? 1 : buf[0][i - 1];
        }

        for (int i = 1; i < N; i++) {
            buf[i][0] = chars1[i] == chars2[0] ? 1 : buf[i - 1][0];
        }

        for (int j = 1; j < M; j++) {
            for (int i = 1; i < N; i++) {
                int p1 = buf[i - 1][j];
                int p2 = buf[i][j - 1];
                int p3 = chars1[i] == chars2[j] ? 1 + buf[i - 1][j - 1] : 0;
                buf[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return buf[N - 1][M - 1];
    }

    public static void main(String[] args) {
        // leetcode地址：https://leetcode.com/problems/longest-common-subsequence/
    }
}
