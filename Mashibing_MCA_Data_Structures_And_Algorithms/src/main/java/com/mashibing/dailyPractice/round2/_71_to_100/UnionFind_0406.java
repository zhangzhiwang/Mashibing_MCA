package com.mashibing.dailyPractice.round2._71_to_100;

import com.mashibing.dailyPractice.round1._81_to_90.UnionFind_0322;

import java.util.*;

/**
 * 实现一个并查集
 */
public class UnionFind_0406<T> {
    static class UnionFindNode_0406<T> {
        private T t;

        public UnionFindNode_0406(T t) {
            this.t = t;
        }
    }

    private Map<T, UnionFindNode_0406> nodeMap = new HashMap<>();
    private Map<UnionFindNode_0406, UnionFindNode_0406> parentMap = new HashMap<>();
    private Map<UnionFindNode_0406, Integer> sizeMap = new HashMap<>();

    public UnionFind_0406(List<T> list) {
        if(list == null || list.size() == 0) {
            return;
        }

        for (T t : list) {
            UnionFindNode_0406 node = new UnionFindNode_0406(t);
            nodeMap.put(t, node);
            parentMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public <T> boolean isSameSet(T t1, T t2) {
        if(t1 == null || t2 == null) {
            return false;
        }

        if(!nodeMap.containsKey(t1) || !nodeMap.containsKey(t2)) {
            return false;
        }

        return findAncestor(nodeMap.get(t1)) == findAncestor(nodeMap.get(t2));
    }

    public <T> void union(T t1, T t2) {
        if(t1 == null || t2 == null) {
            return;
        }

        if(!nodeMap.containsKey(t1) || !nodeMap.containsKey(t2)) {
            return;
        }

        if(isSameSet(t1, t2)) {
            return;
        }

        UnionFindNode_0406 a1 = findAncestor(nodeMap.get(t1));
        UnionFindNode_0406 a2 = findAncestor(nodeMap.get(t2));
        int size1 = sizeMap.get(a1);
        int size2 = sizeMap.get(a2);
        UnionFindNode_0406 longAncestor = size1 >= size2 ? a1 : a2;
        UnionFindNode_0406 shortAncestor = longAncestor == a1 ? a2 : a1;
        parentMap.put(shortAncestor, longAncestor);
        sizeMap.put(longAncestor, size1 + size2);
        sizeMap.remove(shortAncestor);
    }

    public int size() {
        return sizeMap.size();
    }

    private <T> UnionFindNode_0406 findAncestor(UnionFindNode_0406 node) {
        Stack<UnionFindNode_0406> stack = new Stack<>();
        while (parentMap.get(node) != node) {
            stack.add(node);
            node = parentMap.get(node);
        }

        while (!stack.isEmpty()) {
            parentMap.put(stack.pop(), node);
        }

        return node;
    }

    public static void main(String[] args) {
        UnionFind_0322 uf = new UnionFind_0322(Arrays.asList(1,2,3,4,5));
        System.out.println(uf.size());
        System.out.println(uf.isSameSet(1,3));
        uf.union(1,3);
        System.out.println(uf.isSameSet(1,3));
        System.out.println(uf.size());
        System.out.println("-----------");


        System.out.println(uf.isSameSet(3,5));
        uf.union(5,3);
        System.out.println(uf.isSameSet(3,5));
        System.out.println(uf.size());
        System.out.println("-----------");

        System.out.println(uf.isSameSet(4,3));
        uf.union(4,3);
        System.out.println(uf.isSameSet(4,3));
        System.out.println(uf.size());
        System.out.println("-----------");

        System.out.println(uf.isSameSet(2,5));
        uf.union(2,5);
        System.out.println(uf.isSameSet(2,5));
        System.out.println(uf.size());
        System.out.println("-----------");

        System.out.println(uf.isSameSet(3,5));
    }
}
