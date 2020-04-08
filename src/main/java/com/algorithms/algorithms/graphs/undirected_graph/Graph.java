package com.algorithms.algorithms.graphs.undirected_graph;

import edu.princeton.cs.algs4.Bag;

/**
 * 最基本的无向图,使用邻接表数组的数据结构
 */
public class Graph {

    /**
     * 顶点的数量
     */
    private final int V;
    /**
     * 边的数量
     */
    private int E;

    /**
     * 邻接表
     */
    private Bag<Integer>[] adj;

    public Graph(int v) {
        this.V = v;
        this.E = 0;

        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * 连接两个顶点
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * @param v 顶点
     * @return 与该顶点连接的顶点集合
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
