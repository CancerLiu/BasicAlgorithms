package com.algorithms.algorithms.graphs.directed_graph;

import edu.princeton.cs.algs4.Bag;

/**
 * 有向图,和无向图的基本区别就是顶点之间的连接是有方向的,邻接表表示的时候v->w表示为w在v索引对应的集合中
 */
public class Directedgraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Directedgraph(int v) {
        this.V = v;
        adj = (Bag<Integer>[]) new Bag[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Directedgraph reverse() {
        Directedgraph d = new Directedgraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                d.addEdge(w, v);
            }
        }
        return d;
    }

}
