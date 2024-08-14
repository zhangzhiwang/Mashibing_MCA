package com.mashibing.dailyPractice.round2._71_to_100._0708;

import java.util.*;

/**
 * 图的拓扑排序
 */
public class TopologySort3_0712 {
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    static class Record {
        private DirectedGraphNode node;
        private int depth;

        public Record(DirectedGraphNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if (graph == null || graph.isEmpty()) {
            return graph;
        }

        Map<DirectedGraphNode, Record> map = new HashMap<>();
        List<Record> list = new ArrayList<>();
        for (DirectedGraphNode node : graph) {
            list.add(recurse(node, map));
        }

        list.sort(new Comparator<Record>() {
            public int compare(Record r1, Record r2) {
                return r2.depth - r1.depth;
            }
        });

        ArrayList<DirectedGraphNode> resList = new ArrayList<>();
        for (Record record : list) {
            resList.add(record.node);
        }

        return resList;
    }

    private Record recurse(DirectedGraphNode x, Map<DirectedGraphNode, Record> map) {
        if (map.containsKey(x)) {
            return map.get(x);
        }

        int maxDepth = 0;
        for (DirectedGraphNode node : x.neighbors) {
            maxDepth = Math.max(maxDepth, recurse(node, map).depth);
        }

        Record record = new Record(x, maxDepth + 1);
        map.put(x, record);
        return record;
    }

    public static void main(String[] args) {
        // https://www.lintcode.com/problem/127/
    }
}
