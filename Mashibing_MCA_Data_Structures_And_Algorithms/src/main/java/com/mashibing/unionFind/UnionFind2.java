package com.mashibing.unionFind;

/**
 * 并查集的第二种实现——使用数组实现
 * 思路：在com.mashibing.unionFind.UnionFind中是使用Map来实现的并查集，虽然Map的插入、查找的时间复杂度是O(1)，但是这个常数操作是比较大的常数操作。
 * 用数组来实现并查集的速度要快于使用Map来实现的，但是使用数组来实现对于数据样本就有要求了，必须是非负整数，因为样本值本身要作为数组的下标。
 * 另外，本类所实现的并查集对于样本数据还有一个限制，那就是如果输入一个N，N是一个非负整数，代表样本的数据量，那么样本数据的值是从0到N-1连续的整数值。
 * 比如输入N=5，代表样本量大小有5个数据，数据分别为0、1、2、3、4，是连续的，因为值本身要作为数组的下标。
 * 本类的实现可以看做是com.mashibing.unionFind.UnionFind的简化版，且对样本数据有限制要求
 */
public class UnionFind2 {
    // 在本类的实现中，样本数据即结点，节点即数据，所以不需要给样本值外面包一层做成节点了
    // parentArr的索引代表样本值，索引所对应的值代表直接父节点，比如parentArr[i] = j，i的直接父节点是j
    private int[] parentArr;// 相当于com.mashibing.unionFind.UnionFind中的parentMap
    // sizeArr的索引表示代表节点，索引对应的值表示代表节点背后集合的节点数量，比如sizeArr[i] = j，代表节点i代表的集合有j个节点
    private int[] sizeArr;// 相当于com.mashibing.unionFind.UnionFind中的nodeSizeMap，对于sizeArr[i]，只有i位置是代表结点时才有效
    // 辅助数组，在findAncestor方法中代替栈
    private int[] stack;
    // 集合的个数，注意如果用数组来实现并查集size就是sizeArr.length了，因为实际存在的集合数量可能比sizeArr数组初始化时设置的长度小
    private int size;

    /**
     * 输入N，N代表样本的规模，实际的样本值是[0,N-1]
     *
     * @param N
     */
    public UnionFind2(int N) {
        this.parentArr = new int[N];
        this.sizeArr = new int[N];
        this.stack = new int[N];
        this.size = N;

        for(int i = 0; i < N; i++) {
            parentArr[i] = i;
            sizeArr[i] = 1;
        }
    }

    /**
     * 查找代表节点
     * @param num 给定的样本值
     * @return 该样本值的代表节点
     */
    public int findAncestor(int num) {// 实现思路见com.mashibing.unionFind.UnionFind#findAncestor
        int stackIndex = 0;
        while(parentArr[num] != num) {// 当自己是自己的直接父节点的时候，自己就是代表节点了，只有代表节点自己是自己的直接父节点
            stack[stackIndex++] = num;// 相当于压栈
            num = parentArr[num];
        }
        // 从上面的while循环出来num就是代表节点了

        // 优化：从num及以上做扁平化处理
        // 由于栈是FILO，所以要倒序遍历数组
        for(int i = stackIndex - 1; i >= 0; i--) {
            parentArr[stack[i]] = num;
        }

        return num;
    }

    public boolean isSameSet(int num1, int num2) {
        return findAncestor(num1) == findAncestor(num2);
    }

    public void union(int num1, int num2) {
        int ancestor1 = findAncestor(num1);
        int ancestor2 = findAncestor(num2);
        if(ancestor1 == ancestor2) {
            return;
        }

        int size1 = sizeArr[ancestor1];
        int size2 = sizeArr[ancestor2];
        int bigger = size1 >= size2 ? ancestor1 : ancestor2;
        int smaller = bigger == ancestor1 ? ancestor2 : ancestor1;
        parentArr[smaller] = bigger;
        sizeArr[bigger] += sizeArr[smaller];
        sizeArr[smaller] = 0;
        size--;
    }

    public int size() {
        return size;
    }
}
