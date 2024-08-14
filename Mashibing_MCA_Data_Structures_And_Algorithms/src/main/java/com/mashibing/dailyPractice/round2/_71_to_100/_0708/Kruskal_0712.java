package com.mashibing.dailyPractice.round2._71_to_100._0708;

import com.mashibing.dailyPractice.round2._71_to_100.Kruskal_0508;
import com.mashibing.graph.Edge;
import com.mashibing.graph.Graph;
import com.mashibing.graph.GraphNode;

import java.util.*;

/**
 * 最小生成树：
 * 给定一个图graph，每一条边都有权重，在保证图中各节点连通性不变的情况下，返回权重总和最小的方案有哪些边，将这些边放到一个结集合里返回。
 */
public class Kruskal_0712 {
    static class KruskalNode_0712 {
        private GraphNode node;

        public KruskalNode_0712(GraphNode node) {
            this.node = node;
        }
    }

    private Map<GraphNode, KruskalNode_0712> nodeMap = new HashMap<>();
    private Map<KruskalNode_0712, KruskalNode_0712> parentMap = new HashMap<>();
    private Map<KruskalNode_0712, Integer> sizeMap = new HashMap<>();

    public Kruskal_0712(Collection<GraphNode> nodeList) {
        for (GraphNode node : nodeList) {
            KruskalNode_0712 kruskalNode = new KruskalNode_0712(node);
            nodeMap.put(node, kruskalNode);
            parentMap.put(kruskalNode, kruskalNode);
            sizeMap.put(kruskalNode, 1);
        }
    }

    private boolean isSameSet(GraphNode node1, GraphNode node2) {
        return findAnc(nodeMap.get(node1)) == findAnc(nodeMap.get(node2));
    }

    private KruskalNode_0712 findAnc(KruskalNode_0712 node) {
        Stack<KruskalNode_0712> stack = new Stack<>();
        while (parentMap.get(node) != node) {
            stack.add(node);
            node = parentMap.get(node);
        }

        while(!stack.isEmpty()) {
            parentMap.put(stack.pop(), node);
        }

        return node;
    }

    public void union(GraphNode node1, GraphNode node2) {
        if(isSameSet(node1, node2)) {
            return;
        }

        KruskalNode_0712 anc1 = findAnc(nodeMap.get(node1));
        KruskalNode_0712 anc2 = findAnc(nodeMap.get(node2));
        int size1 = sizeMap.get(anc1);
        int size2 = sizeMap.get(anc2);
        KruskalNode_0712 longAnc = size1 >= size2 ? anc1 : anc2;
        KruskalNode_0712 shortAnc = longAnc == anc1 ? anc2 : anc1;
        parentMap.put(shortAnc, longAnc);
        sizeMap.put(longAnc, size1 + size2);
        sizeMap.remove(shortAnc);
    }

    public static Set<Edge> kruskal(Graph graph) {
        Set<Edge> edgeSet = new HashSet<>();
        if(graph == null) {
            return edgeSet;
        }

        PriorityQueue<Edge> heap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        heap.addAll(graph.edges);
        Kruskal_0712 uf = new Kruskal_0712(graph.nodes.values());
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            GraphNode from = edge.from;
            GraphNode to = edge.to;
            if(uf.isSameSet(from, to)) {
                continue;
            }

            uf.union(from, to);
            edgeSet.add(edge);
        }

        return edgeSet;
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

        Set<Edge> edgeSet = new Kruskal_0508().kruskal(graph);
        System.out.println("my ans:" + edgeSet);

        edgeSet = kruskal(graph);
        System.out.println("correct ans:" + edgeSet);
    }
}
