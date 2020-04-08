package com.algorithms.algorithms.graphs.weighted_undirected_graph;

import edu.princeton.cs.algs4.Bag;

/**
 * 加权无向图,除了算法之外的主要区别外,其他的就是使用边Edge对象来表示边,而非两个顶点
 */
public class EdgeWeightGraph {

    private int E;
    private int V;
    private Bag<Edge>[] adj;


    public EdgeWeightGraph(int v) {
        V = v;
        E = 0;

        adj = (Bag<Edge>[]) new Bag[v];
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

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[w].add(e);
        adj[v].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj(v)) {
                //如此来去重,因为是无向图
                if (e.other(v) > v) b.add(e);
            }
        }
        return b;
    }
}
