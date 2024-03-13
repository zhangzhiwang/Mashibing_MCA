package com.mashibing.dailyPractice.round3._No61_70;

/**
 * 打印一个字符串的全部子序列（子序列的字符顺序不能颠倒）
 */
public class PrintAllSubsequences1_0313 {
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

        recurse(charArr, index + 1, path);
        recurse(charArr, index + 1, path + charArr[index]);
    }

    public static void main(String[] args) {
        printAllSubsequences1("acc");
    }
}
