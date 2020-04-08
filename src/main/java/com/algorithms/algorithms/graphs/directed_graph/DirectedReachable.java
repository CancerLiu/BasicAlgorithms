package com.algorithms.algorithms.graphs.directed_graph;

/**
 * 有向图的可达性问题,给出顶点或一堆顶点,找出这些顶点在该图中可达的顶点集合
 * <p>
 * 和无向图中的广度、深度搜索很像
 * <p>
 * 垃圾回收中的 标记-清除算法的底层和此类似
 */
public class DirectedReachable {

    private boolean marked[];

    public DirectedReachable(Directedgraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedReachable(Directedgraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (Integer s : sources) {
            if (marked[s]) dfs(G, s);
        }
    }

    private void dfs(Directedgraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    /**
     * 判断传入的顶点或顶点集是否和该顶点可达
     */
    public boolean marked(int v) {
        return marked[v];
    }

}
