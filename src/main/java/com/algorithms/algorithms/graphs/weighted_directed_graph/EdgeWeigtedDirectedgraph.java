package com.algorithms.algorithms.graphs.weighted_directed_graph;

import edu.princeton.cs.algs4.Bag;

/**
 * 基于定义的加权有向边的基础上定义的加权有向图
 */
public class EdgeWeigtedDirectedgraph {

    private int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public EdgeWeigtedDirectedgraph(int v) {
        V = v;
        E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj(v);
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) b.add(e);
        }
        return b;
    }
}
