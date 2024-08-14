package com.mashibing.dailyPractice.round2._71_to_100._0708;

import com.mashibing.graph.Edge;
import com.mashibing.graph.GraphNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 迪瑞克撕拉算法:
 * 给定一个有向图的起始节点a，边的权重为非负数，返回一个哈希表，这个哈希表描述的是从起点a出发，
 * a能到达的每一个节点的最小距离（自己到自己的距离是0），如果从a出发不能到达的点则不要放到哈希表里。
 */
public class Dijkstra_0713 {
    public static Map<GraphNode<String>, Integer> dijkstra(GraphNode<String> node) {
        Map<GraphNode<String>, Integer> map = new HashMap<>();
        if(node == null) {
            return map;
        }

        map.put(node, 0);
        Set<GraphNode<String>> set = new HashSet<>();
        GraphNode<String> minDisNode = findMinDis(map, set);
        while (minDisNode != null) {
            for (Edge<String> edge : minDisNode.edges) {
                GraphNode<String> toNode= edge.to;
                int minDisNodeDis = map.get(minDisNode);
                int weight = edge.weight;
                if(!map.containsKey(toNode) || minDisNodeDis + weight < map.get(toNode)) {
                    map.put(toNode, minDisNodeDis + weight);
                }
            }

            set.add(minDisNode);
            minDisNode = findMinDis(map, set);
        }

        return map;
    }

    private static GraphNode<String> findMinDis(Map<GraphNode<String>, Integer> map, Set<GraphNode<String>> set) {
        int minDis = Integer.MAX_VALUE;
        GraphNode<String> retNode = null;
        for (GraphNode<String> node : map.keySet()) {
            if(set.contains(node)) {
                continue;
            }

            int dis = map.get(node);
            if(dis < minDis) {
                minDis = dis;
                retNode = node;
            }
        }

        return retNode;
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
        a.edges.add(new Edge<>(20, a, b));
        a.edges.add(new Edge<>(10, a, c));
        a.edges.add(new Edge<>(1, a, d));

        c.nexts.add(b);
        c.nexts.add(e);
        c.edges.add(new Edge<>(3, c, b));
        c.edges.add(new Edge<>(5, c, e));

        d.nexts.add(c);
        d.edges.add(new Edge<>(2, d, c));

        e.nexts.add(d);
        e.edges.add(new Edge<>(7, e, d));

        Map<GraphNode<String>, Integer> map = dijkstra(a);
        for(GraphNode<String> node : map.keySet()) {
            System.out.println(node.data + " = " + map.get(node));
        }
    }
}
