package com.mashibing.dailyPractice.round2._71_to_100._0711;

import com.mashibing.graph.Edge;
import com.mashibing.graph.GraphNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 迪瑞克撕拉算法：
 * 给定一个有向图的起始节点a，边的权重为非负数，返回一个哈希表，这个哈希表描述的是从起点a出发，
 * a能到达的每一个节点的最小距离（自己到自己的距离是0），如果从a出发不能到达的点则不要放到哈希表里。
 */
public class Dijkstra_0711 {
    public static Map<GraphNode<String>, Integer> dijkstra(GraphNode<String> node) {
        Map<GraphNode<String>, Integer> map = new HashMap<>();
        if(node == null) {
            return map;
        }

        map.put(node, 0);
        Set<GraphNode<String>> set = new HashSet<>();
        GraphNode<String> minDisNode = findMinDisNode(map, set);
        while (minDisNode != null) {
            int preDis = map.get(minDisNode);
            for (Edge<String> edge : minDisNode.edges) {
                GraphNode<String> toNode = edge.to;
                if(map.containsKey(toNode) && map.get(toNode) <= edge.weight + preDis) {
                    continue;
                }

                map.put(toNode, edge.weight + preDis);
            }

            set.add(minDisNode);
            minDisNode = findMinDisNode(map, set);
        }

        return map;
    }

    private static GraphNode<String> findMinDisNode(Map<GraphNode<String>, Integer> map, Set<GraphNode<String>> set) {
        int minDis = Integer.MAX_VALUE;
        GraphNode<String> minNode = null;
        for (GraphNode<String> node : map.keySet()) {
            if(!set.contains(node) && map.get(node) <= minDis) {
                minDis = map.get(node);
                minNode = node;
            }
        }

        return minNode;
    }

    // 老师的代码
    public static HashMap<GraphNode<String>, Integer> dijkstra1(GraphNode<String> from) {
        HashMap<GraphNode<String>, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        // 打过对号的点
        HashSet<GraphNode<String>> selectedNodes = new HashSet<>();
        GraphNode<String> minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            //  原始点  ->  minNode(跳转点)   最小距离distance
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                GraphNode<String> toNode = edge.to;
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

    public static GraphNode<String> getMinDistanceAndUnselectedNode(HashMap<GraphNode<String>, Integer> distanceMap, HashSet<GraphNode<String>> touchedNodes) {
        GraphNode<String> minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<GraphNode<String>, Integer> entry : distanceMap.entrySet()) {
            GraphNode<String> node = entry.getKey();
            int distance = entry.getValue();
            if (!touchedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
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
        System.out.println("my ans:");
        for(GraphNode<String> node : map.keySet()) {
            System.out.println(node.data + " = " + map.get(node));
        }

        map = dijkstra1(a);
        System.out.println("correct ans:");
        for(GraphNode<String> node : map.keySet()) {
            System.out.println(node.data + " = " + map.get(node));
        }
    }
}
