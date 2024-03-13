package com.mashibing.dailyPractice.round2._0304;

import java.util.HashSet;
import java.util.Set;

/**
 * 1、打印一个字符串的全部排列
 * 2、打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class PrintAllPermutations1_0304 {
    public static void printAllPermutations1(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        char[] charArray = str.toCharArray();
        recurse1(charArray, 0);
    }

    private static void recurse1(char[] charArray, int index) {
        if(index == charArray.length) {
            System.out.println(new String(charArray));
            return;
        }

        for(int i = index; i < charArray.length; i++) {
            swap(charArray, index, i);
            recurse1(charArray, index + 1);
            swap(charArray, index, i);
        }
    }

    public static void printAllPermutations2(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        char[] charArray = str.toCharArray();
        recurse2(charArray, 0, new HashSet<String>());
    }

    private static void recurse2(char[] charArray, int index, Set<String> set) {
        if(index == charArray.length) {
            String s = new String(charArray);
            if(!set.contains(s)) {
                System.out.println(s);
                set.add(s);
            }
            return;
        }

        for(int i = index; i < charArray.length; i++) {
            swap(charArray, index, i);
            recurse2(charArray, index + 1, set);
            swap(charArray, index, i);
        }
    }

    private static void swap(char[] charArray, int i, int j) {
        if(i == j) {
            return;
        }

        char tmp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = tmp;
    }

    public static void main(String[] args) {
        printAllPermutations1("acc");
        System.out.println("----------");
        printAllPermutations2("acc");
    }
}
