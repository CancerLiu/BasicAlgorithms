package com.algorithms.algorithms.graphs.weighted_undirected_graph;

/**
 * 加权图中最大的区别就在于,因为边有自己的权重属性,所以不再像非加权图中那样,简单地使用两个顶点来表示条边。
 */
public class Edge implements Comparable<Edge> {

    /**
     * 边的一个顶点v
     */
    private final int v;

    /**
     * 边的另一个顶点w
     */
    private final int w;

    /**
     * 边的权重
     */
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return w;
    }

    public int other(int s) {
        return s == v ? w : v;
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight > that.weight) return 1;
        else if (this.weight < that.weight) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
