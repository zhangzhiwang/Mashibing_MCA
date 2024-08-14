package com.mashibing.dailyPractice.round4;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class PrintAllPermutations2_0731 {
    public static void printAllPermutations2(String s) {
        if(s == null || s.length() == 0) {
            return;
        }

        recurse(s.toCharArray(), 0, new HashSet<String>());
    }

    private static void recurse(char[] chars, int i, Set<String> set) {
        if(i == chars.length) {
            String str = new String(chars);
            if(!set.contains(str)) {
                System.out.println(str);
                set.add(str);
            }
            return;
        }

        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            recurse(chars, i + 1, set);
            swap(chars, i, j);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    public static void main(String[] args) {
        String s = "acc";
        printAllPermutations2(s);
    }
}
