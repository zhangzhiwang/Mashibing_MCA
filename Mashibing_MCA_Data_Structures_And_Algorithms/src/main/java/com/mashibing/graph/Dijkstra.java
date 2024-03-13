package com.mashibing.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 迪瑞克撕拉算法
 * 题目：给定一个有向图的起始节点a，边的权重为非负数，返回一个哈希表，这个哈希表描述的是从起点a出发，a能到达的每一个节点的最小距离（自己到自己的距离是0），如果从a出发不能到达的点则不要放到哈希表里。
 * 说明：先说下迪瑞克撕拉算法的三个前提条件：首先必须是有向图，其次必须给出一个起点，最后边的权重不能是负数。
 * 思路：
 * 1、准备一个哈希表，这个哈希表也是最终要返回的，key是所有从起点a开始能够到达到的节点，value是从a开始到该节点的最小距离（最小权重和），
 * 不在该哈希表里面的节点就代表从a出发无法达到该节点，或者认为a到该节点的距离是正无穷。
 * 2、假设a到某节点的距离是distance，遍历整个哈希表，将距离最小的节点拿出来（过滤掉已冻结的节点），假设把节点b拿出来了，遍历b的所有邻居并更新哈希表，然后b节点就被冻结了，以后不用再看了，
 * 等哈希表所有节点都被冻结了就结束。
 *
 * 课程：体系班课时152-157
 */
public class Dijkstra {
    /**
     *
     * @param head 起始节点
     * @return key是所有从起点a开始能够到达到的节点，value是从a开始到该节点的最小距离（最小权重和）
     */
    public static Map<GraphNode<String>, Integer> dijkstra(GraphNode<String> head) {
        Map<GraphNode<String>, Integer> retMap = new HashMap<>();
        if(head == null) {
            return retMap;
        }

        // 根据题目要求：自己到自己的距离是0，所以先把起始节点放进去
        retMap.put(head, 0);
        Set<GraphNode<String>> frozenNodeSet = new HashSet<>();
        /*
        注意：findShortestNode方法虽然可以实现功能，但是时间复杂度是O(N)，因为每调用一次该方法就要遍历一遍retMap，找出最小距离返回，
        这时一个优化点，可以使用加强堆来实现相同的功能，见体系班课程第157视频。
        说明：使用加强堆优化可以使时间复杂度降为O(logN)，但是在面试现场如果遇到迪瑞克撕拉算法，解答的时候再写一个加强堆出来比较费时费力，
        一般情况下再考查此算法时不会时间复杂度限制这么死，所以面试时使用本方法实现即可。
        TODO 由于时间关系使用加强堆优化的方法没有做实现，以后补上。
         */
        GraphNode<String> node = findShortestNode(retMap, frozenNodeSet);// record就是一个跳转点，意为从a经过record后跳转到下一个节点
        while(node != null) {
            int headToNodeDis = retMap.get(node);// 头结点到该节点的距离
            for(Edge<String> edge : node.edges) {
                GraphNode<String> toNode = edge.to;
                if(!retMap.containsKey(toNode)) {// toNode不在retMap里面就加进去
                    retMap.put(toNode, headToNodeDis + edge.weight);
                } else {// toNode在retMap里面就更新
                    // 将距离更新为最小值：看看是原来从a直接到toNode的距离更短，还是从a到record，再从record跳转到toNode距离更短
                    retMap.put(toNode, Math.min(headToNodeDis + edge.weight, retMap.get(toNode)));
                }
            }

            // 将node的所有出度和邻居节点都考察完之后node被冻结，以后就不再看node了
            frozenNodeSet.add(node);
            // 选出除了在frozenNodeSet之外的距离最小的节点，进行下一轮循环
            node = findShortestNode(retMap, frozenNodeSet);
        }

        return retMap;
    }

    /**
     * 找到Map中距离最小的节点，过滤掉已经被冻结的节点
     * @param map
     * @param frozenNodeSet 被冻结节点的集合
     * @return
     */
    private static GraphNode<String> findShortestNode(Map<GraphNode<String>, Integer> map, Set<GraphNode<String>> frozenNodeSet) {
        GraphNode<String> record = null;
        int minDis = Integer.MAX_VALUE;
        for(GraphNode<String> node : map.keySet()) {
            int dis = map.get(node);
            if(frozenNodeSet.contains(node) || dis > minDis) {
                continue;
            }

            minDis = dis;// 不要忘了更新minDis（测试的时候由于没有写这句话到时结果不对）
            record = node;
        }

        return record;
    }

    public static void main(String[] args) {
        GraphNode<String> a = new GraphNode<>("a");
        GraphNode<String> b = new GraphNode<>("b");
        GraphNode<String> c = new GraphNode<>("c");
        GraphNode<String> d = new GraphNode<>("d");
        GraphNode<String> e = new GraphNode<>("e");
        a.nexts.add(b);
        a.nexts.add(c);
        a.nexts.add(d);
        a.edges.add(new Edge<>(1, a, b));
        a.edges.add(new Edge<>(4, a, c));
        a.edges.add(new Edge<>(6, a, d));

        b.nexts.add(c);
        b.nexts.add(e);
        b.edges.add(new Edge<>(2, b, c));
        b.edges.add(new Edge<>(4, b, e));

        c.nexts.add(d);
        c.nexts.add(e);
        c.edges.add(new Edge<>(1, c, d));
        c.edges.add(new Edge<>(1, c, e));

        d.nexts.add(e);
        d.edges.add(new Edge<>(5, d, e));

        Map<GraphNode<String>, Integer> map = dijkstra(a);
        for(GraphNode<String> node : map.keySet()) {
            System.out.println(node.data + " = " + map.get(node));
        }
    }
}
