package com.algorithms.algorithms.graphs.weighted_directed_graph;

import com.algorithms.algorithms.graphs.directed_graph.DirectCycle;

public class DirectedTopological {

    private Iterable<Integer> order;

    public DirectedTopological(EdgeWeigtedDirectedgraph G) {
        DirectCycle cycle = new DirectCycle(G);
        if (!cycle.hasCycle()) {
            DirectedDepthFirstOrder dfs = new DirectedDepthFirstOrder(G);
            order = dfs.getReversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
