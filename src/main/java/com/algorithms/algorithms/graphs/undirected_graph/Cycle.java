package com.algorithms.algorithms.graphs.undirected_graph;

/**
 * 用于检测无向图中是否有环存在
 * <p>
 * 主要思想是,如果(下一个)被标记的邻接顶点不是上一个找到自己的顶点,则说明有环。
 */
public class Cycle {

    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            dfs(G, v, v);
        }
    }

    /**
     * @param G 待处理的图
     * @param v 当前结点
     * @param u 前一个结点
     */
    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w, v);
            //如果没有环,则根本找不到下一个顶点,所以当前结点和前一个结点应该相同
            else if (w != u) hasCycle = true;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
