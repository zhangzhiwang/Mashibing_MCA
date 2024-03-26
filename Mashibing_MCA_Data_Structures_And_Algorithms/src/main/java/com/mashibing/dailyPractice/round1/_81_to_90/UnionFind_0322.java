package com.mashibing.dailyPractice.round1._81_to_90;

import java.util.*;

/**
 * 实现一个并查集
 */
public class UnionFind_0322<T> {
    class UnionFindNode_0322<T> {
        private T t;

        public UnionFindNode_0322(T t) {
            this.t = t;
        }
    }

    private Map<T, UnionFindNode_0322> nodeMap = new HashMap<>();
    private Map<UnionFindNode_0322, UnionFindNode_0322> parentMap = new HashMap<>();
    private Map<UnionFindNode_0322, Integer> sizeMap = new HashMap<>();

    public UnionFind_0322(List<T> list) {
        if(list == null || list.size() == 0) {
            return;
        }

        for (T t : list) {
            UnionFindNode_0322 node = new UnionFindNode_0322(t);
            nodeMap.put(t, node);
            parentMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public boolean isSameSet(T t1, T t2) {
        if(t1 == null && t2 == null) {
            return true;
        } else if (t1 == null ^ t2 == null) {
            return false;
        }

        UnionFindNode_0322 node1 = nodeMap.get(t1);
        UnionFindNode_0322 node2 = nodeMap.get(t2);
        if(node1 == null || node2 == null) {
            return false;
        }
        return findRepresentative(node1) == findRepresentative(node2);
    }

    public void union(T t1, T t2) {
        if(t1 == null && t2 == null) {
            return;
        } else if (t1 == null ^ t2 == null) {
            return;
        }

        UnionFindNode_0322 node1 = nodeMap.get(t1);
        UnionFindNode_0322 node2 = nodeMap.get(t2);
        if(node1 == null || node2 == null) {
            return;
        }

        if(isSameSet(t1, t2)) {
            return;
        }

        UnionFindNode_0322 r1 = findRepresentative(node1);
        UnionFindNode_0322 r2 = findRepresentative(node2);
        int size1 = sizeMap.get(r1);
        int size2 = sizeMap.get(r2);
        UnionFindNode_0322 longR = size1 >= size2 ? r1 : r2;
        UnionFindNode_0322 shortR = longR == r1 ? r2 : r1;

        parentMap.put(shortR, longR);
        sizeMap.put(longR, size1 + size2);
        sizeMap.remove(shortR);
    }

    public int size() {
        return sizeMap.size();
    }

    private UnionFindNode_0322 findRepresentative(UnionFindNode_0322 node) {
        if(node == null) {
            return null;
        }

        Stack<UnionFindNode_0322> stack = new Stack<>();
        UnionFindNode_0322 parent = null;
        while(parent != node) {
            stack.add(node);
            parent = parentMap.get(node);
            stack.add(parent);
            node = parentMap.get(parent);
        }

        parent = stack.pop();
        while (!stack.isEmpty()) {
            parentMap.put(stack.pop(), parent);
        }

        return parent;
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
