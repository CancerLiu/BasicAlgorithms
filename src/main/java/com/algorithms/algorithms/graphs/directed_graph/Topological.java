package com.algorithms.algorithms.graphs.directed_graph;

/**
 * 拓扑排序————拓扑排序就是有向图的逆后续排序
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Directedgraph G) {
        DirectCycle cycle = new DirectCycle(G);
        if (!cycle.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
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
