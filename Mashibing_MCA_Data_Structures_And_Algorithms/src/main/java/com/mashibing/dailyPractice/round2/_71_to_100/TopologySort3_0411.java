package com.mashibing.dailyPractice.round2._71_to_100;

import com.mashibing.dailyPractice.round1._81_to_90.TopologySort3_0321;

import java.net.DatagramPacket;
import java.util.*;

/**
 * 图的拓扑排序
 * 
 * @Auth zhangzhiwang
 * @Date 2024/4/11 08:44
 */
public class TopologySort3_0411 {
    /**
     * lintcode（不是leetcode）
     */
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    class Record {
        private DirectedGraphNode node;
        private int depth;

        public Record(DirectedGraphNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> retList = new ArrayList<>();
        if(graph == null || graph.isEmpty()) {
            return retList;
        }

        List<Record> recordList = new ArrayList<>();
        Map<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            Record record = f(node, map);
            recordList.add(record);
        }

        recordList.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o2.depth - o1.depth;
            }
        });

        for (Record record : recordList) {
            retList.add(record.node);
        }

        return retList;
    }

    private Record f(DirectedGraphNode cur, Map<DirectedGraphNode, Record> map) {
        if(map.containsKey(cur)) {
            return map.get(cur);
        }

        int depth = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            depth = Math.max(f(next, map).depth, depth);
        }

        Record record = new Record(cur, depth + 1);
        map.put(cur, record);
        return record;
    }

    public static void main(String[] args) {
        TopologySort3_0411 topologySort3_0411 = new TopologySort3_0411();
        TopologySort3_0411.DirectedGraphNode n0 = topologySort3_0411.new DirectedGraphNode(0);
        TopologySort3_0411.DirectedGraphNode n1 = topologySort3_0411.new DirectedGraphNode(1);
        TopologySort3_0411.DirectedGraphNode n2 = topologySort3_0411.new DirectedGraphNode(2);
        TopologySort3_0411.DirectedGraphNode n3 = topologySort3_0411.new DirectedGraphNode(3);
        TopologySort3_0411.DirectedGraphNode n4 = topologySort3_0411.new DirectedGraphNode(4);
//        TopologySort3_0411.DirectedGraphNode n5 = topologySort3_0411.new DirectedGraphNode(5);
        n0.neighbors = Arrays.<TopologySort3_0411.DirectedGraphNode>asList(n2, n3);
        n1.neighbors = Arrays.<TopologySort3_0411.DirectedGraphNode>asList(n0, n2, n3);
//        n2.neighbors = Arrays.<TopologySort3_0411.DirectedGraphNode>asList(n4, n5);
        n3.neighbors = Arrays.<TopologySort3_0411.DirectedGraphNode>asList(n2);
        n4.neighbors = Arrays.<TopologySort3_0411.DirectedGraphNode>asList(n1, n2, n3);

        ArrayList<DirectedGraphNode> list = new ArrayList<>();
        list.add(n0);
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
//        list.add(n5);

        ArrayList<DirectedGraphNode> resultList = topologySort3_0411.topSort(list);
        for (DirectedGraphNode node :
                resultList) {
            System.out.print(node.label + "\t");
        }
        System.out.println();
    }
}
