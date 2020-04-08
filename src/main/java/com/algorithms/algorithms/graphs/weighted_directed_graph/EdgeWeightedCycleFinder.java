package com.algorithms.algorithms.graphs.weighted_directed_graph;

import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedCycleFinder {

    /**
     * 检测是否走到该点
     */
    private boolean[] marked;

    /**
     * 该顶点为索引,该顶点到上一个顶点之间的边
     */
    private DirectedEdge[] edgeTo;


    /**
     * 找到的环
     */
    private Stack<DirectedEdge> cycle;

    /**
     * 负权重环
     */
    private Stack<DirectedEdge> negativeCycle;

    /**
     * 类比加权无环有向图中找环,该数组是检测一个临时环中的数组
     */
    private boolean[] onStack;

    public EdgeWeightedCycleFinder(EdgeWeigtedDirectedgraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(EdgeWeigtedDirectedgraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if (onStack[w]) {
                //找到了环
                cycle = new Stack<>();
                DirectedEdge x = e;
                double negativeValue = 0.0;
                //x最初是v——>w的一条边
                for (; x.from() != w; x = edgeTo[x.from()]) {
                    cycle.push(x);
                    negativeValue += x.weight();
                }
                cycle.push(x);
                negativeValue += x.weight();

                //利用负权重环的定义来判定,即环的所有权重相加小于0
                if (negativeValue < 0.0) negativeCycle = cycle;

                return;
            }
        }
        onStack[v] = false;
    }

    public boolean hasNegativeCycle() {
        return negativeCycle != null;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
