package com.mashibing.dailyPractice.round3._No61_70;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印一个字符串的全部子序列（子序列的字符顺序不能颠倒），要求打印的子序列不能重复
 */
public class PrintAllSubsequences2_0313 {
    public static void printAllSubsequences2(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0, "", new HashSet<String>());
    }

    private static void recurse(char[] charArr, int index, String path, Set<String> set) {
        if(index == charArr.length) {
            if(!set.contains(path)) {
                System.out.println(path);
                set.add(path);
            }
            return;
        }

        recurse(charArr, index + 1, path, set);
        recurse(charArr, index + 1, path + charArr[index], set);
    }

    public static void main(String[] args) {
        printAllSubsequences2("acc");
    }
}
