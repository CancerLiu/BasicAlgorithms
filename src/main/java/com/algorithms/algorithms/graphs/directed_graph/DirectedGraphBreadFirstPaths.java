package com.algorithms.algorithms.graphs.directed_graph;

import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.Stack;

public class DirectedGraphBreadFirstPaths extends AbstractDirectedGraphSearchPaths {

    public DirectedGraphBreadFirstPaths(Directedgraph G, int s) {
        super.marked = new boolean[G.V()];
        super.edgeTo = new int[G.V()];
        super.s = s;
        bfs(G, s);
    }

    private void bfs(Directedgraph G, int s) {
        LinkedQueue<Integer> queue = new LinkedQueue();

        marked[s] = true;
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                marked[w] = true;
                edgeTo[w] = v;
                queue.enqueue(w);
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
