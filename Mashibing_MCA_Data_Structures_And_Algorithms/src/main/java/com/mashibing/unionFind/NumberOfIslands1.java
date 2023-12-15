package com.mashibing.unionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * 并查集题目2——岛问题
 * 题目：给定一个二维数组arr，里面的值不是0就是1，上、下、左、右相邻的1认为是一片岛，返回arr中岛的数量。
 * 说明：上、下、左、右四个方向分别为正上、正下、正左、正右，斜上、斜下、斜左、斜右不算数。
 * 思路：
 * 本类是岛问题的第一种实现——使用经典并查集的实现方式来做，由于并查集的经典实现有一个限制，就是要求样本数据如果是基本数据类型的话值不能重复，
 * 显然本题目不符合经典实现的样本要求，所以只能改进一下：将样本值封装进Dot对象里，再将Dot对象封装进UnionFindNode里。
 * 1、建立一个和arr长宽等规模的Dot数组，并将arr中的所有的1拷贝进Dot数组同样位置里，千万注意不能将0拷贝进Dot数组，arr里面0的位置对应Dot数组中的null
 * 2、虽然题目要求的是上下左右四个方向，但是判断的时候只判断左和下两个方向即可。比如A元素无需判断它的右侧，因为如果它右侧有节点B的话，
 * 等到循环到B的时候B去判断它的左侧就相当于当初A判断它右侧了，如果A没有右侧正好也不用判断了，A上方节点同理：等循环到A上方的C节点时，C去判断它下方A就行了，所以不会丢。
 * 这里有个小技巧：如果从坐标系的(0,0)出发，那么用三个循环分别判断矩阵的左边、下边和中间区域，因为最左边的节点它没有左节点，最下边的节点没有下节点，
 * 只有中间区域既有左邻居也有下邻居。其实写在一个循环里用if判断也可以，判断每个节点是不是有左邻居和下邻居，虽然判断逻辑是O(1)的，但是这个常数操作要判断M*N次，
 * 拆成3个并列的循环之后，只有第三个循环的中间区域是需要做判断的，这样是减少常数操作的次数，同时由于循环是并列的时间复杂度仍然是O(N)的。
 * 3、遇到邻居一样的就让并查集union，最终返回集合的数量。
 * <p>
 * 岛屿问题举一反三，leetcode原题：
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * leetcode题目地址：https://leetcode.cn/problems/number-of-islands/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 *
 * 课程：体系班课时126
 */
public class NumberOfIslands1 {
    static class Dot<T> {
        public T data;

        public Dot(T data) {
            this.data = data;
        }
    }

    public static int numberOfIslands(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int rowCount = arr.length;
        int colCount = arr[0].length;

        // 将二维数组里面的所有值都放进并查集里面并构建等规模等数据的Dot[][]
        List<Dot<Integer>> list = new ArrayList<>();
        Dot<Integer>[][] dotArr = new Dot[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Dot dot = new Dot(arr[i][j]);
                dotArr[i][j] = dot;
                if(arr[i][j] == 1) {// 这里注意一定不要把值为0的放入并查集里面，否则最后输出的size不是所有相邻1合并后1集合的数量，里面还掺杂着0节点
                    list.add(dot);
                }
            }
        }

        UnionFind<Dot<Integer>> unionFind = new UnionFind<>(list);
        for (int i = 0; i < rowCount; i++) {
            if (i != rowCount - 1 && dotArr[i][0].data == 1 && dotArr[i + 1][0].data == 1) {
                unionFind.union(dotArr[i][0], dotArr[i + 1][0]);
            }
        }

        for (int j = 0; j < colCount; j++) {
            if (j != colCount - 1 && dotArr[0][j].data == 1 && dotArr[0][j + 1].data == 1) {
                unionFind.union(dotArr[0][j], dotArr[0][j + 1]);
            }
        }

        for (int i = 1; i < rowCount; i++) {
            for (int j = 1; j < colCount; j++) {
                if (dotArr[i][j].data == 1 && dotArr[i - 1][j].data == 1) {
                    unionFind.union(dotArr[i][j], dotArr[i - 1][j]);
                }
                if (dotArr[i][j].data == 1 && dotArr[i][j - 1].data == 1) {
                    unionFind.union(dotArr[i][j], dotArr[i][j - 1]);
                }
            }
        }

        return unionFind.size();
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                new int[]{1, 1, 1, 1, 0},
                new int[]{1, 1, 0, 1, 0},
                new int[]{1, 1, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0},
        };

        int numberOfIslands = numberOfIslands(arr);
        System.out.println("my answer : " + numberOfIslands);
    }
}
