package com.algorithms.algorithms.graphs.undirected_graph;

import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.Stack;

/**
 * 广度优先搜索,核心方法bfs(...)使用了迭代
 */
public class GraphBreadFirstPaths extends AbstractGraphSearchPaths {

    public GraphBreadFirstPaths(Graph G, int s) {
        super.edgeTo = new int[G.V()];
        super.marked = new boolean[G.V()];
        super.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        LinkedQueue queue = new LinkedQueue();

        marked[s] = true;
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            int v = (int) queue.dequeue();

            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int s) {
        return marked[s];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> stack = new Stack<>();

        for (int x = v; x != s; x = edgeTo[x]) stack.push(x);
        stack.push(s);

        return stack;
    }
}
