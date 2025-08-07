package com.mashibing.dailyPractice.round5;

import com.mashibing.dailyPractice.round4.Kruskal_0805;
import com.mashibing.graph.Edge;
import com.mashibing.graph.Graph;
import com.mashibing.graph.GraphNode;

import java.util.*;

/**
 * 给定一个图graph，每一条边都有权重，在保证图中各节点连通性不变的情况下，返回权重总和最小的方案有哪些边，将这些边放到一个结集合里返回。
 */
public class Kruskal_0815 {
    private Map<GraphNode<Integer>, GraphNode<Integer>> parentMap = new HashMap<>();
    private Map<GraphNode<Integer>, Integer> sizeMap = new HashMap<>();

    public Kruskal_0815(Collection<GraphNode<Integer>> nodes) {
        if(nodes == null || nodes.size() == 0) {
            return;
        }

        for (GraphNode<Integer> node : nodes) {
            parentMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    private GraphNode<Integer> findAnc(GraphNode<Integer> node) {
        Stack<GraphNode<Integer>> stack = new Stack<>();
        while (parentMap.get(node) != node) {
            stack.add(node);
            node = parentMap.get(node);
        }

        while (!stack.isEmpty()) {
            parentMap.put(stack.pop(), node);
        }

        return node;
    }

    public boolean isSameSet(GraphNode<Integer> node1, GraphNode<Integer> node2) {
        return findAnc(node1) == findAnc(node2);
    }

    public void union(GraphNode<Integer> node1, GraphNode<Integer> node2) {
        if(isSameSet(node1, node2)) {
            return;
        }

        GraphNode<Integer> anc1 = findAnc(node1);
        GraphNode<Integer> anc2 = findAnc(node2);
        int size1 = sizeMap.get(anc1);
        int size2 = sizeMap.get(anc2);
        GraphNode<Integer> longAnc = size1 >= size2 ? anc1 : anc2;
        GraphNode<Integer> shortAnc = longAnc == anc1 ? anc2 : anc1;
        parentMap.put(shortAnc, longAnc);
        sizeMap.put(longAnc, size1 + size2);
        sizeMap.remove(shortAnc);
    }

    public static Set<Edge<Integer>> kruskal(Graph<Integer> graph) {
        Set<Edge<Integer>> set = new HashSet<>();
        if(graph == null) {
            return set;
        }

        PriorityQueue<Edge<Integer>> heap = new PriorityQueue<>(new Comparator<Edge<Integer>>() {
            @Override
            public int compare(Edge<Integer> o1, Edge<Integer> o2) {
                return o1.weight - o2.weight;
            }
        });
        heap.addAll(graph.edges);

        Kruskal_0815 uf = new Kruskal_0815(graph.nodes.values());
        while (!heap.isEmpty()) {
            Edge<Integer> edge = heap.poll();
            GraphNode<Integer> from = edge.from;
            GraphNode<Integer> to = edge.to;
            if(uf.isSameSet(from, to)) {
                continue;
            }

            uf.union(from, to);
            set.add(edge);
        }

        return set;
    }

    // 老师代码
    public static class UnionFind {
        // key 某一个节点， value key节点往上的节点
        private HashMap<GraphNode, GraphNode> fatherMap;
        // key 某一个集合的代表节点, value key所在集合的节点个数
        private HashMap<GraphNode, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<GraphNode, GraphNode>();
            sizeMap = new HashMap<GraphNode, Integer>();
        }

        public void makeSets(Collection<GraphNode<Integer>> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (GraphNode node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private GraphNode findFather(GraphNode n) {
            Stack<GraphNode> path = new Stack<>();
            while(n != fatherMap.get(n)) {
                path.add(n);
                n = fatherMap.get(n);
            }
            while(!path.isEmpty()) {
                fatherMap.put(path.pop(), n);
            }
            return n;
        }

        public boolean isSameSet(GraphNode a, GraphNode b) {
            return findFather(a) == findFather(b);
        }

        public void union(GraphNode a, GraphNode b) {
            if (a == null || b == null) {
                return;
            }
            GraphNode aDai = findFather(a);
            GraphNode bDai = findFather(b);
            if (aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }


    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public static Set<Edge> kruskalMST(Graph<Integer> graph) {
        Kruskal_0805.UnionFind unionFind = new Kruskal_0805.UnionFind();
        unionFind.makeSets(graph.nodes.values());
        // 从小的边到大的边，依次弹出，小根堆！
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Kruskal_0805.EdgeComparator());
        Set edges = graph.edges;
        for (Edge edge : graph.edges) { // M 条边
            priorityQueue.add(edge);  // O(logM)
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) { // M 条边
            Edge edge = priorityQueue.poll(); // O(logM)
            if (!unionFind.isSameSet(edge.from, edge.to)) { // O(1)
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GraphNode<Integer> a = new GraphNode<>(1);
        GraphNode<Integer> b = new GraphNode<>(2);
        GraphNode<Integer> c = new GraphNode<>(3);
        GraphNode<Integer> d = new GraphNode<>(4);
        GraphNode<Integer> e = new GraphNode<>(5);
        GraphNode<Integer> f = new GraphNode<>(6);
        GraphNode<Integer> g = new GraphNode<>(7);
        a.nexts.add(b);
        a.nexts.add(c);
        a.nexts.add(d);
        b.nexts.add(e);
        c.nexts.add(b);
        c.nexts.add(f);
        d.nexts.add(c);
        d.nexts.add(g);

        Graph<Integer> graph = new Graph<>();
        graph.nodes.put(1, a);
        graph.nodes.put(2, b);
        graph.nodes.put(3, c);
        graph.nodes.put(4, d);
        graph.nodes.put(5, e);
        graph.nodes.put(6, f);
        graph.nodes.put(7, g);

        graph.edges.add(new Edge<>(30, a, b));
        graph.edges.add(new Edge<>(10, a, c));
        graph.edges.add(new Edge<>(2, a, d));
        graph.edges.add(new Edge<>(5, c, b));
        graph.edges.add(new Edge<>(3, d, c));
        graph.edges.add(new Edge<>(4, b, e));
        graph.edges.add(new Edge<>(5, c, f));
        graph.edges.add(new Edge<>(6, d, g));

        Set<Edge> edgeSet1 = kruskalMST(graph);
        System.out.println("correct ans:" + edgeSet1);
        Set<Edge<Integer>> edgeSet2 = kruskal(graph);
        System.out.println("my ans:" + edgeSet2);
    }
}
