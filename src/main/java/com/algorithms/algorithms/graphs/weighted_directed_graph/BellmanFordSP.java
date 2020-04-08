package com.algorithms.algorithms.graphs.weighted_directed_graph;

import edu.princeton.cs.algs4.Queue;

/**
 * 该类也是用于在加权有向图中寻找某个顶点到起点s之间的最短距离
 * <p>
 * 这个算法相比于ShortestPath的最短距离算法,其支持负权重和环的情况,很实用。
 * <p>
 * 关键点在于:只有上一轮中的distTo[]值发生变化的顶点指出的边才能够改变其他distTo[]元素的值。
 */
public class BellmanFordSP {

    /**
     * 从当前结点到起点s的路径长度
     */
    private double[] distTo;

    /**
     * 从起点到某个顶点的最后一条边,即该顶点到上一个顶点之间的边
     */
    private DirectedEdge[] edgeTo;

    /**
     * 顶点是否在队列中,类似于之前的marked[]数组。
     */
    private boolean[] onQ;

    /**
     * 正在被放松的顶点
     */
    private Queue<Integer> queue;

    /**
     * relax(...)调用的次数
     */
    private int cost;

    /**
     * edgeTo[]中是否有负权重环
     */
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeigtedDirectedgraph G, int s) {

        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<>();
        distTo = new double[G.V()];

        for (int v = 0; v < G.V(); v++) distTo[v] = Double.POSITIVE_INFINITY;

        distTo[s] = 0.0;
        //加入队列
        queue.enqueue(s);
        onQ[s] = true;

        //如果有(负权重)环,则该图中无法找到最短距离
        while (!queue.isEmpty() && !hashNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeigtedDirectedgraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }

            //每放松一定次数就检测下负权重环的存在。
            if (cost++ % G.V() == 0) findNegativeCycle();
        }
    }

    private void findNegativeCycle() {
        int v = edgeTo.length;
        EdgeWeigtedDirectedgraph spt;
        spt = new EdgeWeigtedDirectedgraph(v);

        //从edgeTo[]数组中取结点元素然后构成一幅图,之后对这副图进行负权重的判定
        for (int i = 0; i < v; i++) {
            if (edgeTo[i] != null) {
                spt.addEdge(edgeTo[i]);
            }
        }

        //构造负权重判定的对象
        EdgeWeightedCycleFinder cf;
        cf = new EdgeWeightedCycleFinder(spt);

        cycle = cf.cycle();
    }


    private boolean hashNegativeCycle() {
        return cycle != null;
    }

    private Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }
}
