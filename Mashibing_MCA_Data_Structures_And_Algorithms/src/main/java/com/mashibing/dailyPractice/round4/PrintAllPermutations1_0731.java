package com.mashibing.dailyPractice.round4;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 */
public class PrintAllPermutations1_0731 {
    public static void printAllPermutations1(String s) {
        if(s == null || s.length() == 0) {
            return;
        }

        recurse(s.toCharArray(), 0);
    }

    private static void recurse(char[] chars, int i) {
        if(i == chars.length) {
            System.out.println(new String(chars));
            return;
        }

        recurse(chars, i + 1);
        for (int j = i + 1; j < chars.length; j++) {
            swap(chars, i, j);
            recurse(chars, i + 1);
            swap(chars, i, j);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        if (i == j) {
            return;
        }

        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    // 老师的代码
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<Character>();
        for (char cha : str) {
            rest.add(cha);
        }
        String path = "";
        f(rest, path, ans);
        return ans;
    }

    public static void f(ArrayList<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            int N = rest.size();
            for (int i = 0; i < N; i++) {
                char cur = rest.get(i);
                rest.remove(i);
                f(rest, path + cur, ans);
                rest.add(i, cur);
            }
        }
    }

    public static void main(String[] args) {
        String s = "acc";
        System.out.println("correct ans:");
        System.out.println(permutation1(s));
        System.out.println("my ans:");
        printAllPermutations1(s);
    }
}
