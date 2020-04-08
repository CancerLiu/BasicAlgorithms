package com.algorithms.algorithms.graphs.directed_graph;

/**
 * 这里基本上和无向图的相关类似方法是一样的
 */
public class AbstractDirectedGraphSearchPaths {

    /**
     * 该顶点的上一个顶点的数组
     */
    protected int[] edgeTo;

    /**
     * 该顶点的是否被访问到
     */
    protected boolean[] marked;

    /**
     * 起始顶点
     */
    protected int s;
}
