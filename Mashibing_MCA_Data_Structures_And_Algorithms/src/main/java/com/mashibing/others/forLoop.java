package com.mashibing.others;

/**
 * 对for循环的认识
 */
public class forLoop {
    public static void main(String[] args) {
        /**
         * for循环的条件（小括号）里面有三部分：
         * 1、初始值部分
         * 2、控制循环条件部分
         * 3、初始值的变化规律
         *
         * for循环的代码块（大括号）：用于写业务代码
         */
        // for(;;) {}

        // 1、循环条件的初始值部分：可以写在小括号里面，也可以写在小括号外面
//        for(int i = 0; i < 10; i++) {
//            System.out.print(i + "\t");
//        }

//        int i = 0;// 将初始值部分写在外面
//        for(; i < 10; i++) {
//            System.out.print(i + "\t");
//        }

        // 2、控制循环条件部分：为true就继续循环，为false就跳出循环
//        for(int i = 0; 100 == 100; i++) {// 死循环
//            System.out.println(i);
//        }

//        String str = "abc";
//        for(int i = 0; str.length() == 3; i++) {// 退出循环的条件和变量i没有关系
//            try {
//                System.out.println("hello");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            str = "ab";
//        }

//        boolean flag = true;
//        for(int i = 0; flag; i++) {// 退出循环的条件和变量i没有关系
//            System.out.println(i);
//            if(i == 100) {
//                flag = false;
//            }
//        }

        // 3、初始值的变化规律
        for(int i = 1; i <= 100;) {// 初始值的变化规律不在小括号里面写
            System.out.println(i);
            i *= 10;
        }

        // 至此，for循环小括号里面的三个部分都可以不在小括号里面写，都可以单独拿出来写：
//        String s = "a";
//        int i = 0;
//        for(;s.length() <= 5;) {
//            s += "" + i;
//            System.out.println(s);
//            i++;
//        }

        // 4、代码块用于写运算代码，如果不写运算代码就可以不写大括号
        // 找出数组arr中元素5的下标
        int[] arr = {1,2,3,4,5,6,7,8,9};
        int i = 0;
        for(; arr[i] != 5; i++);
        System.out.println("元素5所在的索引下标i是：" + i);
    }
}
