package com.mashibing.dailyPractice.round1._81_to_90;

import java.util.*;

/**
 * 图的拓扑排序（按点深度）
 */
public class TopologySort3_0321 {
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
        private int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            recurse(node, map);
        }

        List<Record> recordList = new ArrayList<>(map.values());
        recordList.sort(new Comparator<Record>() {
            public int compare(Record r1, Record r2) {
                return r2.deep - r1.deep;
            }
        });

        ArrayList<DirectedGraphNode> retList = new ArrayList<>();
        for (Record r : recordList) {
            retList.add(r.node);
        }

        return retList;
    }

    private Record recurse(DirectedGraphNode x, Map<DirectedGraphNode, Record> map) {
        if (map.containsKey(x)) {
            return map.get(x);
        }

        int deep = 0;
        for (DirectedGraphNode neighbor : x.neighbors) {
            deep = Math.max(recurse(neighbor, map).deep, deep);
        }

        Record record = new Record(x, deep + 1);
        map.put(x, record);
        return record;
    }
}
