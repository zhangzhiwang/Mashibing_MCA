package com.mashibing.graph;

import java.util.*;

/**
 * 拓扑序（3）
 * 本类是对拓扑序（2）（com.mashibing.graph.TopologySort2）的另一种实现，在（2）中是根据点次来判断的，在（3）中是根据深度来判断的。
 * 思路：
 * 和（2）基本一致：
 * 1、在图中任意找到两个点X和Y，如果从X出发走完后续所有节点所走过的深度为d1（深度就是经过的节点个数），从Y出发走完后续所有节点所经历的深度为d2，
 * 如果d1>=d2，就可以认为在最终的拓扑序中X一定排在Y的前面，即X的拓扑序小于等于Y的拓扑序。
 * 2、准备一个Map<Node, Integer>当做缓存，key为节点，value为：把这个节点当做起点，遍历完后面所有节点后所经历过的深度，
 * 这样就把每一个节点和其深度都放入缓存中，需要的时候可以直接取。
 *
 * 课程：体系班课时147
 */
public class TopologySort3 {
    static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    /**
     * 辅助类，用于记录某个节点及遍历该节点后续节点的深度
     * 这个类是自定义的，非题目给定
     */
    static class Record {
        public DirectedGraphNode node;
        public int deep;// 该节点的深度

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    /**
     * 输出拓扑序
     * ！！！该方法也是题目给出的，你只能在方法里面写实现，不能对方法本身的定义（出参、入参、方法名）做修改！！！
     * @param graph
     * @return
     */
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 可以将题目给出的结构转换为自己熟悉的那三个类，这里就不转换了，直接用原始入参做

        ArrayList<DirectedGraphNode> retList = new ArrayList<>();
        if(graph == null || graph.size() == 0) {
            return retList;
        }

        // 用作缓存
        Map<DirectedGraphNode, Record> nodeCountMap = new HashMap<>();
        // 把所有节点的深度都加入到缓存里
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
                return o2.deep - o1.deep;// 按照深度倒叙排序，深度越大的在拓扑序中越靠前（拓扑序越小）
            }
        });

        for(Record record : recordList) {
            retList.add(record.node);
        }

        return retList;
    }


    public static Record recurse(DirectedGraphNode node, Map<DirectedGraphNode, Record> nodeCountMap) {
        // base case：base case不可能有node是null的情况
        if(nodeCountMap.containsKey(node)) {// 如果缓存里面有该节点，说明之前遍历过，从缓存直接取
            return nodeCountMap.get(node);
        }

        int maxDeep = 0;
        for(DirectedGraphNode neighbor : node.neighbors) {
            // 如果用deep来统计的话，那就取当前节点所有邻居深度的最大值
            maxDeep = Math.max(maxDeep, recurse(neighbor, nodeCountMap).deep);
        }

        Record curRecord = new Record(node, maxDeep + 1);// 自己的深度加1
        nodeCountMap.put(node, curRecord);// 放入缓存

        return curRecord;
    }

    public static void main(String[] args) {
        DirectedGraphNode n1 = new DirectedGraphNode(1);
        DirectedGraphNode n2 = new DirectedGraphNode(2);
        DirectedGraphNode n3 = new DirectedGraphNode(3);
        DirectedGraphNode n4 = new DirectedGraphNode(4);
        DirectedGraphNode n5 = new DirectedGraphNode(5);
        DirectedGraphNode n6 = new DirectedGraphNode(6);
        DirectedGraphNode n7 = new DirectedGraphNode(7);
        n1.neighbors.add(n2);
        n7.neighbors.add(n2);
        n2.neighbors.add(n3);
        n3.neighbors.add(n4);
        n3.neighbors.add(n5);
        n5.neighbors.add(n6);

        ArrayList<DirectedGraphNode> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
        list.add(n5);
        list.add(n6);
        list.add(n7);

        ArrayList<DirectedGraphNode> directedGraphNodes = topSort(list);
        for(DirectedGraphNode node : directedGraphNodes) {
            System.out.print(node.label + "\t");
        }
        System.out.println();
    }
}
