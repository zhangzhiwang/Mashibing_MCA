package com.mashibing.unionFind;

import java.util.ArrayList;

/**
 * 并查集题目3——岛问题变种，动态岛问题
 * 题目：
 * 给你定两个正整数m和n，代表矩阵的长和宽，即横纵坐标的长度，矩阵里面的值初始化都是0，然后给定一个二维数组positions，里面的每一个元素都是一个节点的坐标，
 * 意思是将该坐标上的值改为1，每给出一个坐标就返回到的个数，返回值是一个数组，里面是每次给出坐标后岛的个数。
 * 比如:m=10，n=10，先初始化一个有100个元素的二维数组，每一个元素值都为0，然后给出positions=[[5,7],[5,8],[6,7]]，意为将(5,7)这个坐标的值改为1，改为1后找出岛的个数c1，
 * 然后将坐标为(5,8)上的位置改为1，返回岛的个数c2，再将(6,7)上的位置改为1，返回岛的个数c3，最终返回[c1,c2,c3]。
 * 思路：
 * 这道题和之前的岛问题不一样的地方是：之前的岛问题是一开始就初始化好二维数组，哪些位置为1已经已知了，而这道题是动态地一个一个指定哪个位置是1，每给出一个1的位置就要返回岛的数量。
 * 实现方式有两种：
 * 方式1：就是按照题目说的，先初始化一个m*n的二维数组，然后再根据positions指定1的位置去找岛的个数。
 * 方式2：方式1的弊端就是当m和n非常大，而positions给出的坐标数量非常少，比如m=10万，n=10万，positions只给出三个坐标点的位置，那么为了这三个坐标点你要初始化一个10万*10万的二维数组，
 * 而这个二维数组大部分节点是用不到的，这样大量浪费了内存和申请内存的时间。如果说方式1是"饿汉模式"，那么方式2就是"懒汉模式"，方式2就是先不根据题目的要求去初始化m*n的二维数组，
 * positions给出一个坐标我就初始化一个位置，如果positions只给出了3个坐标，那么我就初始化撒个位置即可，看看这三个位置能连成几个岛即可，这样的话不浪费内存。
 * 那么在本类中会实现方式1，在com.mashibing.unionFind.NumberOfIslandsDynamic2中会实现方式2。
 * leetcode题目连接：https://leetcode.com/problems/number-of-islands-ii/
 *
 * 说明：这道题老师没有写对数器，leetcode上还是个付费题目，没有办法提交，所以没有验证。
 *
 * 课程：体系班课时126
 */
public class NumberOfIslandsDynamic1 {
    public ArrayList<Integer> numberOfIslandsDynamic(int m, int n, int[][] positions) {
        ArrayList<Integer> islandCountList = new ArrayList<>();
        // 由于题目说明了m和n都是正整数，所以不用校验
        if(positions == null || positions.length == 0) {
            return islandCountList;
        }

        UnionFind4 unionFind = new UnionFind4(m, n);
        for(int[] pos : positions) {
            islandCountList.add(unionFind.connect(pos[0], pos[1]));
        }

        return islandCountList;
    }

    static class UnionFind4 {
        private int[] parentArr;
        private int[] sizeArr;
        private int[] stack;
        private int size;
        private int rowCount;
        private int colCount;


        public UnionFind4(int m, int n) {
            int totcalCount = m * n;
            parentArr = new int[totcalCount];
            sizeArr = new int[totcalCount];
            stack = new int[totcalCount];
            size = 0;
            rowCount = m;
            colCount = n;
        }

        private int findIndex(int i, int j) {
            return i * colCount + j;
        }

        /**
         * 查找代表节点
         *
         * @param index 原始数组arr[i][j]中通过findIndex方法换算出的index值
         * @return 该样本值的代表节点
         */
        public int findAncestor(int index) {
            int stackIndex = 0;
            while (parentArr[index] != index) {
                stack[stackIndex++] = index;
                index = parentArr[index];
            }

            for (int i = stackIndex - 1; i >= 0; i--) {
                parentArr[stack[i]] = index;
            }

            return index;
        }

        /**
         * 在原始arr数组中(i1,j1)位置和(i2,j2)位置合并
         * @param i1
         * @param j1
         * @param i2
         * @param j2
         */
        public void union(int i1, int j1, int i2, int j2) {
            /*
            为什么要在union方法里面去判断越界以及是否遇到1，是因为在之前岛问题的实现中，这两个判断是在union的调用方进行的，调用方去判断下标是不是越界以及是不是遇到了1，
            如果遇到了1会调用union方法合并集合。但是看connect()方法可知，在union的调用方并没有做任何判断，不由分说地让(i,j)节点和其上下左右去合并，那么调用方不做判断，
            这个判断又必须做，只能放在union里面去做了。
             */
            // 防止下标越界
            if(i1 < 0 || i1 >= rowCount || i2 < 0 || i2 >= rowCount || j1 < 0 || j1 >= colCount || j2 < 0 || j2 >= colCount) {
                return;
            }

            int index1 = findIndex(i1, j1);
            int index2 = findIndex(i2, j2);
            // 判断要合并的是不是两个1，如果其中有一个是0就不需要合并，所以下面的sizeArr[smaller]不能清零
            if(sizeArr[index1] == 0 || sizeArr[index2] == 0) {// 如果sizeArr[index1]=0，说明没有初始化过，说明还没有遇到过1
                return;
            }


            int ancestor1 = findAncestor(index1);
            int ancestor2 = findAncestor(index2);
            if (ancestor1 == ancestor2) {
                return;
            }

            int size1 = sizeArr[ancestor1];
            int size2 = sizeArr[ancestor2];
            int bigger = size1 >= size2 ? ancestor1 : ancestor2;
            int smaller = bigger == ancestor1 ? ancestor2 : ancestor1;
            parentArr[smaller] = bigger;
            sizeArr[bigger] += sizeArr[smaller];
            /*
             注意：在这种实现中，smaller为代表节点的size不能清空，因为要留下痕迹，通过看sizeArr[i]是不是为0来判断i位置上的数是不是初始化过，
             由于只有遇到1才会被初始化，所以通过看sizeArr[i]是不是为0来判断i位置上的值是不是遇到过1。
             为啥用sizeArr判断而不用parentArr来判断？因为parentArr的索引即值，值即索引，parentArr的0位置初始化的值就是0，所以根据parentArr[0]的值0判断不出来有没有初始化过。
             */
//            sizeArr[smaller] = 0;
            size--;
        }

        /**
         *
         * @param i position的横坐标
         * @param j position的纵坐标
         * @return
         */
        private int connect(int i, int j) {
            int index = findIndex(i, j);
            /*
            判断(i,j)之前有没有改过1，有的话就直接返回现有的集合个数
            比如：positions=[[5,7],[5,8],[5,7]]，第二次输入的(5,7)是无效的，重复输入没有意义
             */
            if(sizeArr[index] != 0) {// 说明该位置之前已经改过1了
                return size;
            }

            parentArr[index] = index;
            sizeArr[index] = 1;
            size++;
            // 以(i,j)为起点，上下左右去和1合并
            union(i, j, i - 1, j);// 和左边合并
            union(i, j, i + 1, j);// 和右边合并
            union(i, j, i, j - 1);// 和下边合并
            union(i, j, i, j + 1);// 和上边合并

            return size;
        }
    }

    public static void main(String[] args) {
        // 测试链接：https://leetcode.com/problems/number-of-islands-ii/，但是得是会员才能测
    }
}
