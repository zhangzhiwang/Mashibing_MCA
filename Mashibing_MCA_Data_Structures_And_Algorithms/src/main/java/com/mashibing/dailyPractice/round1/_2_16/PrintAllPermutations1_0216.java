package com.mashibing.dailyPractice.round1._2_16;

/**
 * 打印一个字符串的全部排列
 */
public class PrintAllPermutations1_0216 {
    public static void printAllPermutations1(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0);
    }

    private static void recurse(char[] charArr, int index) {
        if(index == charArr.length) {
            System.out.println(new String(charArr));
            return;
        }

        for(int i = index; i < charArr.length; i++) {
            swap(charArr, i, index);
            recurse(charArr, index + 1);
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
        printAllPermutations1(str);
    }
}
