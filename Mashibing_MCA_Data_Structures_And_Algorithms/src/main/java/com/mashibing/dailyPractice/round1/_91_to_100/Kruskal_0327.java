package com.mashibing.dailyPractice.round1._91_to_100;

import com.mashibing.graph.Edge;
import com.mashibing.graph.Graph;
import com.mashibing.graph.GraphNode;

import java.util.*;

/**
 * 给定一个图graph，每一条边都有权重，在保证图中各节点连通性不变的情况下，返回权重总和最小的方案有哪些边，将这些边放到一个结集合里返回。
 */
public class Kruskal_0327 {
    class KruskalUnionFind_0327 {
        private Map<GraphNode, GraphNode> parentMap = new HashMap<>();
        private Map<GraphNode, Integer> sizeMap = new HashMap<>();

        public KruskalUnionFind_0327(Collection<GraphNode> graphNodes) {
            if(graphNodes == null || graphNodes.size() == 0) {
                return;
            }

            for (GraphNode graphNode : graphNodes) {
                parentMap.put(graphNode, graphNode);
                sizeMap.put(graphNode, 1);
            }
        }

        private GraphNode findRepr(GraphNode node) {
            Stack<GraphNode> stack = new Stack<>();
            while (parentMap.get(node) != node) {
                stack.add(node);
                node = parentMap.get(node);
            }

            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(GraphNode node1, GraphNode node2) {
            return findRepr(node1) == findRepr(node2);
        }

        public void union(GraphNode node1, GraphNode node2) {
            if(isSameSet(node1, node2)) {
                return;
            }

            GraphNode r1 = findRepr(node1);
            GraphNode r2 = findRepr(node2);
            int size1 = sizeMap.get(r1);
            int size2 = sizeMap.get(r2);
            GraphNode longR = size1 >= size2 ? r1 : r2;
            GraphNode shortR = longR == r1 ? r2 : r1;
            parentMap.put(shortR, longR);
            sizeMap.put(longR, size1 + size2);
            sizeMap.remove(shortR);
        }
    }

    public Set<Edge> kruskal(Graph graph) {
        Set<Edge> set = new HashSet<>();
        if(graph == null) {
            return set;
        }

        PriorityQueue<Edge> heap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        Set<Edge> edges = graph.edges;
        for (Edge edge : edges) {
            heap.add(edge);
        }

        KruskalUnionFind_0327 uf = new KruskalUnionFind_0327(graph.nodes.values());
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            GraphNode from = edge.from;
            GraphNode to = edge.to;
            if(uf.isSameSet(from, to)) {
                continue;
            }

            uf.union(from, to);
            set.add(edge);
        }

        return set;
    }

    public static void main(String[] args) {
        GraphNode<String> a = new GraphNode<>("1");
        GraphNode<String> b = new GraphNode<>("2");
        GraphNode<String> c = new GraphNode<>("3");
        GraphNode<String> d = new GraphNode<>("4");
        GraphNode<String> e = new GraphNode<>("5");
        GraphNode<String> f = new GraphNode<>("6");
        GraphNode<String> g = new GraphNode<>("7");
        a.nexts.add(b);
        a.nexts.add(c);
        a.nexts.add(d);
        b.nexts.add(e);
        c.nexts.add(b);
        c.nexts.add(f);
        d.nexts.add(c);
        d.nexts.add(g);

        Graph<String> graph = new Graph<>();
        graph.nodes.put("1", a);
        graph.nodes.put("2", b);
        graph.nodes.put("3", c);
        graph.nodes.put("4", d);
        graph.nodes.put("5", e);
        graph.nodes.put("6", f);
        graph.nodes.put("7", g);

        graph.edges.add(new Edge<>(30, a, b));
        graph.edges.add(new Edge<>(10, a, c));
        graph.edges.add(new Edge<>(2, a, d));
        graph.edges.add(new Edge<>(5, c, b));
        graph.edges.add(new Edge<>(3, d, c));
        graph.edges.add(new Edge<>(4, b, e));
        graph.edges.add(new Edge<>(5, c, f));
        graph.edges.add(new Edge<>(6, d, g));

        Set<Edge> edgeSet = new Kruskal_0327().kruskal(graph);
        System.out.println(edgeSet);
    }
}
