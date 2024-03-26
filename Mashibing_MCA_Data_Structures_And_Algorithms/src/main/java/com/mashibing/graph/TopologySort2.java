package com.mashibing.graph;

import java.util.*;

/**
 * 拓扑序（2）
 * 这里要说明一个问题：图这种数据结构和其它数据结构不要一样，它有好多种表达方式，用一维数组、二维数组、列表都可以表示图。
 * 对于刚接触图这种数据结构的时候，由于对图玩儿得不是很溜，可以找一个自己熟悉的方式来表达图，对于题目给出的其它表达方式，自己写一个转换方法，
 * 将题目的表达方式转换成自己熟悉的表达方式然后再开始做题，如果对图玩儿的特别溜就可以直接拿题目给的方式来做题。
 * 常见的图表达方式有邻接表法和邻接矩法，自己熟悉的方式可以是任意形式，比如com.mashibing.graph.Graph、com.mashibing.graph.GraphNode、
 * com.mashibing.graph.Edge就是常见的适合于新手理解的表达方式，如果题目给出邻接表，可以将邻接表准换成那三个类然后再做题。
 * 对于本类来说，就是见识一下题目给出一种类似于邻接表的方式最终让你来输出拓扑序，这个题目是leetcode原题，地址：https://www.lintcode.com/problem/127/
 *
 * 思路：
 * 1、在图中任意找到两个点X和Y，如果从X出发走完后续所有节点所经历的节点个数为num1（同一个节点多次被经过要重复计算数量（即点次），下同），
 * 从Y出发走完后续所有节点所经历的节点个数为num2，如果num1>=num2，就可以认为在最终的拓扑序中X一定排在Y的前面，我们就说X的拓扑序小于等于Y的拓扑序。
 * 2、准备一个Map<Node, Integer>当做缓存，key为节点，value为：把这个节点当做起点，遍历完后面所有节点后所经历过的节点数（点次），
 * 这样就把每一个节点和其点次都放入缓存中，需要的时候可以直接取。
 *
 * 课程：体系班课时146
 */
public class TopologySort2 {
    /**
     * ！！！这个类是lintcode（不是leetcode）题目给出的类，只能用不能改。！！！
     * 这个类就是题目给出的表达图的方式，类似于邻接表
     */
    static class DirectedGraphNode {
        public int label;// 节点本身的值，类似于邻接表中的key
        public ArrayList<DirectedGraphNode> neighbors;// 该节点的邻居节点，类似于邻接表中的value

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    /**
     * 辅助类，用于记录某个节点及遍历该节点后续节点的点次
     * 这个类是自定义的，非题目给定
     */
    class Record {
        public DirectedGraphNode node;
//        public int nodeCount;// 该节点的点次
        public long nodeCount;// 该节点的点次，由于lintcode测试数据数量偏大，会超出int的范围，所以这里改为long

        public Record(DirectedGraphNode node, long nodeCount) {
            this.node = node;
            this.nodeCount = nodeCount;
        }
    }

    /**
     * 输出拓扑序
     * ！！！该方法也是题目给出的，你只能在方法里面写实现，不能对方法本身的定义（出参、入参、方法名）做修改！！！
     * @param graph
     * @return
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 可以将题目给出的结构转换为自己熟悉的那三个类，这里就不转换了，直接用原始入参做

        ArrayList<DirectedGraphNode> retList = new ArrayList<>();
        if(graph == null || graph.size() == 0) {
            return retList;
        }

        // 用作缓存
        Map<DirectedGraphNode, Record> nodeCountMap = new HashMap<>();
        // 把所有节点的点次都加入到缓存里
        for(DirectedGraphNode node : graph) {
            recurse(node, nodeCountMap);
        }

        // 将缓存里的所有Record放入列表中用于排序
        List<Record> recordList = new ArrayList<>();
        for(Record record : nodeCountMap.values()) {
            recordList.add(record);
        }

        recordList.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
//                return o2.nodeCount - o1.nodeCount;// 按照点次倒叙排序，点次越大的在拓扑序中越靠前（拓扑序越小）
                return o1.nodeCount == o2.nodeCount ? 0 : (o1.nodeCount > o2.nodeCount ? -1 : 1);
            }
        });

        for(Record record : recordList) {
            retList.add(record.node);
        }

        return retList;
    }


    public Record recurse(DirectedGraphNode node, Map<DirectedGraphNode, Record> nodeCountMap) {
        // base case
        if(nodeCountMap.containsKey(node)) {// 如果缓存里面有该节点，说明之前遍历过，从缓存直接取
            return nodeCountMap.get(node);
        }

        long nodeCount = 0;// 统计自己所有邻居的点次
        for(DirectedGraphNode neighbor : node.neighbors) {
            nodeCount += recurse(neighbor, nodeCountMap).nodeCount;
        }

        Record curRecord = new Record(node, nodeCount + 1);// 加的这个1就是自己，自己本身算一个点次
        nodeCountMap.put(node, curRecord);// 放入缓存

        return curRecord;
    }

    public static void main(String[] args) {
//        DirectedGraphNode n1 = new DirectedGraphNode(1);
//        DirectedGraphNode n2 = new DirectedGraphNode(2);
//        DirectedGraphNode n3 = new DirectedGraphNode(3);
//        DirectedGraphNode n4 = new DirectedGraphNode(4);
//        DirectedGraphNode n5 = new DirectedGraphNode(5);
//        DirectedGraphNode n6 = new DirectedGraphNode(6);
//        DirectedGraphNode n7 = new DirectedGraphNode(7);
//        n1.neighbors.add(n2);
//        n7.neighbors.add(n2);
//        n2.neighbors.add(n3);
//        n3.neighbors.add(n4);
//        n3.neighbors.add(n5);
//        n5.neighbors.add(n6);
//
//        ArrayList<DirectedGraphNode> list = new ArrayList<>();
//        list.add(n1);
//        list.add(n2);
//        list.add(n3);
//        list.add(n4);
//        list.add(n5);
//        list.add(n6);
//        list.add(n7);
//
//        ArrayList<DirectedGraphNode> directedGraphNodes = topSort(list);
//        for(DirectedGraphNode node : directedGraphNodes) {
//            System.out.print(node.label + "\t");
//        }
//        System.out.println();
    }
}
