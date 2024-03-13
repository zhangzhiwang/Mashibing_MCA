package com.mashibing.dailyPractice.round3._No61_70;

/**
 * 打印一个字符串的全部排列
 */
public class PrintAllPermutations1_0313 {
    public static void printAllPermutations1(String str) {
        if (str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0);
    }

    private static void recurse(char[] charArr, int index) {
        if(index == charArr.length) {
            System.out.println(new String(charArr));
            return;
        }

        for (int i = index; i < charArr.length; i++) {
            swap(charArr, index, i);
            recurse(charArr, index + 1);
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
