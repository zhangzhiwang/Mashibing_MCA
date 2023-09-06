package com.mashibing.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 比较器
 */
public class ComparatorTest {
    public static void main(String[] args) {
        int[] arr = {1,3,5,7,2,4,6,8};
        Arrays.sort(arr);
        DuiShuQiUtil.printArr(arr);
        System.out.println("----------------");

        Student[] stuArr = new Student[5];
        Student s1 = new Student(3, "a", 10);
        Student s2 = new Student(7, "b", 30);
        Student s3 = new Student(5, "c", 20);
        Student s4 = new Student(1, "d", 19);
        Student s5 = new Student(9, "e", 15);
        stuArr[0] = s1;
        stuArr[1] = s2;
        stuArr[2] = s3;
        stuArr[3] = s4;
        stuArr[4] = s5;

        Arrays.sort(stuArr, new MyComparator3());
        printStudent(stuArr);
        System.out.println("----------------");

        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.sort(new MyComparator3());
        System.out.println(list);
    }

    static class Student {
        private int id;
        private String name;
        private int age;

        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void printStudent(Student[] stuArr) {
        for(Student s : stuArr) {
            System.out.println(s);
        }
    }

    /**
     * 按学生id正序排
     */
    static class MyComparator1 implements Comparator<Student> {
        /**
         * 所有的compare方法都遵循这样一个规律:
         * 1、返回负数，第一个参数排在第二个参数前面
         * 2、返回正数，第二个参数排在第一个参数前面
         * 3、返回0，谁排在前面无所谓
         * @param s1 the first object to be compared.
         * @param s2 the second object to be compared.
         * @return
         */
        @Override
        public int compare(Student s1, Student s2) {
            // 按学生id正序排，谁id小谁排在前面
            if(s1.id < s2.id) {
                return -1;
            } else if(s1.id > s2.id) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 按学生id倒序排
     */
    static class MyComparator2 implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            if(o1.id > o2.id) {
                return -1;
            } else if(o1.id < o2.id) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 按学生年龄正序排
     */
    static class MyComparator3 implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            if(o1.age < o2.age) {
                return -1;
            } else if (o1.age > o2.age) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
