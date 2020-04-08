package com.algorithms.algorithms.graphs.weighted_undirected_graph;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.LinkedQueue;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * 所谓的即时是指放入优先队列之前就"即时"演算了。
 */
public class ImmediatelyPrimMST {

    /**
     * 相关顶点离最小生成树最近的一条边
     */
    private Edge[] edgeTo;

    /**
     * 相关顶点离最小生成树最近的那条边的权重值
     */
    private double[] distTo;

    /**
     * 点是否被访问到
     */
    private boolean[] marked;

    /**
     * 有效的横切边,最小优先队列.这里确切的说,应该说是一个优先图,类似于符号表与符号图之前的区别。
     */
    private IndexMinPQ<Double> pq;

    public ImmediatelyPrimMST(EdgeWeightGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            //找最小的,所以将初始化为最大值
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            //pq.delMin()表示最小值所对应的索引
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightGraph G, int v) {
        marked[v] = true;

        //遍历该顶点所有的边,然后依次比较,将最小的一条边放入优先队列中
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;

            //替换最小的边和顶点
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.changeKey(w, e.weight());
                else pq.insert(w, e.weight());
            }
        }
    }

    public Iterable<Edge> edges() {
        LinkedQueue queue = new LinkedQueue();
        Stream.of(edgeTo).forEach(queue::enqueue);
        return queue;
    }

    public double weight() {
        return DoubleStream.of(distTo).sum();
    }
}
