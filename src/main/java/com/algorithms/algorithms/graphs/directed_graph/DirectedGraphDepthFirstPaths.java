package com.algorithms.algorithms.graphs.directed_graph;

import edu.princeton.cs.algs4.Stack;

public class DirectedGraphDepthFirstPaths extends AbstractDirectedGraphSearchPaths {

    public DirectedGraphDepthFirstPaths(Directedgraph G, int s) {
        super.marked = new boolean[G.V()];
        super.edgeTo = new int[G.V()];
        super.s = s;
        dfs(G, s);
    }

    private void dfs(Directedgraph G, int v) {
        marked[v] = true;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 到顶点v的路径集合
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }


}
