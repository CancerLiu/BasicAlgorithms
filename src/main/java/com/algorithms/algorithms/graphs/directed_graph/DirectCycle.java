package com.algorithms.algorithms.graphs.directed_graph;

import com.algorithms.algorithms.graphs.weighted_directed_graph.DirectedEdge;
import com.algorithms.algorithms.graphs.weighted_directed_graph.EdgeWeigtedDirectedgraph;
import edu.princeton.cs.algs4.Stack;

/**
 * 用于在有向图中判断可达,这是后续的拓扑排序的基础
 * <p>
 * 总体是在无向图的Cycle类基础上改的,还是用的深度优先搜索
 */
public class DirectCycle {

    /**
     * 用于标记顶点是否被访问。
     */
    private boolean[] marked;
    /**
     * 记录该顶点的上一个顶点
     */
    private int[] edgeTo;
    /**
     * 递归调用栈上得顶点是否被访问
     * 相对于marked数组表示的是所有走到的顶点集合,onstack数组表示的是在引入方向的情况下形成的可能的有向环中的走过的顶点。
     */
    private boolean[] onStack;


    /**
     * 找到的其中一个有向环(找到一个即可)
     */
    private Stack<Integer> cycle;

    public DirectCycle(Directedgraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];

        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) dfs(G, i);
        }
    }

    public DirectCycle(EdgeWeigtedDirectedgraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];

        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) dfsWeight(G, i);
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    private void dfs(Directedgraph G, int v) {
        marked[v] = true;
        onStack[v] = true;

        for (int w : G.adj(v)) {
            if (this.hasCycle()) {
                //先判断是否已经找到环
                return;
            } else if (!marked[w]) {
                //没找到则继续按照路径到达点
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                //如果发现一个点已经被到达,同时其递归栈中也被到达(说明该次递归之前来过),可知是绕了一圈回来的,可知是环
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                //此处v是起点,w是终点
                cycle.push(w);
                cycle.push(v);
            }
        }
        //一个栈调用完毕后,将之置为false。
        onStack[v] = false;
    }

    private void dfsWeight(EdgeWeigtedDirectedgraph G, int v) {
        marked[v] = true;
        onStack[v] = true;

        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (this.hasCycle()) {
                //先判断是否已经找到环
                return;
            } else if (!marked[w]) {
                //没找到则继续按照路径到达点
                edgeTo[w] = v;
                dfsWeight(G, w);
            } else if (onStack[w]) {
                //如果发现一个点已经被到达,同时其递归栈中也被到达(说明该次递归之前来过),可知是绕了一圈回来的,可知是环
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                //此处v是起点,w是终点
                cycle.push(w);
                cycle.push(v);
            }
        }
        //一个栈调用完毕后,将之置为false。
        onStack[v] = false;
    }

}
