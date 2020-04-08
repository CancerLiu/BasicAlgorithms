package com.algorithms.algorithms.graphs.weighted_undirected_graph;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * 基于Kruskal的最小生成树算法,每次都找最短的边,一直到需要的边数量.唯一需要注意的地方就是防止环的出现
 * <p>
 * 效率大部分时候比不上Prim算法
 * <p>
 * 具体防止出现环的算法是使用的union-find算法中的connect()方法。
 */
public class KruskalMST {

    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightGraph G) {
        mst = new Queue<>();

        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) pq.insert(e);

        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);

            if (uf.connected(w, v)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weightTotal = 0.0;
        for (Edge e : mst) {
            weightTotal += e.weight();
        }
        return weightTotal;
    }
}
