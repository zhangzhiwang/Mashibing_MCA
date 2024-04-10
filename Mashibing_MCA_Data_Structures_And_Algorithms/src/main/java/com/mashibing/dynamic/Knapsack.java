package com.mashibing.dynamic;

/**
 * 动态规划题目3：
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 *
 * 思路：
 * 暴力递归的思路：每一个物品都有选和不选两种可能性，返回这两种可能性的最大收益
 *
 * 课程：体系班课时181-184
 */
public class Knapsack {
    // ---------------版本一：暴力递归版---------------
    public static int knapsack1(int[] weights, int[] values, int bag) {
        if(weights == null || values == null || weights.length != values.length || weights.length == 0) {
            throw new RuntimeException("参数有误！");
        }

        return recurse(weights, values, bag, 0);
    }

    /**
     *
     * @param weights
     * @param values
     * @param rest 背包剩余可装的重量
     * @param index 当前考查的位置
     * @return
     */
    private static int recurse(int[] weights, int[] values, int rest, int index) {
        /*
         base case：base case有两个：一个是没有东西可以选了，一个是数书包没有剩余重量额度了，要先判断rest后判断index，不能反。
         说白了就是先看看书包还能不能装，再看还有没有东西可装
         */
        // base case1
        /*
        当书包剩余能装的重量为0时，说明已经装满了，当前考察的index位置的物品肯定不能要了。
        但是跟base case2不一样的是此种情况不能返回0，要返回-1，原因就是告诉上游调用的地方：此种方案不能选否则书包超载，所以要返回一个负数的无效值
         */
        if(rest < 0) {
            return -1;
        }

        // base case2
        // 注意：判断条件不是等于最后一个元素，而是最后一个元素的下一个元素，就是越界了，就代表当前考察的是一个不存在的物品，既然物品不存在那么它的价值一定是0
        if(index == weights.length) {
            return 0;
        }


        // 分两种情况：要当前index位置上的物品时的最大收益，和不要的收益
        //不要index的收益，直接到下一个位置上考查
        int p1 = recurse(weights, values, rest, index + 1);
//        int p2 = values[index] + recurse(weights, values, rest - weights[index], index + 1);// 如果要了index的物品的情况，不能这么写，原因在下面

        /*
        如果要了index上的物品，情况稍有点复杂，要了index那么p2就要加上index物品的价值，剩余重量就要减去index物品的重量，然后继续向index后面的位置考查。
        但是有可能要了index物品的时候就已经超标了，比如要index之前剩余rest是1，index本身的重量是7，如果要了index，那么剩余重量变成了-6，超载了，
        但是我在本轮递归中没有发现超载了，会继续调用递归函数考察后面的位置，但是考查后面位置的物品时发现rest已经小于0了，命中了base case1，base case1返回了-1，
        就是告诉上游：哥哥，你上一个物品就不应该要，因为你要了就超标了，这也就是为什么base case1不能返回0的原因，因为如果这种情况下游递归函数返回了0，上游是无感的，
        上游会以为是到了数组的尽头，如果下游返回一个无意义的值，那么上游就知道了：我选了index就超标了，所以不能选。
         */
        int v = recurse(weights, values, rest - weights[index], index + 1);// 先让下游判断一下是不是超标了
        int p2 = 0;
        if(v >= 0) {// 如果下游返回正常的值，说明本轮index是可以要的，就走正常逻辑：index的价值加上后续考查元素的价值，否则index就不能要，p2是0
            p2 = values[index] + v;
        }

        // 最后返回要和不要index两种情况的最大值
        return Math.max(p1, p2);
    }
    // -------------------------------------------
    // ---------------版本三：最终版---------------
    /*
    思路：
    看递归函数的入参，发现只有rest和index对递归过程产生影响，即(rest,index)，然后进行树形分析，发现确实存在重复计算的过程，题目可以改成动态规划。
    跳过版本二，通过版本一直接改出版本三：
    1、既然(rest,index)对递归产生影响，那么如果有版本二的话也是通过(rest,index)建立二维数组，不妨直接分析这两个变量。
    首先确定这两个变量的范围，然后一乘就是整个二维矩阵的规模。rest的范围是[0,bag]，index的范围是[0-N]，注意index可以到N，虽然N对于数组下标来说已越界。
    为什么0可以到N？是因为你的暴力递归写的0可以到N。
    所以就可以建立一个规模为(bag+1)*(N+1)的二维数组int[bag + 1][N+1]，这样下标可以把bag和N包括进去。
    2、确定base case的搁在在哪里、值怎么填。在草稿纸上画出二维表，看base case代码：当index=N的时候返回0，返回的就是收益，二维表的每一个格子也用于存放收益，所以index=N的所有列格子里都填0。
    3、确定最终返回的目标位置：(bag,0)
    4、确定一个普适位置怎么填。看递归怎么写的：p1和p2取最大值，而p1和p2都依赖index+1的位置，也就是都依赖它右边的格子；p1依赖rest，p2依赖rest - weights[index]，去掉weights，
    也就是依赖rest和index，就是自己依赖自己本身的格子，自身格子的值是纵坐标rest减去数组weights的某一个值，所以综上，一个普适位置的格子依赖于它右边的和自己。
    5、确定遍历顺序。通过上面分析，可以看到填入顺序是：横坐标index是从右往左填的，而纵坐标的是自己依赖自己，所以纵坐标的填入顺序正序逆序都可以。
     */
    public static int knapsack2(int[] weights, int[] values, int bag) {
        if(weights == null || values == null || weights.length != values.length || weights.length == 0) {
            throw new RuntimeException("参数有误！");
        }

        int N = weights.length;
        //准备二维数组
        int[][] buf = new int[N + 1][bag + 1];
        // 本来应该将base case的值在初始化的时候填好，但由于base case的值是横坐标=N的时候所有列上的数据都是0，而默认值恰巧也是0，所以可以理解为默认就填好了

        for(int index = N-1; index >= 0; index--) {// 横坐标为index，在base中index=N的位置已经填完了，所以从倒数第二个N-1的位置倒序填即可
            for(int rest = 0; rest <= bag; rest++) {// 纵坐标为rest
                int p1 = buf[index + 1][rest];
                int p2 = 0;
                int x = rest - weights[index] < 0 ? -1 : buf[index + 1][rest - weights[index]];
                if(rest - weights[index] >= 0) {
                    p2 = values[index] + x;
                }

                buf[index][rest] = Math.max(p1, p2);
            }
        }

        return buf[0][bag];
    }
    // -------------------------------------------

    public static void main(String[] args) {
        int[] weights = {1,2,3,4,5};
        int[] values = {1,2,3,4,5};
//        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
//        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 17;
        int result1 = knapsack1(weights, values, bag);
        System.out.println("result1 = " + result1);
        int result2 = knapsack2(weights, values, bag);
        System.out.println("result2 = " + result2);
    }
}
