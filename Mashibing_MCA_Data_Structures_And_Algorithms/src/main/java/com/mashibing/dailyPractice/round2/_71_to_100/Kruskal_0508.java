package com.mashibing.dailyPractice.round2._71_to_100;

import com.mashibing.graph.Edge;
import com.mashibing.graph.Graph;
import com.mashibing.graph.GraphNode;

import java.util.*;

/**
 * 给定一个图graph，每一条边都有权重，在保证图中各节点连通性不变的情况下，返回权重总和最小的方案有哪些边，将这些边放到一个结集合里返回。
 */
public class Kruskal_0508 {
    static class KruskalNode_0508 {
        private GraphNode<String> value;

        public KruskalNode_0508(GraphNode<String> value) {
            this.value = value;
        }
    }

    private Map<GraphNode<String>, KruskalNode_0508> nodeMap = new HashMap<>();
    private Map<KruskalNode_0508, KruskalNode_0508> parentMap = new HashMap<>();
    private Map<KruskalNode_0508, Integer> sizeMap = new HashMap<>();

    public Kruskal_0508() {
    }

    public Kruskal_0508(Collection<GraphNode<String>> graphNodeList) {
        if(graphNodeList == null || graphNodeList.isEmpty()) {
            return;
        }

        for (GraphNode<String> graphNode : graphNodeList) {
            KruskalNode_0508 kruskalNode_0508 = new KruskalNode_0508(graphNode);
            nodeMap.put(graphNode, kruskalNode_0508);
            parentMap.put(kruskalNode_0508, kruskalNode_0508);
            sizeMap.put(kruskalNode_0508, 1);
        }
    }

    private boolean isSameSet(GraphNode node1, GraphNode node2) {
        return findAnc(nodeMap.get(node1)) == findAnc(nodeMap.get(node2));
    }

    private KruskalNode_0508 findAnc(KruskalNode_0508 node) {
        Stack<KruskalNode_0508> stack = new Stack<>();
        while(parentMap.get(node) != node) {
            node = parentMap.get(node);
            stack.add(node);
        }

        while(!stack.isEmpty()) {
            parentMap.put(stack.pop(), node);
        }

        return node;
    }

    private void union(GraphNode node1, GraphNode node2) {
        if(isSameSet(node1, node2)) {
            return;
        }

        KruskalNode_0508 anc1 = findAnc(nodeMap.get(node1));
        KruskalNode_0508 anc2 = findAnc(nodeMap.get(node2));
        int size1 = sizeMap.get(anc1);
        int size2 = sizeMap.get(anc2);
        KruskalNode_0508 longAnc = size1 >= size2 ? anc1 : anc2;
        KruskalNode_0508 shortAnc = longAnc == anc1 ? anc2 : anc1;
        parentMap.put(shortAnc, longAnc);
        sizeMap.put(longAnc, size1 + size2);
        sizeMap.remove(shortAnc);
    }

    public static Set<Edge> kruskal(Graph<String> graph) {
        Set<Edge> edgeSet = new HashSet<>();
        if(graph == null || graph.nodes.isEmpty() || graph.edges.isEmpty()) {
            return edgeSet;
        }

        PriorityQueue<Edge> heap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        for (Edge edge: graph.edges) {
            heap.add(edge);
        }

        Kruskal_0508 uf = new Kruskal_0508(graph.nodes.values());
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            if(uf.isSameSet(edge.from, edge.to)) {
                continue;
            }

            uf.union(edge.from, edge.to);
            edgeSet.add(edge);
        }

        return edgeSet;
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

        public void makeSets(Collection<GraphNode<String>> nodes) {
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

    public static Set<Edge> kruskalMST(Graph<String> graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        // 从小的边到大的边，依次弹出，小根堆！
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
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

        edgeSet = kruskalMST(graph);
        System.out.println("correct ans:" + edgeSet);
    }
}
