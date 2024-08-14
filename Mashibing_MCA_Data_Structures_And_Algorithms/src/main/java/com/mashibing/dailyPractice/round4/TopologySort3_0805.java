package com.mashibing.dailyPractice.round4;

import java.util.*;

/**
 * 图的拓扑排序
 */
public class TopologySort3_0805 {
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
        if(graph == null || graph.size() == 0) {
            return graph;
        }

        Map<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            map.put(node, recurse(node, map));
        }

        List<Record> list = new ArrayList<>(map.values());
        list.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o2.depth - o1.depth;
            }
        });

        ArrayList<DirectedGraphNode> retList = new ArrayList<>();
        for (Record record : list) {
            retList.add(record.node);
        }

        return retList;
    }

    private Record recurse(DirectedGraphNode node, Map<DirectedGraphNode, Record> map) {
        if(map.containsKey(node)) {
            return map.get(node);
        }

        int maxDepth = 0;
        for (DirectedGraphNode neighbor : node.neighbors) {
            maxDepth = Math.max(maxDepth, recurse(neighbor, map).depth);
        }

        Record record = new Record(node, maxDepth + 1);
        map.put(node, record);
        return record;
    }

    public static void main(String[] args) {
        // lintcode:https://www.lintcode.com/problem/127/
    }
}
