package com.mashibing.dynamic;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印一个字符串的全部子序列（子序列的字符顺序不能颠倒），要求打印的子序列不能重复
 * 思路：这个其实就是com.mashibing.dynamic.PrintAllSubsequences1的去重版，在PrintAllSubsequences1的基础上传入一个Set，用于保存已经生成的子序列，
 * 当新生成的子序列已经在里面时，跳过打印。
 *
 * 课程：体系班课时161
 */
public class PrintAllSubsequences2 {
    public static void findAllSubsequences(String str) {
        if(str == null || str.length() == 0) {
            return;
        }

        recurse(str.toCharArray(), 0, "", new HashSet<String>());
    }

    /**
     *
     * @param charArr
     * @param index
     * @param path
     * @param subSet 将所有生成的子序列都放入到subSet中
     */
    private static void recurse(char[] charArr, int index, String path, Set<String> subSet) {
        if(index == charArr.length) {
            if(!subSet.contains(path)) {
                System.out.println(path);
                subSet.add(path);
            }
            return;
        }

        recurse(charArr, index + 1, path, subSet);
        recurse(charArr, index + 1, path + charArr[index], subSet);
    }

    public static void main(String[] args) {
        String str = "acc";
        findAllSubsequences(str);
    }
}
