package com.algorithms.algorithms.graphs.undirected_graph;

import edu.princeton.cs.algs4.Stack;

/**
 * 深度优先搜索，核心方法dfs(...)使用了递归
 */
public class GraphDepthFirstPaths extends AbstractGraphSearchPaths {

    public GraphDepthFirstPaths(Graph G, int s) {
        super.edgeTo = new int[G.V()];
        super.marked = new boolean[G.V()];
        super.s = s;
        dfs(G, s);

    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                //表示到w之前的最后一个顶点是v
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * 从起点s能否到达v
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 从起点s到达v的路径集合
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) stack.push(x);

        stack.push(s);

        return stack;
    }
}

