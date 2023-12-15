package com.mashibing.graph;

import com.mashibing.unionFind.UnionFind;

import java.util.*;

/**
 * 最小生成树实现方式1——K算法（Kruskal）
 * 给定一个图graph，每一条边都有权重，在保证图中各节点连通性不变的情况下，返回权重总和最小的方案有哪些边，将这些边放到一个结集合里返回。
 *
 * 思路：
 * 1、将所有边按照权重从小到大排序，可以使用小根堆实现排序（优先级队列）。
 * 2、使用并查集，最初每一个节点自己都是一个集合，然后每从小根堆中弹出一个边就判断它连接的两个节点是不是在同一个集合内，如果不是就union，并收集这条边用于返回
 * 如果是在一个集合内，说明这条边会形成环路，环路的边不要，跳过。等小根堆为空的时候，收集的边就是权重总和最小的方案。
 * 原理：当初边是按照权重从小到大排序的，由于使用小根堆保存，所以弹出的顺序一定是权重小的先弹出，一个集合内的点肯定是能联通的（可能直接联通也可能间接联通），
 * 如果一条边发现它连接的两个点已经在同一个集合内了，说明这条边一定是一个环路边，所以不能要。
 *
 * 课程：体系班课时148
 */
public class Kruskal {
    public static Set<Edge> kruskal(Graph<String> graph) {
        Set<Edge> retEdgeSet = new HashSet<>();
        if(graph == null) {
            return retEdgeSet;
        }

        // 将所有节点放入并查集
        UnionFind<GraphNode<String>> unionFind = new UnionFind<>(new ArrayList<>(graph.nodes.values()));
        // 将所有边放入小根堆
        PriorityQueue<Edge<String>> heap = new PriorityQueue<>(new Comparator<Edge<String>>() {
            @Override
            public int compare(Edge<String> o1, Edge<String> o2) {
                return o1.weight - o2.weight;
            }
        });
        for(Edge<String> edge : graph.edges) {
            heap.add(edge);
        }

        while(!heap.isEmpty()) {
            Edge<String> edge = heap.poll();
            if(unionFind.isSameSet(edge.from, edge.to)) {// 如果边连接的两个节点已经在同一个集合里了，直接跳过
                continue;
            }

            unionFind.union(edge.from, edge.to);// 在一个集合里面你的所有节点，一定是直接或者间接能联通的
            retEdgeSet.add(edge);
        }

        return retEdgeSet;
    }

    public static void main(String[] args) {
        GraphNode<String> a = new GraphNode<>("1");
        GraphNode<String> b = new GraphNode<>("2");
        GraphNode<String> c = new GraphNode<>("3");
        GraphNode<String> d = new GraphNode<>("4");
        GraphNode<String> e = new GraphNode<>("5");
        GraphNode<String> f = new GraphNode<>("6");
        a.nexts.add(b);
        a.nexts.add(e);
        b.nexts.add(c);
        c.nexts.add(d);
        c.nexts.add(e);
        e.nexts.add(d);
        e.nexts.add(f);
        d.nexts.add(f);

        Graph<String> graph = new Graph<>();
        graph.nodes.put("1", a);
        graph.nodes.put("2", b);
        graph.nodes.put("3", c);
        graph.nodes.put("4", d);
        graph.nodes.put("5", e);
        graph.nodes.put("6", f);

        graph.edges.add(new Edge<>(1, a, b));
        graph.edges.add(new Edge<>(1, d, f));
        graph.edges.add(new Edge<>(5, b, c));
        graph.edges.add(new Edge<>(4, c, d));
        graph.edges.add(new Edge<>(2, c, e));
        graph.edges.add(new Edge<>(3, e, d));
        graph.edges.add(new Edge<>(6, a, e));
        graph.edges.add(new Edge<>(5, e, f));

        Set<Edge> edgeSet = kruskal(graph);
        System.out.println(edgeSet);
    }
}
