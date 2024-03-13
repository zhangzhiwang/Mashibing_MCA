package com.mashibing.dailyPractice.round1._2_16;

/**
 * 打印一个字符串的全部子序列（子序列的字符顺序不能颠倒）
 */
public class PrintAllSubsequences1_0216 {
    public static void printAllSubsequences1(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0, "");
    }

    private static void recurse(char[] charArr, int index, String path) {
        if(index == charArr.length) {
            System.out.println(path);
            return;
        }

        recurse(charArr, index + 1, path + charArr[index]);
        recurse(charArr, index + 1, path);
    }

    public static void main(String[] args) {
        String str = "abcc";
        printAllSubsequences1(str);
    }
}
