package com.mashibing.dailyPractice.round4;

/**
 * 动态规划题目6：
 * 给定两个字符串str1和str2，返回这两个字符串的最长公共子序列长度。
 * 比如 ： str1 = “a12b3c456d”，str2 = “1ef23ghi4j56k”，最长公共子序列是“123456”，所以返回长度6。
 */
public class LongestCommonSubsequence_0806 {
    public int longestCommonSubsequence1(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        return recurse(text1.toCharArray(), text1.length() - 1, text2.toCharArray(), text2.length() - 1);
    }

    private int recurse(char[] text1, int i, char[] text2, int j) {
        if(i == 0 && j == 0) {
            return text1[i] == text2[j] ? 1 : 0;
        } else if (i == 0) {
            if(text2[j] == text1[i]) {
                return 1;
            } else {
                return recurse(text1, i, text2, j - 1);
            }
        } else if (j == 0) {
            if(text2[j] == text1[i]) {
                return 1;
            } else {
                return recurse(text1, i - 1, text2, j);
            }
        } else {
            int p1 = recurse(text1, i - 1, text2, j);
            int p2 = recurse(text1, i, text2, j - 1);
            int p3 = text1[i] == text2[j] ? recurse(text1, i - 1, text2, j - 1) + 1 : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        int m = text1.length();
        int n = text2.length();
        char[] text1Chars = text1.toCharArray();
        char[] text2Chars = text2.toCharArray();
        int[][] buf = new int[m][n];
        buf[0][0] = text1Chars[0] == text2Chars[0] ? 1 : 0;

        for (int i = 1; i < n; i++) {
            buf[0][i] = text2Chars[i] == text1Chars[0] ? 1 : buf[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            buf[i][0] = text2Chars[0] == text1Chars[i] ? 1 : buf[i - 1][0];
        }

        for (int j = 1; j < n; j++) {
            for (int i = 1; i < m; i++) {
                int p1 = buf[i - 1][j];
                int p2 = buf[i][j - 1];
                int p3 = text2Chars[j] == text1Chars[i] ? buf[i - 1][j - 1] + 1 : 0;
                buf[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return buf[m - 1][n - 1];
    }

    public static void main(String[] args) {
        // leetcode地址：https://leetcode.com/problems/longest-common-subsequence/
    }
}
