package com.mashibing.dailyPractice.round3._No61_70;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class PrintAllPermutations2_0313 {
    public static void printAllPermutations1(String str) {
        if (str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0, new HashSet<String>());
    }

    private static void recurse(char[] charArr, int index, Set<String> set) {
        if(index == charArr.length) {
            String s = new String(charArr);
            if(!set.contains(s)) {
                System.out.println(s);
                set.add(s);
            }
            return;
        }

        for (int i = index; i < charArr.length; i++) {
            swap(charArr, index, i);
            recurse(charArr, index + 1, set);
            swap(charArr, index, i);
        }
    }

    private static void swap(char[] charArr, int i, int j) {
        if(i == j) {
            return;
        }

        char tmp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = tmp;
    }

    public static void main(String[] args) {
        printAllPermutations1("acc");
    }
}
