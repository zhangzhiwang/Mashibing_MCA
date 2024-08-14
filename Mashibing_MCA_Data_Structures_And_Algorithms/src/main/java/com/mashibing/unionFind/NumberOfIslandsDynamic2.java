package com.mashibing.unionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集题目3——岛问题变种，动态岛问题，实现方式2
 * 说明：同com.mashibing.unionFind.NumberOfIslandsDynamic1情况一样，本类也没有测试过
 * 课程：体系班课时126
 */
public class NumberOfIslandsDynamic2 {
    public ArrayList<Integer> numberOfIslandsDynamic(int m, int n, int[][] positions) {
        ArrayList<Integer> islandCountList = new ArrayList<>();
        if (positions == null || positions.length == 0) {
            return islandCountList;
        }

        UnionFind5 unionFind = new UnionFind5();
        for (int[] pos : positions) {
            islandCountList.add(unionFind.connect(pos[0], pos[1]));
        }

        return islandCountList;
    }

    static class UnionFind5 {
        /*
        这里value是String类型的，也就是直接将字符串作为代表节点，省去了并查集节点，也省去了nodeMap属性。
        当然如果为了并查集组件的完整性，也可以使用并查集节点，定义一个节点类，将String类型包装进去，然后再定义一个nodeMap属性。
         */
        private Map<String, String> parentMap;
        private Map<String, Integer> sizeMap;
        private Stack<String> stack;

        public UnionFind5() {// 初始化时不对二维数组做初始化，二维数组为空
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            stack = new Stack<>();
        }

        public String findAncestor(String key) {
            int stackIndex = 0;
            while (!parentMap.get(key).equals(key)) {
                stack.add(key);
                key = parentMap.get(key);
            }

            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), key);
            }

            return key;
        }

        public void union(String key1, String key2) {
            /*
            上下左右遇到1时才合并，遇到0不合并，这个判断逻要么放在主函数中，如果主函数不判断就得在union里判断
             */
            if(!parentMap.containsKey(key1) || !parentMap.containsKey(key2)) {// 说明要合并的两个节点有一个没有被初始化过，也就是有一个不是1
                return;
            }

            String ancestor1 = findAncestor(key1);
            String ancestor2 = findAncestor(key2);
            int size1 = sizeMap.get(ancestor1);
            int size2 = sizeMap.get(ancestor2);
            String bigger = size1 >= size2 ? ancestor1 : ancestor2;
            String smaller = bigger == ancestor1 ? ancestor2 : ancestor1;
            parentMap.put(smaller, bigger);
            sizeMap.put(bigger, size1 + size2);
            sizeMap.remove(smaller);
        }

        /**
         * @param i position的横坐标
         * @param j position的纵坐标
         * @return
         */
        private int connect(int i, int j) {
            // 将横纵坐标转换为"i_j"字符串的形式
            String key = i + "_" + j;
            if (parentMap.containsKey(key)) {// 由于不用int数组来做并查集，所以就可以使用parentMap来判断节点是不是初始化过了
                return sizeMap.size();
            }

            // 懒初始化：来一个坐标初始化一个
            parentMap.put(key, key);
            sizeMap.put(key, 1);

            // 上面的key是用双引号括起来的下划线，所以将int型转换为了String类型，其它地方要采用统一标准，测试时其它地方不小心将下划线用单引号扩了起来，导致计算结果不一致
            union(i + "_" + j, (i - 1) + "_" + j);
            union(i + "_" + j, (i + 1) + "_" + j);
            union(i + "_" + j, i + "_" + (j - 1));
            union(i + "_" + j, i + "_" + (j + 1));

            return sizeMap.size();
        }
    }
}
