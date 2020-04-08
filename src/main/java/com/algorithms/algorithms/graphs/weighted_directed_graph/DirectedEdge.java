package com.algorithms.algorithms.graphs.weighted_directed_graph;

/**
 * 类比于加权无向图,此处的加权有向图中也对与边自定义一个对象
 */
public class DirectedEdge {

    /**
     * 边的其中一个顶点v
     */
    private int v;

    /**
     * 边的另一个顶点w
     */
    private int w;

    /**
     * 边的权重
     */
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return v;
    }

    @Override
    public String toString() {
        return "DirectedEdge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
