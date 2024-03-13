package com.mashibing.dailyPractice.round1._2_16;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class PrintAllPermutations2_0216 {
    public static void printAllPermutations2(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0, new HashSet<String>());
    }

    private static void recurse(char[] charArr, int index, Set<String> set) {
        if(index == charArr.length) {
            String printStr = new String(charArr);
            if(!set.contains(printStr)) {
                System.out.println(printStr);
                set.add(printStr);
            }
            return;
        }

        for(int i = index; i < charArr.length; i++) {
            swap(charArr, i, index);
            recurse(charArr, index + 1, set);
            swap(charArr, i, index);
        }
    }

    private static void swap(char[] charArr, int i , int j) {
        char tmp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = tmp;
    }

    public static void main(String[] args) {
        String str = "acc";
        printAllPermutations2(str);
    }
}
