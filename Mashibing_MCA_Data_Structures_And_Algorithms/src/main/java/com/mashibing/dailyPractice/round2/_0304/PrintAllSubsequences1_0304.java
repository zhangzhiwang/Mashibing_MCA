package com.mashibing.dailyPractice.round2._0304;

import java.util.HashSet;
import java.util.Set;

/**
 * 1、打印一个字符串的全部子序列（子序列的字符顺序不能颠倒）
 * 2、打印一个字符串的全部子序列（子序列的字符顺序不能颠倒），要求打印的子序列不能重复
 */
public class PrintAllSubsequences1_0304 {
    /**
     * 1、打印一个字符串的全部子序列（子序列的字符顺序不能颠倒）
     * @param str
     */
    public static void printAllSubsequences1(String str) {
        if (str == null || str.length() == 0) {
            return;
        }

        char[] charArray = str.toCharArray();
        recurse1(charArray, "", 0);
    }

    private static void recurse1(char[] charArray, String path, int index) {
        if (index == charArray.length) {
            System.out.println(path);
            return;
        }

        recurse1(charArray, path, index + 1);
        recurse1(charArray, path + charArray[index], index + 1);
    }

    /**
     * 2、打印一个字符串的全部子序列（子序列的字符顺序不能颠倒），要求打印的子序列不能重复
     * @param str
     */
    public static void printAllSubsequences2(String str) {
        if (str == null || str.length() == 0) {
            return;
        }

        char[] charArray = str.toCharArray();
        recurse2(charArray, "", 0, new HashSet<>());
    }

    private static void recurse2(char[] charArray, String path, int index, Set<String> set) {
        if (index == charArray.length) {
            if(!set.contains(path)) {
                System.out.println(path);
                set.add(path);
            }
            return;
        }

        recurse2(charArray, path, index + 1, set);
        recurse2(charArray, path + charArray[index], index + 1, set);
    }

    public static void main(String[] args) {
//        printAllSubsequences1("acc");
//        System.out.println("------------");
        printAllSubsequences2("ccc");
    }
}
