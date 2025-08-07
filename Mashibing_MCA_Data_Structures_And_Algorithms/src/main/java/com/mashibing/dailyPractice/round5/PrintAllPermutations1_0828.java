package com.mashibing.dailyPractice.round5;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 */
public class PrintAllPermutations1_0828 {
    public static void printAllPermutations1(String s) {
        if(s == null || s.length() == 0) {
            return;
        }

        recurse(s.toCharArray(), 0);
    }

    private static void recurse(char[] str, int i) {
        if(i == str.length) {
            System.out.println(new String(str));
            return;
        }

        for (int j = i; j < str.length; j++) {
            swap(str, i, j);
            recurse(str, i + 1);
            swap(str, i, j);
        }
    }

    private static void swap(char[] str, int i, int j) {
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
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
        String s = "abc";
        System.out.println("correct ans:");
        System.out.println(permutation1(s));
        System.out.println("my ans:");
        printAllPermutations1(s);
    }
}
