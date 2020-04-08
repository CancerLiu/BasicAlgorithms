package com.algorithms.algorithms.graphs.undirected_graph;

/**
 * 将刚才定义的图通过构造方法传入该类,然后使用该类中的各种方法获得图的属性
 * <p>
 * 此处主要获得的是图中点之间的连通问题,具体分深度优先搜索和广度优先搜索。
 * 这里也会记录下搜索的路径,以数组形式保存
 */
public abstract class AbstractGraphSearchPaths {

    /**
     * 用于标记顶点是否已经被算法搜索过
     */
    protected boolean[] marked;

    /**
     * 从起点到一个顶点的已知路径上的最后一个顶点
     */
    protected int[] edgeTo;

    /**
     * 图中搜索的起点
     */
    protected int s;
}
