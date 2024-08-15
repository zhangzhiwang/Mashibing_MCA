package com.mashibing.dailyPractice.round5;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class PrintAllPermutations2_0815 {
    public static void printAllPermutations2(String s) {
        if(s == null || s.length() == 0) {
            return;
        }

        recurse(s.toCharArray(), 0, new HashSet<>());
    }

    public static void recurse(char[] str, int index, Set<String> set) {
        if(index == str.length) {
            String s = new String(str);
            if(set.contains(s)) {
                return;
            }
            System.out.println(s);
            set.add(s);
            return;
        }

        for (int j = index; j < str.length; j++) {
            swap(str, index, j);
            recurse(str, index + 1, set);
            swap(str, index, j);
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        String s = "acc";
        printAllPermutations2(s);
    }
}
