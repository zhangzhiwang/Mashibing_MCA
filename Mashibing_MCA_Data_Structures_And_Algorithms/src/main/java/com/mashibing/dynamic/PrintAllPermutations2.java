package com.mashibing.dynamic;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 * 解释：就是1的去重版
 * 思路：在1的基础上加上去重。
 *
 * 课程：体系班课时164
 */
public class PrintAllPermutations2 {
    /**
     * 去重实现方式1
     * @param str
     * @return
     */
    public static Set<String> printAllPermutations1(String str) {
        HashSet<String> resultSet = new HashSet<>();
        if(str == null || str.length() == 0) {
            return resultSet;
        }

        recurse1(str.toCharArray(), 0, resultSet);
        return resultSet;
    }

    /**
     * 去重实现方式2
     * @param str
     * @return
     */
    public static Set<String> printAllPermutations2(String str) {
        HashSet<String> resultSet = new HashSet<>();
        if(str == null || str.length() == 0) {
            return resultSet;
        }

        recurse2(str.toCharArray(), 0);
        return resultSet;
    }

    /**
     * 去重实现方式1——"救火"
     * 不由分说，将所有结果全部放到Set里，让Set去去重，类似于"救火"
     *
     * @param charArr
     * @param index
     */
    public static Set<String> recurse1(char[] charArr, int index, Set<String> visitedSet) {
        if(index == charArr.length) {
            visitedSet.add(new String(charArr));// 生成的所有子序列全部加到visitedSet里，让Set去去重
            return visitedSet;
        }

        for(int i = index; i < charArr.length; i++) {
            swap(charArr, i, index);
            recurse1(charArr, index + 1, visitedSet);
            swap(charArr, i, index);// 恢复现场，当时哪两个字符调换了顺序再给换回去
        }

        return visitedSet;
    }

    /**
     * 去重实现方式2——"防火"
     * "防火"即"防患于未然"，就是重复的话就不生成，而不是像"救火方案"那样生成完之后再去重。
     * "防火"的这个方案专业叫"剪支"，减去分支的意思，如果某个分支会出现问题就不走这个分支
     *
     * @param charArr
     * @param index
     */
    public static void recurse2(char[] charArr, int index) {
        if(index == charArr.length) {
            System.out.println(new String(charArr));
            return;
        }

        boolean[] visited = new boolean[256];// 字符ascii码的范围是0-255，所以生成一个256长度的数组
        for(int i = index; i < charArr.length; i++) {// 子字符串的第一个字符是从i位置开始，向后依次尝试每一个字符去当该子字符串的开头
            /*
            根据ascii码表，任意一个字符都对应一个0-255的数字，所以可以将字符转换为visited的下标，具体可以了解下ascii码。
            如果某个字符被访问过，也就是说该字符已经当过了子字符串的开头了，那么就忽略
             */
            if(visited[charArr[i]]) {
                continue;
            }

            visited[charArr[i]] = true;// 标记charArr[i]已经当过开头了
            swap(charArr, i, index);
            recurse2(charArr, index + 1);
            swap(charArr, i, index);// 恢复现场，当时哪两个字符调换了顺序再给换回去
        }
    }

    private static void swap(char[] charArr, int i, int j) {
        char tmp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = tmp;
    }

    public static void main(String[] args) {
//        Set<String> set = printAllPermutations1("acc");
//        System.out.println(set);

        printAllPermutations2("acc");
    }
}
