package com.mashibing.dailyPractice.round1._81_to_90;

/**
 * 并查集的第二种实现——使用数组
 */
public class UnionFind2_0322 {
    private int[] parent;
    private int[] setSize;
    private int size;

    public UnionFind2_0322(int N) {
        parent = new int[N];
        setSize = new int[N];
        size = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            setSize[i] = 1;
        }
    }

    public boolean isSameSet(int num1, int num2) {
        if(num1 < 0 || num2 < 0 || num1 >= parent.length || num2 >= parent.length) {
            return false;
        }
        return findRepresentative(num1) == findRepresentative(num2);
    }

    public void union(int num1, int num2) {
        if(num1 < 0 || num2 < 0 || num1 >= parent.length || num2 >= parent.length) {
            return;
        }

        if(isSameSet(num1, num2)) {
            return;
        }

        int r1 = findRepresentative(num1);
        int r2 = findRepresentative(num2);
        int longR = setSize[r1] >= setSize[r2] ? r1 : r2;
        int shortR = longR == r1 ? r2 : r1;
        parent[shortR] = longR;
        setSize[longR] += setSize[shortR];
        setSize[shortR] = 0;
        size--;
    }

    public int size() {
        return size;
    }

    private int findRepresentative(int num) {
        if(num < 0) {
            return -1;
        }

        int[] stack = new int[parent.length];
        int stackIndex = 0;
        while (parent[num] != num) {
            stack[stackIndex++] = num;
            num = parent[num];
        }

        for (int i = stackIndex - 1; i >= 0; i--) {
            parent[stack[i]] = num;
        }

        return num;
    }

    public static void main(String[] args) {
        UnionFind2_0322 uf = new UnionFind2_0322(5);
        System.out.println("size = " + uf.size());
        System.out.println(uf.isSameSet(1,3));
        uf.union(3,1);
        System.out.println(uf.isSameSet(1,3));
        System.out.println("size = " + uf.size());
        System.out.println("------------");

        System.out.println(uf.isSameSet(4,3));
        uf.union(3,4);
        System.out.println(uf.isSameSet(4,3));
        System.out.println("size = " + uf.size());
        System.out.println("------------");

        System.out.println(uf.isSameSet(0,2));
        uf.union(2,0);
        System.out.println(uf.isSameSet(2,0));
        System.out.println("size = " + uf.size());
        System.out.println("------------");

        System.out.println(uf.isSameSet(0,4));
        uf.union(4,0);
        System.out.println(uf.isSameSet(4,0));
        System.out.println("size = " + uf.size());
        System.out.println("------------");
    }
}
