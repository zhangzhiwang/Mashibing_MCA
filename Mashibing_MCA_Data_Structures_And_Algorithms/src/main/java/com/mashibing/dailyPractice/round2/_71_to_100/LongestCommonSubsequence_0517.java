package com.mashibing.dailyPractice.round2._71_to_100;

/**
 * 动态规划题目6：
 * 给定两个字符串str1和str2，返回这两个字符串的最长公共子序列长度。
 * 比如 ：str1 = “a12b3c456d”，str2 = “1ef23ghi4j56k”，最长公共子序列是“123456”，所以返回长度6。
 */
public class LongestCommonSubsequence_0517 {
    public int longestCommonSubsequence1(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        return recurse1(text1.toCharArray(), text1.length() - 1, text2.toCharArray(), text2.length() - 1);
    }

    private int recurse1(char[] str1, int index1, char[] str2, int index2) {
        if(index1 == 0 && index2 == 0) {
            return str1[index1] == str2[index2] ? 1 : 0;
        } else if (index1 == 0) {
            if(str1[index1] == str2[index2]) {
                return 1;
            } else {
               return recurse1(str1, index1, str2, index2 - 1);
            }
        }  else if (index2 == 0) {
            if(str1[index1] == str2[index2]) {
                return 1;
            } else {
                return recurse1(str1, index1 - 1, str2, index2);
            }
        } else {
            int p1 = recurse1(str1, index1 - 1, str2, index2);
            int p2 = recurse1(str1, index1, str2, index2 - 1);
            int p3 = str1[index1] == str2[index2] ? 1 + recurse1(str1, index1 - 1, str2, index2 - 1) : 0;
            return Math.max(Math.max(p1, p2), p3);
        }
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();
        int[][] buf = new int[arr1.length][arr2.length];
        if(arr1[0] == arr2[0]) {
            buf[0][0] = 1;
        }

        for (int i = 1; i < arr2.length; i++) {
            if(arr1[0] == arr2[i]) {
                buf[0][i] = 1;
            } else {
                buf[0][i] = buf[0][i - 1];
            }
        }

        for (int i = 1; i < arr1.length; i++) {
            if(arr1[i] == arr2[0]) {
                buf[i][0] = 1;
            } else {
                buf[i][0] = buf[i - 1][0];
            }
        }

        for (int i = 1; i < arr1.length; i++) {
            for (int j = 1; j < arr2.length; j++) {
                int p1 = buf[i - 1][j];
                int p2 = buf[i][j - 1];
                int p3 = arr1[i] == arr2[j] ? 1 + buf[i - 1][j - 1] : 0;
                buf[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }

        return buf[arr1.length - 1][arr2.length - 1];
    }
}
