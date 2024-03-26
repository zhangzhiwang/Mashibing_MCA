package com.mashibing.dailyPractice.round1._81_to_90;

import com.mashibing.graph.Edge;
import com.mashibing.graph.GraphNode;

import java.util.*;

/**
 * 给定一个有向图的起始节点a，边的权重为非负数，返回一个哈希表，这个哈希表描述的是从起点a出发，
 * a能到达的每一个节点的最小距离（自己到自己的距离是0），如果从a出发不能到达的点则不要放到哈希表里
 */
public class Dijkstra_0321 {
    public static Map<GraphNode<String>, Integer> dijkstra(GraphNode<String> start) {
        Map<GraphNode<String>, Integer> retMap = new HashMap<>();
        if(start == null) {
            return retMap;
        }

        Set<GraphNode<String>> set = new HashSet<>();
        retMap.put(start, 0);

        GraphNode<String> node = findShortestNode(retMap, set);
        while(node != null) {
            Integer dis = retMap.get(node);
            for (Edge<String> edge: node.edges) {
                GraphNode<String> toNode = edge.to;
                if(!retMap.containsKey(toNode)) {
                    retMap.put(toNode, dis + edge.weight);
                } else {
                    retMap.put(toNode, Math.min(dis + edge.weight, retMap.get(toNode)));
                }
            }

            set.add(node);
            node = findShortestNode(retMap, set);
        }

        return retMap;
    }

    private static GraphNode<String> findShortestNode(Map<GraphNode<String>, Integer> map, Set<GraphNode<String>> set) {
        int minDis = Integer.MAX_VALUE;
        GraphNode<String> shortestNode = null;
        for (GraphNode<String> node : map.keySet()) {
            int dis = map.get(node);
            if(set.contains(node) || dis >= minDis) {
                continue;
            }

            minDis = dis;
            shortestNode = node;
        }

        return shortestNode;
    }

    public static void main(String[] args) {
        GraphNode<String> a = new GraphNode<>("a");
        GraphNode<String> b = new GraphNode<>("b");
        GraphNode<String> c = new GraphNode<>("c");
        GraphNode<String> d = new GraphNode<>("d");
        GraphNode<String> e = new GraphNode<>("e");
        GraphNode<String> f = new GraphNode<>("f");
        a.nexts.add(b);
        a.nexts.add(c);
        a.nexts.add(d);
        a.edges.add(new Edge<>(3, a, b));
        a.edges.add(new Edge<>(2, a, c));
        a.edges.add(new Edge<>(10, a, d));

        b.nexts.add(d);
        b.edges.add(new Edge<>(5, b, d));

        c.nexts.add(d);
        c.edges.add(new Edge<>(1, c, d));

        d.nexts.add(e);
        d.edges.add(new Edge<>(9, d, e));

        e.nexts.add(f);
        e.edges.add(new Edge<>(4, e, f));

        f.nexts.add(d);
        f.edges.add(new Edge<>(7, f, d));

        System.out.println(dijkstra(a));
    }
}
