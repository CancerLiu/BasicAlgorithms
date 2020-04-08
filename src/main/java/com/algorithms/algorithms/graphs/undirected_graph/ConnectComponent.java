package com.algorithms.algorithms.graphs.undirected_graph;

/**
 * 找到一幅图中所有的连通分量,此处使用的是深度优先搜索
 */
public class ConnectComponent {

    private boolean[] marked;
    /**
     * 同一个连通分量中的顶点的值一样
     */
    private int[] id;

    /**
     * 一共有多少个连通分量
     */
    private int count;

    public ConnectComponent(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(G, i);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }
}
