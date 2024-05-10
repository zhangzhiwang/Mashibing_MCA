package com.mashibing.dailyPractice.round2._71_to_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的拓扑排序
 */
public class TopologySort3_0508 {
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

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        return null;
    }
}
