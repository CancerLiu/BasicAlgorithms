package com.algorithms.algorithms.graphs.weighted_undirected_graph;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * 最小生成树(MST——minimum spanning tree)的延迟算法。
 * <p>
 * 最小生成树是指含有一幅图的所有顶点且所有边的权值之和最小的树。
 *
 * 延时版本在从优先队列中取边的时候,才进行计算,延时在这个地方
 */
public class LazyPrimMST {

    /**
     * 用于记录顶点是否被访问到
     */
    private boolean[] marked;

    /**
     * 放置最小生成树所需的队列
     */
    private Queue<Edge> mst;

    /**
     * 用于Prim算法每次筛选出最小边时所需的优先队列
     */
    private MinPQ<Edge> pq;

    /**
     * 这里假设传入的G是连通的
     */
    public LazyPrimMST(EdgeWeightGraph G) {

        marked = new boolean[G.V()];
        mst = new Queue<>();
        pq = new MinPQ<>();

        //从索引为0的第一个顶点开始找
        visit(G, 0);

        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (!marked[v] && !marked[w]) continue;

            mst.enqueue(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    /**
     * Prim算法寻找横切边,将找到的边放入优先队列中
     *
     * @param G 图对象
     * @param v 顶点
     */
    private void visit(EdgeWeightGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) pq.insert(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weightCount = 0.0;
        for (Edge edge : mst) {
            weightCount += edge.weight();
        }
        return weightCount;
    }
}
