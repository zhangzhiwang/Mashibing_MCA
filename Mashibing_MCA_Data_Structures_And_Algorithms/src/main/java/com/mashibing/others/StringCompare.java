package com.mashibing.others;

/**
 * 字符串比较
 * 字符串比较是按照字典序排序的，排序规则：
 * 1、如果比较的两个字符串等长，那么就根据单个字符从左往右依次比较，直到比较出大小之后返回，后面的字符就不需要比较了。
 * 比如："abc"与"def"比较大小，由于字符a比字符d要小，那么整体"abc"就小于"def"，后面你的字符不再参与比较。
 * 2、如果比较的两个字符串不等长，那么先将短的字符串的后面补上最小的asc码再按照上面的规则1进行比较。
 * 比如："abc"与"ab"比较大小，先将第二个字符串后面补上一个最小的asc码，然后再开始比较，由于前面的a和b都相等，c比最小的asc码要大，所以整体abc要大于ab。
 */
public class StringCompare {
    public static void main(String[] args) {
        System.out.println("abc".compareTo("def"));// 返回负数，说明第一个字符串小
        System.out.println("abc".compareTo("ab"));// 返回正数，说明第一个字符串大
        System.out.println("abc".compareTo("abc"));// 返回0，说明两个字符串相等
    }
}
