package com.mashibing.dailyPractice.round5;

/**
 * 动态规划题目6：
 * 给定两个字符串str1和str2，返回这两个字符串的最长公共子序列长度。
 * 比如 ： str1 = “a12b3c456d”，str2 = “1ef23ghi4j56k”，最长公共子序列是“123456”，所以返回长度6。
 */
public class LongestCommonSubsequence_0826 {
    public int longestCommonSubsequence1(String text1, String text2) {
        if((text1 == null && text2 == null) ||
                (text1 == null ^ text2 == null) ||
                (text1.length() == 0 && text2.length() == 0) ||
                (text1.length() == 0 ^ text2.length() == 0)) {
            return 0;
        }

        return recurse(text1.toCharArray(), text1.length() - 1, text2.toCharArray(), text2.length() - 1);
    }

    private int recurse(char[] str1, int i, char[] str2, int j) {
        if(i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            if(str1[i] == str2[j]) {
                return 1;
            } else {
                return recurse(str1, i, str2, j - 1);
            }
        } else if (j == 0) {
            if(str1[i] == str2[j]) {
                return 1;
            } else {
                return recurse(str1, i - 1, str2, j);
            }
        } else {
            int p1 = recurse(str1, i - 1, str2, j);
            int p2 = recurse(str1, i, str2, j - 1);
            int p3 = str1[i] == str2[j] ? 1 + recurse(str1, i - 1, str2, j - 1) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        if((text1 == null && text2 == null) ||
                (text1 == null ^ text2 == null) ||
                (text1.length() == 0 && text2.length() == 0) ||
                (text1.length() == 0 ^ text2.length() == 0)) {
            return 0;
        }

        char[] text1Chars = text1.toCharArray();
        char[] text2Chars = text2.toCharArray();
        int[][] buf = new int[text1.length()][text2.length()];
        buf[0][0] = text1Chars[0] == text2Chars[0] ? 1 : 0;

        for (int i = 1; i < text2.length(); i++) {
            buf[0][i] = text1Chars[0] == text2Chars[i] ? 1 : buf[0][i - 1];
        }

        for (int i = 1; i < text1.length(); i++) {
            buf[i][0] = text1Chars[i] == text2Chars[0] ? 1 : buf[i - 1][0];
        }

        for (int j = 1; j < text2.length(); j++) {
            for (int i = 1; i < text1.length(); i++) {
                int p1 = buf[i - 1][j];
                int p2 = buf[i][j - 1];
                int p3 = text1Chars[i] == text2Chars[j] ? 1 + buf[i - 1][j - 1] : 0;
                buf[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return buf[text1.length() - 1][text2.length() - 1];
    }

    public static void main(String[] args) {
        // leetcode地址：https://leetcode.com/problems/longest-common-subsequence/
    }
}
