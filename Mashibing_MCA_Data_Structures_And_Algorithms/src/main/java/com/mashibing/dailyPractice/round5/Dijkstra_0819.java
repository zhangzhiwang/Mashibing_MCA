package com.mashibing.dailyPractice.round5;

import com.mashibing.graph.Edge;
import com.mashibing.graph.GraphNode;

import java.util.*;

/**
 * 给定一个有向图的起始节点a，边的权重为非负数，返回一个哈希表，这个哈希表描述的是从起点a出发，
 * a能到达的每一个节点的最小距离（自己到自己的距离是0），如果从a出发不能到达的点则不要放到哈希表里。
 */
public class Dijkstra_0819 {
    public static Map<GraphNode<Integer>, Integer> dijkstra(GraphNode<Integer> a) {
        Map<GraphNode<Integer>, Integer> map = new HashMap<>();
        if(a == null) {
            return map;
        }

        map.put(a, 0);
        Set<GraphNode<Integer>> set = new HashSet<>();
        GraphNode<Integer> node = findMinNode(map, set);
        while (node != null) {
            List<Edge<Integer>> edges = node.edges;
            for (Edge<Integer> edge : edges) {
                int dis = edge.weight;
                GraphNode<Integer> toNode = edge.to;
                if(map.containsKey(toNode)) {
                    int oriDis = map.get(toNode);
                    if(map.get(node) + dis < oriDis) {
                        map.put(toNode, map.get(node) + dis);
                    }
                } else {
                    map.put(toNode, map.get(node) + dis);
                }
            }

            set.add(node);
            node = findMinNode(map, set);
        }

        return map;
    }

    private static GraphNode<Integer> findMinNode(Map<GraphNode<Integer>, Integer> map, Set<GraphNode<Integer>> set) {
        GraphNode<Integer> node = null;
        int minDis = Integer.MAX_VALUE;
        for (GraphNode<Integer> key : map.keySet()) {
            if (set.contains(key) || map.get(key) >= minDis) {
                continue;
            }

            node = key;
            minDis = map.get(key);
        }

        return node;
    }

    // 老师的代码
    public static HashMap<GraphNode<Integer>, Integer> dijkstra1(GraphNode<Integer> from) {
        HashMap<GraphNode<Integer>, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        // 打过对号的点
        HashSet<GraphNode<Integer>> selectedNodes = new HashSet<>();
        GraphNode<Integer> minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            //  原始点  ->  minNode(跳转点)   最小距离distance
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                GraphNode<Integer> toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else { // toNode
                    distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static GraphNode<Integer> getMinDistanceAndUnselectedNode(HashMap<GraphNode<Integer>, Integer> distanceMap, HashSet<GraphNode<Integer>> touchedNodes) {
        GraphNode<Integer> minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<GraphNode<Integer>, Integer> entry : distanceMap.entrySet()) {
            GraphNode<Integer> node = entry.getKey();
            int distance = entry.getValue();
            if (!touchedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    public static void main(String[] args) {
        GraphNode<Integer> a = new GraphNode<>(1);
        GraphNode<Integer> b = new GraphNode<>(2);
        GraphNode<Integer> c = new GraphNode<>(3);
        GraphNode<Integer> d = new GraphNode<>(4);
        GraphNode<Integer> e = new GraphNode<>(5);
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

        System.out.println("correct ans:");
        Map<GraphNode<Integer>, Integer> map = dijkstra1(a);
        for(GraphNode<Integer> node : map.keySet()) {
            System.out.println(node.data + " = " + map.get(node));
        }

        System.out.println("-----------");
        System.out.println("my ans:");
        map = dijkstra(a);
        for(GraphNode<Integer> node : map.keySet()) {
            System.out.println(node.data + " = " + map.get(node));
        }
    }
}
