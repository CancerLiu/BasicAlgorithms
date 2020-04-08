package com.algorithms.algorithms.graphs.weighted_directed_graph;

import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Stack;

/**
 * 加权有向图中找最短路径,类似于加权无向图中找最小生成树.
 * <p>
 * 这里使用的是Dijkstra算法。类似于Prim算法
 * <p>
 * Prim算法是每次添加的都是离树最近的非树顶点;Dijkstra算法是每次添加离起点最近的非树顶点
 */
public class ShortestPath {

    /**
     * 用于存在该顶点到上一个顶点之间的边
     */
    private DirectedEdge[] edgeTo;

    /**
     * 相关顶点索引到起点的(最短距离)
     */
    private double[] distTo;

    /**
     * 最小优先队列(最小优先图),用于保存需要被
     * 放松的顶点并确认下一个被放松的顶点
     */
    private IndexMinPQ<Double> pq;

    /**
     * 在加权有向图G中计算到所有顶点到s的最短路径
     */
    public ShortestPath(EdgeWeigtedDirectedgraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());

        for (int v = 0; v < G.V(); v++) distTo[v] = Double.POSITIVE_INFINITY;

        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }

    }

    /**
     * 边的松弛
     */
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();

        if (distTo[w] < distTo[v] + e.weight()) {
            //则放松成功
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            //以下代码非放松代码,而是当前数据结构才需要加入的
            if (pq.contains(w)) pq.changeKey(w, distTo[w]);
            else pq.insert(w, distTo[w]);
        }
    }

    /**
     * 顶点的松弛————可知顶点的放松就是将该顶点邻接的所有边都放松
     */
    private void relax(EdgeWeigtedDirectedgraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            relax(e);
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<DirectedEdge> stack = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            stack.push(e);
        }
        return stack;
    }

    /**
     * 无环加权有向图中的最短路径算法,因为有了无环的条件,所以可以在此基础使用一种效率更高的算法
     * <p>
     * 即按照拓扑排序的方式放松一章无环加权有向图中的顶点
     * <p>
     * AcyclicSP可以复用很多的ShortestPath中的API
     */
    class AcyclicSP {
        private DirectedEdge[] edgeTo;
        private double[] distTo;

        public AcyclicSP(EdgeWeigtedDirectedgraph G, int s) {
            edgeTo = new DirectedEdge[G.V()];
            distTo = new double[G.V()];

            for (int v = 0; v < G.V(); v++) distTo[v] = Double.POSITIVE_INFINITY;
            distTo[s] = 0.0;

            DirectedTopological top = new DirectedTopological(G);

            for (int v : top.order()) {
                relax(G, v);
            }
        }
    }
}
