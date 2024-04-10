package com.mashibing.dailyPractice.round1._91_to_100;

/**
 * 动态规划题目6：
 * 给定两个字符串str1和str2，返回这两个字符串的最长公共子序列长度。
 * 比如 ： str1 = “a12b3c456d”，str2 = “1ef23ghi4j56k”，最长公共子序列是“123456”，所以返回长度6。
 */
public class LongestCommonSubsequence_0329 {
    public int longestCommonSubsequence1(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        return recurse1(text1.toCharArray(), text1.length() - 1, text2.toCharArray(), text2.length() - 1);
    }

    private int recurse1(char[] s1, int i, char[] s2, int j) {
        if(i == 0 && j == 0) {
            return s1[i] == s2[j] ? 1 : 0;
        }

        if(i == 0) {
            if(s1[i] == s2[j]) {
                return 1;
            } else {
                return recurse1(s1, i, s2, j - 1);
            }
        } else if (j == 0) {
            if(s1[i] == s2[j]) {
                return 1;
            } else {
                return recurse1(s1, i - 1, s2, j);
            }
        } else {
            int p1 = recurse1(s1, i - 1, s2, j);
            int p2 = recurse1(s1, i, s2 , j - 1);
            int p3 = s1[i] == s2[j] ? recurse1(s1, i - 1, s2 , j - 1) + 1: 0;
            return Math.max(Math.max(p1, p2), p3);
        }
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int[][] arr = new int[text1.length()][text2.length()];
        arr[0][0] = s1[0] == s2[0] ? 1 : 0;

        for (int i = 1; i < arr.length; i++) {
            arr[i][0] = s1[i] == s2[0] ? 1 : arr[i - 1][0];
        }

        for (int i = 1; i < arr[0].length; i++) {
            arr[0][i] = s1[0] == s2[i] ? 1 : arr[0][i - 1];
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                int p1 = arr[i - 1][j];
                int p2 = arr[i][j - 1];
                int p3 = s1[i] == s2[j] ? arr[i - 1][j - 1] + 1 : 0;
                arr[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }

        return arr[arr.length - 1][arr[0].length - 1];
    }

    public static void main(String[] args) {
        // leetcode地址：https://leetcode.com/problems/longest-common-subsequence/

        System.out.println(new LongestCommonSubsequence_0329().longestCommonSubsequence1("ab", "abcbc"));
    }
}
